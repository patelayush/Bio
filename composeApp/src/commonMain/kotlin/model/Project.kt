package model

import githubLink

data class Project(
    val title: String,
    val shortDescription: String?= null,
    val githubLink: String? = null,
    val webLink:String?=null,
    val playStoreLink:String?=null,
    val tools: List<String>? = null,
)

val projectsList = listOf(
    Project(
        title = "FlickCase",
        shortDescription = "FlickCase is built with passion and a deep appreciation for storytelling in all its forms, aiming to provide you with an intuitive and informative way to navigate the vast landscape of film and television.Built with the power of **(Compose Multiplatform)**, FlickCase delivers a consistent and intuitive experience across various devices. ",
        githubLink = "$githubLink/FlickCase",
        webLink = "https://patelayush.github.io/FlickCase/",
        playStoreLink = "https://play.google.com/store/apps/details?id=org.appsmith.flickcase",
        tools = listOf("Compose Multiplatform", "Jetpack Compose", "Kotlin", "Ktor", "Coil"),
    ),
    Project(
        title = "Bug-Free Bio",
        shortDescription = "This app is a dynamic representation of my professional path and the skills I've acquired. Built with **(Compose Multiplatform)**, this app demonstrates my commitment to modern and efficient development practices.",
        githubLink = "$githubLink/Bug-Free-Bio",
        webLink = "https://patelayush.github.io/Bug-Free-Bio/",
        playStoreLink = "https://play.google.com/store/apps/details?id=org.appsmith.bio",
        tools = listOf("Compose Multiplatform", "Jetpack Compose", "Kotlin", "Canvas API")
    ),
    Project(
        title = "Innovative Computing Projects Evaluator",
        shortDescription = "The mobile app is for evaluators to scan the QR Code of the teams, evaluate their projects, and submit the results. The web app is used to design the evaluation survey questions, manage evaluators and teams, and review the submitted evaluations.",
        githubLink = "$githubLink/Innovative-Computing-Projects-Evaluator",
        tools = listOf("Android App Development", "Java", "Node JS", "Retrofit", "Mobile Vision API", "JWT Authentication")
    ),
    Project(
        title = "Restaurant Seating Reservation Application ",
        shortDescription = "The Android app is used by restaurants to register customers who are looking for seating. The customers can book a table through SMS and are also alerted through SMS when a table is available for them. The web app is used to monitor the status of the reservations.",
        githubLink = "$githubLink/Restaurant-Seating-Reservation-Application",
        tools = listOf("Android App Development", "Java", "Twilio API", "Node Js", "Rest API")
    ),
    Project(
        title = "More to come..."
    )
)
