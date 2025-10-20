package com.example.home.domain.entity

import com.example.network.models.Course

data class CourseEntity(
    val hasLike: Boolean,
    val id: Int,
    val price: String,
    val publishDate: String,
    val rate: String,
    val startDate: String,
    val text: String,
    val title: String
)

