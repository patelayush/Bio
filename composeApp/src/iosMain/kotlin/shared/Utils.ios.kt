package shared

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import email
import model.Message
import platform.Foundation.NSError
import platform.Foundation.NSURL
import platform.MessageUI.MFMailComposeResult
import platform.MessageUI.MFMailComposeViewController
import platform.MessageUI.MFMailComposeViewControllerDelegateProtocol
import platform.PDFKit.PDFDocument
import platform.PDFKit.PDFView
import platform.UIKit.UIApplication
import platform.UIKit.UIDevice
import platform.UIKit.UIUserInterfaceIdiomPad
import platform.darwin.NSObject
import kotlin.Result.Companion.failure
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

actual object AppContext

actual fun platform(): String {
    return "ios"
}

@Composable
actual fun PdfColumn(url: String, modifier: Modifier) {
    val nsUrl = NSURL(string = url)
    val document = PDFDocument(uRL = nsUrl)

    val view = PDFView().apply {
        this.document = document
        this.autoScales = true
        this.displayMode =
            1 // Represent Enum entry 1 ( Single Page Continuous ) from PDFDisplayMode
        this.displayDirection = 0 // Represent Enum entry 1 ( Horizontal ) from PDFDisplayDirection
    }

    UIKitView(
        factory = { view },
        modifier = modifier,
    )
}

actual suspend fun sendEmail(message: Message) {
    suspendCoroutine<Result<Unit>> { continuation ->
        if (!MFMailComposeViewController.canSendMail()) {
            continuation.resume(failure(Exception("Mail services are not available")))
            return@suspendCoroutine
        }

        val mail = MFMailComposeViewController()
        mail.setToRecipients(listOf(email))
        mail.setSubject(message.getSubject())
        mail.setMessageBody(message.body ?: "", isHTML = false)

        mail.mailComposeDelegate = object : NSObject(),
            MFMailComposeViewControllerDelegateProtocol {
            override fun mailComposeController(
                controller: MFMailComposeViewController,
                didFinishWithResult: MFMailComposeResult,
                error: NSError?
            ) {
                controller.dismissViewControllerAnimated(true, completion = null)
                return when (didFinishWithResult) {
                    MFMailComposeResult.MFMailComposeResultCancelled -> continuation.resume(
                        failure(Exception("Email sending cancelled"))
                    )

                    MFMailComposeResult.MFMailComposeResultSaved -> continuation.resume(
                        Result.success(
                            Unit
                        )
                    )

                    MFMailComposeResult.MFMailComposeResultSent -> continuation.resume(
                        Result.success(
                            Unit
                        )
                    )

                    MFMailComposeResult.MFMailComposeResultFailed -> continuation.resume(
                        failure(
                            error?.let { Exception(it.localizedDescription) }
                                ?: Exception("Email sending failed")))

                    else -> {
                        continuation.resume(
                            failure(
                                error?.let { Exception(it.localizedDescription) }
                                    ?: Exception("Email sending failed")))
                    }
                }

            }
        }

        val viewController = UIApplication.sharedApplication.keyWindow?.rootViewController
        viewController?.presentViewController(mail, animated = true, completion = null)
            ?: continuation.resume(failure(Exception("Could not present view controller")))
    }.run {
        onFailure {
            println("Error sending email: ${it.message}")
        }
        onSuccess {
            println("Email sent successfully")
        }
    }
}

@Composable
actual fun isTabletVersion(): Boolean {
    return UIDevice.currentDevice.userInterfaceIdiom == UIUserInterfaceIdiomPad
}