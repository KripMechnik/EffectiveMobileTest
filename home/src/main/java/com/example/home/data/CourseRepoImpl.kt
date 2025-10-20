package com.example.home.data

import com.example.home.domain.repo.CourseRepo
import com.example.network.api.CoursesApi
import com.example.network.models.CourseList
import com.example.core.utils.Resource
import javax.inject.Inject

class CourseRepoImpl @Inject constructor(
    private val coursesApi: CoursesApi
): CourseRepo {

    override suspend fun getCourses(): Resource<CourseList> {
        return try {
            val courses = coursesApi.getCourses()
            Resource.Success(courses)
        } catch (e: Exception) {
            handleError(e)
        }
    }

    private fun handleError(e: Exception): Resource.Error<CourseList> {
        return Resource.Error(errorMessage = e.localizedMessage, errorCode = null)
    }

}