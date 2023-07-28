package com.example.flowasynchronousexpandeblerecyclerview.Flow

import com.example.flowasynchronousexpandeblerecyclerview.Animal
import com.example.flowasynchronousexpandeblerecyclerview.Person
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


object Repository {
    // Метод, который возвращает Flow со списком животных
    fun getAnimalsFlow(): Flow<List<Animal>> = flow {
        // Имитируем асинхронную загрузку данных с задержкой
        // В реальности здесь может быть запрос к базе данных или сетевой сервис

        // Просто создаем некоторые животные для примера
        val animals = listOf(
            Animal("Барсик", "Кот"),
            Animal("Рекс", "Собака"),
            Animal("Васька", "Кот"),
            Animal("Мурзик", "Кот"),
            Animal("Тузик", "Собака")
        )
        delay(3000)
        emit(animals)

    }

    // Метод, который возвращает Flow со списком людей
    fun getPersonsFlow(): Flow<List<Person>> = flow {
        // Имитируем асинхронную загрузку данных с задержкой
        // В реальности здесь может быть запрос к базе данных или сетевой сервис

        // Просто создаем некоторых людей с их животными для примера
        val persons = listOf(
            Person("Иван", listOf(Animal("Барсик", "Кот"))),
            Person("Анна", listOf(Animal("Барсик", "Кот")))
        )
        emit(persons)

    }
}