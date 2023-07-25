package com.example.flowasynchronousexpandeblerecyclerview

import android.media.metrics.PlaybackErrorEvent
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

    fun getPersonFlowWithPets(item: Person) {
        viewModelScope.launch {
            combine(
                repository.getPersonsFlow(),
                repository.getAnimalsFlow()
            ) { listOfPerson, listOfAnimals ->
                val newFlow = listOfPerson.map { person ->
                    if (person.name == item.name) {
                        person.copy(pets = person.pets + listOfAnimals)
                    } else {
                        person
                    }
                }
                return@combine newFlow
            }.collect {
                myMainFlow.emit(it)

            }
        }
    }
}