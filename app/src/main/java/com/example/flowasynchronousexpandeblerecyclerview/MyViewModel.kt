package com.example.flowasynchronousexpandeblerecyclerview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowasynchronousexpandeblerecyclerview.Flow.Repository
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.combine

import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val repository = Repository

    val myMainFlow: MutableStateFlow<List<Person>> = MutableStateFlow(listOf())
    fun getPersons() {
        viewModelScope.launch {
            repository.getPersonsFlow().collect {
                myMainFlow.emit(it)


            }

        }
    }

    fun getPersonFlowWithPets() {
        viewModelScope.launch {
            combine(repository.getPersonsFlow(), repository.getAnimalsFlow()) { person, animals ->

                val newFlow = person.map { person ->
                    person.copy(pets = person.pets + animals)

                }
                println("@@@" + newFlow.toString())
                newFlow

            }.collect {
                myMainFlow.emit(it)
                println("###" + it)
            }


        }
    }
}