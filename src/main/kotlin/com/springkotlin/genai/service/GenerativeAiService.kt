package com.springkotlin.genai.service

import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class GenerativeAiService(private val webClientBuilder: WebClient.Builder) {

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

    //prompt request
    fun generateText(prompt: String, temperature: Double, maxOutputTokens: Int, apiKey: String): String {
        val url = "/models/gemini-1.5-flash:generateContent?key=$apiKey"

        val requestBody = mapOf(
            "contents" to listOf(
                mapOf(
                    "parts" to listOf(
                        mapOf("text" to prompt)
                    )
                )
            ),
            "generationConfig" to mapOf(
                "temperature" to temperature,
                "maxOutputTokens" to maxOutputTokens
            )
        )

        return try {
            val response = webClientBuilder.build()
                .post()
                .uri(url)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map::class.java) // Adjust based on actual response structure
                .block()

            response?.toString() ?: "No response from API"
        } catch (e: WebClientResponseException) {
            "Error: ${e.responseBodyAsString}" // Log or return API error for debugging
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }
}