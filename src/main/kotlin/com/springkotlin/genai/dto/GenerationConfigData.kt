package com.springkotlin.genai.dto

data class GenerationConfigData(
    val stopSequences: List<String>? = null,
    val responseMimeType: String? = null,
    val responseSchema: Schema? = null,
    val candidateCount: Int? = null,
    val maxOutputTokens: Int? = null,
    val temperature: Double? = null,
    val topP: Double? = null,
    val topK: Int? = null,
    val presencePenalty: Double? = null,
    val frequencyPenalty: Double? = null,
    val responseLogprobs: Boolean? = null,
    val logprobs: Int? = null
)

// Placeholder for the Schema object
data class Schema(
    val details: Any? // Adjust this based on the actual schema structure
)