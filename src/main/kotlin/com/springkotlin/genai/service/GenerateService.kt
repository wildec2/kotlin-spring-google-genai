package com.springkotlin.genai.service

import com.springkotlin.genai.dto.ContentsData
import com.springkotlin.genai.dto.GenerateRequestBody
import com.springkotlin.genai.dto.GenerationConfigData
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.companionObjectInstance

@Service
class GenerateService(private val webClientBuilder: WebClient.Builder) {

    private val logger: Logger = LoggerFactory.getLogger(GenerateService::class.java)

    fun generateText(
        apiKey: String,
        model: String,
        contents: List<ContentsData>,
        temperature: Double,
        maxOutputTokens: Int,
        topP: Double,
        topK: Int?,
        responseMimeType: String? = null,
        responseSchema: Any? = null
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
            logger.error("WebClientResponseException: ${e.responseBodyAsString}", e)
            "Error: ${e.responseBodyAsString}"
        } catch (e: ClassNotFoundException) {
            logger.error("Class not found: $responseSchema", e)
            "Error: Invalid response schema."
        } catch (e: Exception) {
            logger.error("Unexpected error occurred", e)
            "Error: ${e.message}"
        }
    }

    private fun parseResponseSchema(responseSchema: Any?): Map<String, Any>? {
        return try {
            val className = responseSchema as? String ?: return null
            val enumClass = Class.forName(className).kotlin

            if (enumClass.java.isEnum) {
                // Look for entries in the companion object if available
                val companionObject = enumClass.companionObjectInstance
                val entriesProperty = companionObject?.let { _ ->
                    enumClass.companionObject?.members?.find { it.name == "entries" }
                }

                val enumValues = enumClass.companionObjectInstance
                    ?.let { companion ->
                        enumClass.companionObject?.members
                            ?.find { it.name == "entries" }
                            ?.call(companion) as? List<*>
                    } ?: enumClass.java.enumConstants.map { it.toString() }

                mapOf(
                    "type" to "string",
                    "enum" to enumValues
                )
            } else {
                null
            }
        } catch (e: ClassNotFoundException) {
            logger.error("Class not found: $responseSchema", e)
            null
        } catch (e: Exception) {
            logger.error("Unexpected error occurred", e)
            "Error: ${e.message}"
            null
        }
    }
}