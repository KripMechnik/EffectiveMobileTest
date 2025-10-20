package com.example.home.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.home.databinding.CourseItemBinding
import com.example.home.domain.entity.CourseEntity

class CoursesAdapter(
    private val courses: List<CourseEntity>
) : RecyclerView.Adapter<CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = CourseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courses[position].title, courses[position].text)
    }
}