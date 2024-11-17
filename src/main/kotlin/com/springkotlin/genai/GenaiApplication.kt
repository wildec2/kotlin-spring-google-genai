package com.springkotlin.genai

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GenaiApplication

fun main(args: Array<String>) {
	runApplication<GenaiApplication>(*args)
}
