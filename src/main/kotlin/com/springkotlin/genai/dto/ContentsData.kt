package com.springkotlin.genai.dto

data class ContentsData(
    val parts: List<PartsData>,
    val role: String = "user"
)

data class PartsData(
    val text: String,
    val inlineData: Blob? = null,
    val functionCall: FunctionCall? = null,
    val functionResponse: FunctionResponse? = null,
    val fileData: FileData? = null,
    val executableCode: ExecutableCode? = null,
    val codeExecutionResult: CodeExecutionResult? = null
)

// Placeholder data classes for the nested objects
data class Blob(val data: Any?)
data class FunctionCall(val details: Any?)
data class FunctionResponse(val details: Any?)
data class FileData(val details: Any?)
data class ExecutableCode(val details: Any?)
data class CodeExecutionResult(val details: Any?)

