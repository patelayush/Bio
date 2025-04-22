package model

data class Message(
    val name: String? = "",
    val email: String? = "",
    val body: String? = ""
) {
    fun getSubject(): String {
        return "Bio App - Reach out" + name.takeIf { !it.isNullOrBlank() }?.let { "from $name" }
    }
}