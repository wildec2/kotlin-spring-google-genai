package com.springkotlin.genai.dto

data class GenerateRequestBody(
    val prompt: String,
    val temperature: Double,
    val maxOutputTokens: Int
)
