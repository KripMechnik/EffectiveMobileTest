package com.example.home.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.core.di.utils.getComponent
import com.example.home.R
import com.example.home.databinding.FragmentMainBinding
import com.example.home.di.HomeComponent
import com.example.home.ui.main.adapter.CoursesAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private lateinit var viewModel: MainViewModel

    private lateinit var coursesAdapter: CoursesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(
            owner = this,
            factory = getComponent<HomeComponent>().viewModelFactory
        )[MainViewModel::class.java]
        coursesAdapter = CoursesAdapter(viewModel.state.value.courses ?: emptyList())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchBar.queryHint = resources.getString(R.string.search_courses)
        binding.rvCourses.adapter = coursesAdapter

        viewModel.state.onEach { value ->
            value.courses?.let {
                coursesAdapter = CoursesAdapter(it)
                binding.rvCourses.adapter = coursesAdapter
                coursesAdapter.notifyDataSetChanged()
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }

}