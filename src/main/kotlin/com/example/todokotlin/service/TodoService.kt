package com.example.todokotlin.service

import com.example.todokotlin.database.Todo
import com.example.todokotlin.database.convertTodo
import com.example.todokotlin.model.http.TodoDto
import com.example.todokotlin.model.http.convertTodoDto
import com.example.todokotlin.repository.TodoRepositoryImpl
import org.springframework.stereotype.Service

/**
 * 현업에서는 java에서는 model mapper, kotlin에서는 kotlin reflection을 사용한다 (dto <-> entity)
 */
@Service
class TodoService(
    val todoRepositoryImpl: TodoRepositoryImpl
) {

    // C
    fun create(todoDto: TodoDto): TodoDto? {
        return todoDto.let {
            Todo().convertTodo(it) // it(TodoDto) -> convert -> it(Todo)
        }.let {
            todoRepositoryImpl.save(it) // 현재 it은 Todo객체 이므로 바로 넣어준다.
        }?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    // R
    fun read(index: Int): TodoDto? {
        return todoRepositoryImpl.findOne(index)?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    fun readAll(): MutableList<TodoDto> {
        return todoRepositoryImpl.findAll()
            .map {
                TodoDto().convertTodoDto(it)
            }.toMutableList()
    }


    // U
    fun update(todoDto: TodoDto): TodoDto? {
        return todoDto.let {
            Todo().convertTodo(it) // it(TodoDto) -> convert -> it(Todo)
        }.let {
            todoRepositoryImpl.save(it) // 현재 it은 Todo객체 이므로 바로 넣어준다.
        }?.let {
            TodoDto().convertTodoDto(it)
        }
    }

    // D
    fun delete(index: Int): Boolean {
        return todoRepositoryImpl.delete(index)
    }
}