package model

data class Experience(
    val year: Int,
    val company: String? = null,
    val location: String? = null,
    val timePeriod: String? = null,
    val title: String? = null,
    val description: List<String>? = null
)

val experiences = listOf(
    Experience(
        year = 2025,
    ),
    Experience(
        year = 2024,
        company = "Ally Financial",
        location = "Charlotte, NC",
        timePeriod = "Sept 2024 - Present",
        title = "Principal Android Developer",
        description = listOf(
            "Involved in the maintenance, stability, and performance optimization of the core native Android application for Ally Auto within the Ally mobile app.",
            "Spearheaded efforts to enhance the stability of the native Android application for the Auto division, resulting in a measurable reduction in crashes.",
            "Diagnosed, debugged, and resolved critical bugs and performance bottlenecks within the native Android codebase for the Ally Auto application.",
            "Implemented robust error handling and logging mechanisms to proactively identify and address potential stability issues in the Auto app.",
            "Actively learning and contributing to the company's strategic transition towards React Native for a unified mobile platform.",
            "Collaborating with cross-functional teams to understand the architecture and contribute to the development of new features using React Native.",
            "Leveraged native Android expertise while acquiring proficiency in React Native principles and best practices.",
        )
    ),
    Experience(
        year = 2021,
        company = "LendingTree",
        location = "Charlotte, NC",
        timePeriod = "July 2019 - Sept 2024",
        title = "Senior Android Developer",
        description = listOf(
            "Having spearheaded LendingTree: Spring's development with Jetpack Compose, I transformed the LendingTree mortgage experience.",
            "Redesigned app's UI/UX, delivering user-friendly and intuitive experience with use of Canvas API for custom drawings and animations.",
            "Embraced learning mindset, continuously exploring and implementing latest tools and technologies, including Jetpack Compose Navigation, and the single activity architecture stack.",
            "Gained comprehensive exposure to the entire application lifecycle, from initial product requirements to ensuring user satisfaction.",
            "Contributed to development of comprehensive financial management solutions, empowering users to manage their finances effectively.",
            "Spearheaded adoption of cutting-edge technology, enhancing app's performance and elevating user satisfaction levels.",
        )
    ),
    Experience(
        year = 2019,
        company = "LendingTree",
        location = "Charlotte, NC",
        timePeriod = "July 2019 - Sept 2024",
        title = "Android Developer II",
        description = listOf(
            "Having spearheaded LendingTree: Spring's development with Jetpack Compose, I transformed the LendingTree mortgage experience.",
            "Redesigned app's UI/UX, delivering user-friendly and intuitive experience with use of Canvas API for custom drawings and animations.",
            "Embraced learning mindset, continuously exploring and implementing latest tools and technologies, including Jetpack Compose Navigation, and the single activity architecture stack.",
            "Gained comprehensive exposure to the entire application lifecycle, from initial product requirements to ensuring user satisfaction.",
            "Contributed to development of comprehensive financial management solutions, empowering users to manage their finances effectively.",
            "Spearheaded adoption of cutting-edge technology, enhancing app's performance and elevating user satisfaction levels.",
        )
    ),
    Experience(
        year = 2018,
        company = "TimeTap",
        location = "Charlotte, NC",
        timePeriod = "Aug 2018 - June 2019",
        title = "Android Developer",
        description = listOf(
            "Led development of native application TimeTap from scratch, focusing on creating a more user-friendly and free-flowing experience.",
            "Implemented key features such as Biometric Authentication, Multi Screen Layout, Session Management, Bottom Navigation View, and Calendar View Appointments.",
            "Leveraged Android platform, REST APIs, Retrofit v2.0, and MVC Architecture to ensure seamless integration and efficient data handling.",
        )
    ),
    Experience(
        year = 2018,
        company = "TIAA",
        location = "Charlotte, NC",
        timePeriod = "June 2018 - Aug 2018",
        title = "IT Intern",
        description = listOf(
            "Implemented Logistic Regression model to find transactions that can be fraud using R.",
            "Implementation was followed by the parallel testing of the model for daily and monthly transactions.",
            "The model was used for scoring all the transactions to determine the fraud scores. Further these scores were used to set a limit to classify them as fraud and non-fraud testing various tuning parameters and normalization of the data.",
        )
    ),
    Experience(
        year = 2017,
        company = "University of North Carolina at Charlotte",
        title = "Master's in Computer Science",
        location = "GPA: 3.9/4",
        timePeriod = "Sept 2017 - May 2019",
    ),
    Experience(
        year = 2013,
        company = "Rajiv Gandhi Proudyogiki Vishwavidhyalaya, India",
        title = "Bachelor's in Computer Science",
        location = "GPA: 7.8/10",
        timePeriod = "Sept 2013 - May 2017",
    ),
)