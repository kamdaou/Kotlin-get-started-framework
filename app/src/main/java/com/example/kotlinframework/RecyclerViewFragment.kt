package com.example.kotlinframework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinframework.database.FrameworkDatabase
import com.example.kotlinframework.database.FrameworkDatabaseDao
import com.example.kotlinframework.databinding.FragmentRecyclerViewBinding
import com.example.kotlinframework.viewModel.FrameworkViewModel

class RecyclerViewFragment : Fragment() {

    private lateinit var binding:FragmentRecyclerViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRecyclerViewBinding.inflate(inflater)
        val adapter = RecyclerViewAdapter(ItemListener { id ->
            //action to do after an item has been clicked
        })
        val application = requireActivity().application
        val dataSource = FrameworkDatabase.getInstance(requireContext()).frameworkDatabaseDao
        val viewModelFactory = FrameworkViewModelFactory( dataSource, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(FrameworkViewModel::class.java)

        binding.recyclerView.adapter = adapter

        // observe the list so that we can submit it at everytime
        viewModel.frameworkDataList.observe(this.requireActivity()) { list ->
            list?.apply {
                adapter.submitList(list)
            }
        }

        return binding.root
    }
}