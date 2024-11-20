package com.springkotlin.genai.service

import com.springkotlin.genai.dto.ContentsData
import com.springkotlin.genai.dto.GenerateRequestBody
import com.springkotlin.genai.dto.GenerationConfigData
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException

@Service
class GenerateService(private val webClientBuilder: WebClient.Builder) {

    //chat request
    fun generateText(
        contents: List<ContentsData>,
        model: String,
        temperature: Double,
        maxOutputTokens: Int,
        topP: Double,
        topK: Int?,
        apiKey: String
    ): String {
        val url = "/models/$model:generateContent?key=$apiKey"

        val requestBody = GenerateRequestBody(
            contents = contents,
            generationConfig = GenerationConfigData(
                temperature = temperature,
                maxOutputTokens = maxOutputTokens,
                topP = topP,
                topK = topK
                // Add other parameters if necessary
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