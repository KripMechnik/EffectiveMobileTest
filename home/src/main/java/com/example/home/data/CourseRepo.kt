package com.example.home.data

import com.example.network.models.CourseList
import com.example.network.models.Resource

interface CourseRepo {
    suspend fun getCourses(): Resource<CourseList>
}