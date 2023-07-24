package com.example.flowasynchronousexpandeblerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowasynchronousexpandeblerecyclerview.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnClick {
    lateinit var binding: ActivityMainBinding
    private val myViewModel: MyViewModel by viewModels()
    lateinit var adapter: ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ItemAdapter(this)
        val layout = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = layout
        observeList()
        myViewModel.getPersons()


    }

    fun observeList() {
        lifecycleScope.launch {
            myViewModel.myMainFlow.collect {
                adapter.submitList(it)


            }
        }


    }

    override fun onclick() {
        myViewModel.getPersonFlowWithPets()
    }
}