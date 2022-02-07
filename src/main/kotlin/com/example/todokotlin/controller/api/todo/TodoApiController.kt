package com.example.todokotlin.controller.api.todo

import com.example.todokotlin.model.http.TodoDto
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/todo")
class TodoApiController {

    // R
    @GetMapping("")
    fun read(@RequestParam(required = false) index: Int?) { // query parameter가 있으면 단건조회, 없으면 전체조회

    }

    // C
    @PostMapping("")
    fun create(@Valid @RequestBody todoDto: TodoDto) {

    }

    // U
    @PutMapping("")
    fun update(@Valid @RequestBody todoDto: TodoDto) {

    }


    // D
    @DeleteMapping("/{index}")
    fun delete(@PathVariable(name = "index") _index: Int) {

    }
}