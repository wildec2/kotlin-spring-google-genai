package com.springkotlin.genai.dto

data class GenerateRequestBody(
    val contents: List<ContentsData>,
    val generationConfig: GenerationConfigData? = null
)
