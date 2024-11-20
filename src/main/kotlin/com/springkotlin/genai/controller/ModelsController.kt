package com.springkotlin.genai.controller

import com.springkotlin.genai.service.ModelsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ModelsController(private val modelsService: ModelsService) {

    @GetMapping("/models/list")
    fun listModels(@RequestParam apiKey: String): Map<String, Any>? {
        return modelsService.listModels(apiKey)
    }

    @GetMapping("/models/details")
    fun getModelDetails(
        @RequestParam apiKey: String,
        @RequestParam modelName: String
    ): Map<String, Any>? {
        return modelsService.getModelDetails(modelName, apiKey)
    }

}