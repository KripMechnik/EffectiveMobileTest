package com.example.home.domain.repo

import com.example.network.models.CourseList
import com.example.core.utils.Resource

interface CourseRepo {
    suspend fun getCourses(): Resource<CourseList>
}