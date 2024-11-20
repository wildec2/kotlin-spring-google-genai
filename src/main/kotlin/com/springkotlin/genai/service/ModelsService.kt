package com.springkotlin.genai.service

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class ModelsService(private val webClientBuilder: WebClient.Builder) {

    //list models
    fun listModels(apiKey: String): Map<String, Any>? {
        return webClientBuilder.build()
            .get()
            .uri("/models?key=$apiKey")
            .retrieve()
            .bodyToMono<Map<String, Any>>()
            .block()
    }

    // Get details for a specific model
    fun getModelDetails(modelName: String, apiKey: String): Map<String, Any>? {
        return webClientBuilder.build()
            .get()
            .uri("/models/$modelName?key=$apiKey")
            .retrieve()
            .bodyToMono<Map<String, Any>>()
            .block()
    }

}