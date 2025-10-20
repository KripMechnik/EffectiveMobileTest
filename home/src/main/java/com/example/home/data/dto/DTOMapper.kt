package com.example.home.data.dto

import com.example.home.domain.entity.CourseEntity
import com.example.network.models.Course


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