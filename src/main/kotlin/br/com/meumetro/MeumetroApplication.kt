package br.com.meumetro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MeumetroApplication

fun main(args: Array<String>) {
	runApplication<MeumetroApplication>(*args)
}