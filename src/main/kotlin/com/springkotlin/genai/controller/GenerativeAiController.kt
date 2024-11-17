package com.springkotlin.genai.controller

import com.springkotlin.genai.service.GenerativeAiService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

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

    @GetMapping("/generate")
    fun generateText(
        @RequestParam prompt: String,
        @RequestParam temperature: Double,
        @RequestParam maxOutputTokens: Int,
        @RequestParam apiKey: String
    ): String {
        return generativeAiService.generateText(prompt, temperature, maxOutputTokens, apiKey)
    }
}