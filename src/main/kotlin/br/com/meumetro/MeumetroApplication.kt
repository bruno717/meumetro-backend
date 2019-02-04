package br.com.meumetro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication(exclude = [MongoAutoConfiguration::class])
class MeumetroApplication

fun main(args: Array<String>) {
    runApplication<MeumetroApplication>(*args)
}