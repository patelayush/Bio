package model

data class Project(
    val title: String,
    val shortDescription: String,
    val githubLink: String? = null,
    val webLink:String?=null
)

val projectsList = listOf(
    Project(
        "FlickCase",
        "FlickCase is built with passion and a deep appreciation for storytelling in all its forms, aiming to provide you with an intuitive and informative way to navigate the vast landscape of film and television.Built with the power of **(Compose Multiplatform)**, FlickCase delivers a consistent and intuitive experience across various devices. ",
        "https://github.com/patelayush/FlickCase",
        "https://patelayush.github.io/FlickCase/"
    ),
    Project(
        "Bug-Free-Bio",
        "This app is a dynamic representation of my professional path and the skills I've acquired. Built with Compose Multiplatform, this app demonstrates my commitment to modern and efficient development practices.",
        "https://github.com/patelayush/Bug-Free-Bio",
        "https://patelayush.github.io/Bug-Free-Bio/"
    ),
)
