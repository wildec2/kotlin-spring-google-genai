package com.springkotlin.genai.controller

import com.springkotlin.genai.dto.GenerateRequestBody
import com.springkotlin.genai.service.GenerativeAiService
import org.springframework.web.bind.annotation.*

@RestController
class GenerativeAiController(private val generativeAiService: GenerativeAiService) {

    @GetMapping("/models/list")
    fun listModels(@RequestParam apiKey: String): Map<String, Any>? {
        return generativeAiService.listModels(apiKey)
    }

    @GetMapping("/models/details")
    fun getModelDetails(
        @RequestParam apiKey: String,
        @RequestParam modelName: String
    ): Map<String, Any>? {
        return generativeAiService.getModelDetails(modelName, apiKey)
    }

    @PostMapping("/generate")
    fun generateText(
        @RequestBody requestBody: GenerateRequestBody,
        @RequestParam apiKey: String
    ): String {
        return generativeAiService.generateText(
            requestBody.prompt,
            requestBody.temperature,
            requestBody.maxOutputTokens,
            apiKey
        )
    }
}