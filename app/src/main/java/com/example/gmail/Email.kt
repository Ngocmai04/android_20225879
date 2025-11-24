package com.example.gmail

data class Email(
    val icon: Int,
    val sender: String,
    val subject: String,
    val time: String,
    val content: String
)