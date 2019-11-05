package com.kyudong.assignment.data

import java.io.Serializable

data class Company(
    val id: Int,
    val name: String,
    val end_time: String,
    val content: String,
    val view_count: Int,
    val favorite_count: Int,
    val homepage_count: Int,
    val resume_count: Int,
    val image_url: String,
    val fields: ArrayList<String>
) : Serializable