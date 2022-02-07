package com.example.todokotlin.config

import com.example.todokotlin.database.TodoDataBase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration // spring이 구동될 때 참고한다
class AppConfig {

    @Bean(initMethod = "init") // bean 생성 시 init 메소드를 지정할 수 있다. -> @Bean(initMethod = "[init 메소드 명]")
    fun todoDataBase(): TodoDataBase {
        return TodoDataBase()
    }
}