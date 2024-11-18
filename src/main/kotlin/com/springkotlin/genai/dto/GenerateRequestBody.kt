package com.springkotlin.genai.dto

data class GenerateRequestBody(
    val prompt: String,
    val temperature: Double = 0.5,
    val maxOutputTokens: Int = 100,
    val topP: Double = 0.33,
    val topK: Int?
)
