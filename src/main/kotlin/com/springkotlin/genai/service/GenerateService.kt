package com.springkotlin.genai.service

import com.springkotlin.genai.dto.ContentsData
import com.springkotlin.genai.dto.GenerateRequestBody
import com.springkotlin.genai.dto.GenerationConfigData
import com.springkotlin.genai.enums.Sentiment
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException

@Service
class GenerateService(private val webClientBuilder: WebClient.Builder) {

    //chat request
    fun generateText(
        apiKey: String,
        model: String,
        contents: List<ContentsData>,
        temperature: Double,
        maxOutputTokens: Int,
        topP: Double,
        topK: Int?,
        responseMimeType: String? = null,
        responseSchema: String? = null
    ): String {
        val url = "/models/$model:generateContent?key=$apiKey"

        val resolvedMimeType = responseMimeType ?: "text/plain"
        val resolvedSchema = responseSchema?.let { parseResponseSchema(it) }

        val requestBody = GenerateRequestBody(
            contents = contents,
            generationConfig = GenerationConfigData(
                temperature = temperature,
                maxOutputTokens = maxOutputTokens,
                topP = topP,
                topK = topK,
                responseMimeType = resolvedMimeType,
                responseSchema = resolvedSchema
            )
        )

        return try {
            val response = webClientBuilder.build()
                .post()
                .uri(url)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map::class.java)
                .block()

            response?.toString() ?: "No response from API"
        } catch (e: WebClientResponseException) {
            "Error: ${e.responseBodyAsString}"
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }

    private fun parseResponseSchema(responseSchema: Any?): String? {
        return when (responseSchema) {
            "Sentiment" -> Sentiment::class.java.name
            //"CustomDataClass" -> CustomDataClass::class
            else -> null
        }
    }
}