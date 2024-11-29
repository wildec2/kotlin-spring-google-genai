package com.springkotlin.genai.dto

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class GenerationConfigData(
    val stopSequences: List<String>? = null,
    val responseMimeType: String? = null,
    val responseSchema: Any? = null,
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