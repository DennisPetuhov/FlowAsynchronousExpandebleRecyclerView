package com.example.flowasynchronousexpandeblerecyclerview

import com.example.flowasynchronousexpandeblerecyclerview.Flow.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext


data class Animal(val name: String, val species: String)


data class Person(val name: String, val pets: List<Animal>)




suspend fun main() {

    val animalsFlow = Repository.getAnimalsFlow()
//    animalsFlow.collect { animals ->
//        println("Список животных:")
//        for (animal in animals) {
//            println("${animal.name} - ${animal.species}")
//        }
//    }


    val personsFlow = Repository.getPersonsFlow()
//    personsFlow.collect { persons ->
//        println("Список людей и их домашних животных:")
//        for (person in persons) {
//            println("${person.name} имеет следующих домашних животных:")
//            for (pet in person.pets) {
//                println("${pet.name} - ${pet.species}")
//            }
//        }
//    }
//    addAnimalsToPersons(personsFlow, animalsFlow).collect {
//        println(it.toString())
//    }

    addAnimalsToPersonsbyName(personsFlow, animalsFlow, "Иван").collect {
        println(it.toString())
    }
}


fun addAnimalsToPersons(
    personsFlow: Flow<List<Person>>,
    animalsFlow: Flow<List<Animal>>
): Flow<List<Person>> {
    return combine(personsFlow, animalsFlow) { persons, animals ->
        // Создаем новый список людей с добавленными домашними животными
        val personsWithPets = persons.mapIndexed { index, person ->
            // Если есть соответствующий список животных для текущего человека,
            // то добавляем эти животные к списку его домашних животных
            if (index < animals.size) {
                person.copy(pets = person.pets + animals[index])
            } else {
                // В случае, если список животных закончился, просто возвращаем человека без изменений
                person
            }
        }
        personsWithPets
    }
}

fun addAnimalsToPersonsbyName(
    personsFlow: Flow<List<Person>>,
    animalsFlow: Flow<List<Animal>>,
    name: String
): Flow<List<Person>> {
    return combine(personsFlow, animalsFlow) { persons, animals ->
        // Создаем новый список людей с добавленными домашними животными
        val personsWithPets = persons.map{ person ->
            // Если есть соответствующий список животных для текущего человека,
            // то добавляем эти животные к списку его домашних животных
            if (person.name == name) {
                person.copy(pets = person.pets + animals)
            } else {
                // В случае, если список животных закончился, просто возвращаем человека без изменений
                person
            }
        }
       return@combine personsWithPets
    }
}