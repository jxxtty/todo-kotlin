package com.example.todokotlin.model.http

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.validation.FieldError
import java.time.LocalDateTime
import javax.validation.Validation

class TodoDtoTest {

    val validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDtoTest() { // TodoDto에 작성한 @field:NotBlank가 잘 작동하는지 확인
        val todoDto = TodoDto().apply {
            this.title = "테스트"
            this.description = ""
            this.schedule = "2021-12-12 12:12:12"
        }

        val result = validator.validate(todoDto)

        result.forEach {
            println(it.propertyPath.last().name)
            println(it.message)
            println(it.invalidValue)
        }

        // result.isEmpty() -> 에러가 없으면 true, 있으면 false

        Assertions.assertEquals(true, result.isEmpty())
    }
}