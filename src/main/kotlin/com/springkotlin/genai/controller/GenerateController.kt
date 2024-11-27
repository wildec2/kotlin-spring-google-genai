package com.springkotlin.genai.controller

import com.springkotlin.genai.dto.GenerateRequestBody
import com.springkotlin.genai.service.GenerateService
import org.springframework.web.bind.annotation.*

@RestController
class GenerateController(private val generateService: GenerateService) {

    @PostMapping("/generate")
    fun generateText(
        @RequestBody requestBody: GenerateRequestBody,
        @RequestParam model: String = "gemini-1.5-flash-001",
        @RequestParam apiKey: String
    ): String {
        val generationConfig = requestBody.generationConfig
        return generateService.generateText(
            apiKey = apiKey,
            model = model,
            contents = requestBody.contents,
            temperature = generationConfig?.temperature ?: 0.3,
            maxOutputTokens = generationConfig?.maxOutputTokens ?: 100,
            topP = generationConfig?.topP ?: 0.5,
            topK = generationConfig?.topK,
            responseMimeType = generationConfig?.responseMimeType,
            responseSchema = generationConfig?.responseSchema
        )
    }
}