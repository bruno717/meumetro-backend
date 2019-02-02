package br.com.meumetro.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("test")
class TestController {


    @GetMapping
    private fun findAll(): String {
        return "teste"
    }
}