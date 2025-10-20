package com.example.home.domain

import com.example.home.data.dto.toCourseEntity
import com.example.home.domain.entity.CourseEntity
import com.example.home.domain.repo.CourseRepo
import com.example.network.models.CourseList
import com.example.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val courseRepo: CourseRepo
) {
    operator fun invoke(): Flow<Resource<List<CourseEntity>>> = flow {
        emit(Resource.Loading())
        val result = courseRepo.getCourses()
        if (result is Resource.Success)
            emit(Resource.Success(data = result.data!!.toCourseEntities()))
        else if (result is Resource.Error)
            emit(Resource.Error(errorMessage = result.errorMessage, errorCode = result.errorCode))
    }

    private fun CourseList.toCourseEntities(): List<CourseEntity> {
        return this.courses.map { it.toCourseEntity() }
    }

}