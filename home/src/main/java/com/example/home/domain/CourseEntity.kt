package com.example.home.domain

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

fun Course.toCourseEntity(): CourseEntity {
    return CourseEntity(
        hasLike = this.hasLike,
        id = this.id,
        price = this.price,
        publishDate = this.publishDate,
        rate = this.rate,
        startDate = this.startDate,
        text = this.text,
        title = this.title
    )
}
