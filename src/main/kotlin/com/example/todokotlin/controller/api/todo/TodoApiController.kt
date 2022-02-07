package com.example.todokotlin.controller.api.todo

import com.example.todokotlin.model.http.TodoDto
import com.example.todokotlin.service.TodoService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/todo")
class TodoApiController(val todoService: TodoService) {

    // R

    // readAll을 따로 뺏는데 굳이 redirect 처리하는 코드를 작성하는 이유는뭐지? @RequestParam의 null여부로 메소드를 나눠서 호출하는게 안되는건가???
//    @GetMapping("")
//    fun read(@RequestParam index: Int): TodoDto? {
//        return todoService.read(index)
//    }

    @GetMapping("")
    fun read(@RequestParam(required = false) index: Int?): ResponseEntity<Any?> {
        return index?.let {
            todoService.read(it)
        }?.let{
            return ResponseEntity.ok(it)
        }?: kotlin.run { // index가 없다면 -> readAll을 호출해서 전체조회를 해라
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "/api/todo/all").build()
        }
    }


    @GetMapping("/all")
    fun readAll(): MutableList<TodoDto> {
        return todoService.readAll()
    }

    // C
    @PostMapping("")
    fun create(@Valid @RequestBody todoDto: TodoDto): TodoDto? {
        return todoService.create(todoDto)
    }

    // U
    // TODO create = 201을 내리고, update = 200을 내리도록 변경
    @PutMapping("")
    fun update(@Valid @RequestBody todoDto: TodoDto): TodoDto? {
        return todoService.create(todoDto)
    }


    // D
    @DeleteMapping("/{index}")
    fun delete(@PathVariable(name = "index") _index: Int): ResponseEntity<Any> {
        if(!todoService.delete(_index)) {
            return ResponseEntity.status(500).build()
        }
        return ResponseEntity.ok().build()
    }
}