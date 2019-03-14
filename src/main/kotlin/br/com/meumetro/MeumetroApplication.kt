package br.com.meumetro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.runApplication
import java.util.*
import javax.annotation.PostConstruct


@SpringBootApplication(exclude = [MongoAutoConfiguration::class])
class MeumetroApplication {

    @PostConstruct
    fun init() {
        val timeZone = TimeZone.getTimeZone("America/Sao_Paulo")
        TimeZone.setDefault(timeZone)
        GregorianCalendar.getInstance(timeZone)
    }
}

fun main(args: Array<String>) {
    runApplication<MeumetroApplication>(*args)
}