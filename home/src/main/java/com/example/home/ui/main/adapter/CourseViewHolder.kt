package com.example.home.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.home.databinding.CourseItemBinding

class CourseViewHolder(private val binding: CourseItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(title: String, desc: String){
        binding.tvCourseTitle.text = title
        binding.tvDescription.text = desc
    }
}