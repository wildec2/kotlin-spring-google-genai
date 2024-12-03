# Generative AI Service Project

## Overview
This project provides a Spring Boot-based REST API for interacting with Google's Generative Language API. It allows users to list available models, fetch model details, and generate content using specified models.

### Features
- **List Available Models**: Fetches a list of all available models.
- **Model Details**: Provides details about a specific model.
- **Generate Text**: Uses a specified model to generate text based on a prompt, with configurable parameters like `temperature` and `maxOutputTokens`.

---

## Prerequisites
1. **API Key**: Obtain an API key from [Google AI Studio](https://ai.google.com/studio/).
2. **Java 17+**: Ensure Java is installed on your machine.
3. **Gradle**: Use Gradle to build and run the project.

---

## Running the Project
1. Clone the repository:
   ```bash
   git clone https://github.com/wildec2/kotlin-spring-google-genai.git
   cd <repository-directory>
2. To compile and package the project, run:
    ```bash
    ./gradlew build
3. To start the application, execute:
   ```bash
   ./gradlew bootRun 

The application will be available at http://localhost:8080.


---

## Endpoints

### 1. List Models
Fetches a list of available models.

**Request**
```bash
curl -X GET "http://localhost:8080/models/list?apiKey=YOUR_API_KEY" -H "Accept: application/json"
```

Response Returns a JSON object containing the list of models.

### 2. Get Model
Fetches details for a specified model.

**Request**
```bash
curl -X GET "http://localhost:8080/models/list?apiKey=YOUR_API_KEY" -H "Accept: application/json"
```

Response Returns a JSON object with details about the specified model.

### 3. Generate Text [Prompt/Chat]
Generates content based on a prompt using a specific model.

**Prompt Request With Selected Model**
```bash
curl --location 'http://localhost:8080/generate?model=gemini-1.5-flash&apiKey=YOUR_API_KEY' \
--header 'Content-Type: application/json' \
--data-raw '{
  "contents": [
    {
      "role": "user",
      "parts": [
        { "text": "Tell me a story about Dublin City." }
      ]
    }
  ],
  "generationConfig": {
    "temperature": 0.1,
    "maxOutputTokens": 100,
    "topP": 0.3,
    "topK": 40
  }
}'
```
**Chat Request**
```bash
curl -X POST "http://localhost:8080/generate?apiKey=YOUR_API_KEY" \
-H "Content-Type: application/json" \
-d '{
  "contents": [
    {
      "role": "user",
      "parts": [
        {
          "text": "Hello! My Name is Colum."
        }
      ]
    },
    {
      "role": "model",
      "parts": [
        {
          "text": "Hi there Colum! How can I help you today?"
        }
      ]
    },
    {
      "role": "user",
      "parts": [
        {
          "text": "Do you remember my name?"
        }
      ]
    }
  ],
  "generationConfig": {
    "temperature": 0.7,
    "maxOutputTokens": 150,
    "topP": 0.9,
    "topK": 40
  }
}'
```
**Enum Mode Request**
```bash
curl --location 'http://localhost:8080/generate?model=gemini-1.5-flash-001&apiKey=YOUR_API_KEY' \
--header 'Content-Type: application/json' \
--data-raw '{
  "contents": [
    {
      "role": "user",
      "parts": [
        { "text": "The night life in Dublin city is awesome" }    
      ]
    }
  ],
  "generationConfig": {
    "responseMimeType": "text/x.enum",
    "responseSchema": "Sentiment"
  }
}'
```


This endpoint generates content based on the provided content, using the specified parameters such as `temperature` and `maxOutputTokens`.
It can use user prompts and previous model responses to build an interactive chat experience. The request allows the model to be changed. 
The default the model is `gemini-1.5-flash-001`.


---


## Configuration

### API Key
Replace `YOUR_API_KEY` in the requests with your actual API key. For security, avoid hardcoding the API key in the codebase. Use environment variables or external configuration files to securely manage the API key.

### Parameters
- **temperature**: Controls the randomness of the generated text. Values range from 0 (deterministic) to 1 (highly random). A lower value results in more predictable text, while a higher value leads to more diverse, creative outputs.

- **maxOutputTokens**: Sets the maximum number of tokens (words or parts of words) the model will generate in the output. This limits the length of the generated response.

- **TopP**: The maximum cumulative probability of tokens to consider when sampling.

- **TopK**: Optional. The maximum number of tokens to consider when sampling.

- **responseMimeType**: Optional. MIME type of the generated candidate text. Supported MIME types are: `text/plain`: (default) Text output. 
`application/json`:  This forces the model to constrain decoding, such that token selection is guided by the supplied schema.
`text/x.enum`: ENUM as a string response in the response candidates which allows you to constrain the output to a fixed set of values.

- **responseSchema**: Optional. Output schema of the generated candidate text. If set, a compatible responseMimeType must also be set.


---


## Development

### Structure
- **Controller**: Handles incoming HTTP requests. The controller layer is responsible for routing requests to the appropriate service methods.

- **Service**: Contains the logic for interacting with the Generative Language API. The service layer is where the API communication and business logic take place.

### Adding Features
To add new features, modify or add new endpoints in the `GenerateController` and implement corresponding logic in the `GenerateService`. You can extend functionality by interacting with the Generative Language API or enhancing the service layer to support additional capabilities.


---


## .gitignore
Ensure sensitive files like `.env` or `application.yml` are ignored in version control. See the included `.gitignore` file for more details.


