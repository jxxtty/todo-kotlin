package com.example.todokotlin.database

import com.example.todokotlin.model.http.TodoDto
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Todo(
    var index: Int? = null,
    var title: String? = null,
    var description: String? = null,
    var schedule: LocalDateTime? = null,
    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
)

// [dto -> entity변환] 1. 이렇게 확장함수를 사용하는 방법, 2. model mapper를 사용하는 방법, 3. kotlin reflection 사용하는 방법
// 확장 함수
fun Todo.convertTodo(todoDto: TodoDto): Todo {
    return Todo().apply {
        this.index = todoDto.index
        this.title = todoDto.title
        this.description = todoDto.description
        this.schedule = LocalDateTime.parse(todoDto.schedule, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) // 이미 검증을 했기때문에 오류발생 X
        this.createdAt = todoDto.createdAt
        this.updatedAt = todoDto.updatedAt
    }
}
