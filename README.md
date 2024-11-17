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
   git clone <repository-url>
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

### 3. Generate Text
Generates content based on a prompt using a specific model.

**Request**
```bash
curl --location 'http://localhost:8080/generate?prompt=Write+a+story+about+a+magic+backpack.&temperature=0.7&maxOutputTokens=200&apiKey=YOUR_API_KEY' \
--header 'Accept: text/plain'
```

This endpoint generates content based on the provided prompt, using the specified parameters such as `temperature` and `maxOutputTokens`.

## Configuration

### API Key
Replace `YOUR_API_KEY` in the requests with your actual API key. For security, avoid hardcoding the API key in the codebase. Use environment variables or external configuration files to securely manage the API key.

### Parameters
- **temperature**: Controls the randomness of the generated text. Values range from 0 (deterministic) to 1 (highly random). A lower value results in more predictable text, while a higher value leads to more diverse, creative outputs.

- **maxOutputTokens**: Sets the maximum number of tokens (words or parts of words) the model will generate in the output. This limits the length of the generated response.

## Development

### Structure
- **Controller**: Handles incoming HTTP requests. The controller layer is responsible for routing requests to the appropriate service methods.

- **Service**: Contains the logic for interacting with the Generative Language API. The service layer is where the API communication and business logic take place.

### Adding Features
To add new features, modify or add new endpoints in the `GenerativeAiController` and implement corresponding logic in the `GenerativeAiService`. You can extend functionality by interacting with the Generative Language API or enhancing the service layer to support additional capabilities.

## .gitignore
Ensure sensitive files like `.env` or `application.yml` are ignored in version control. See the included `.gitignore` file for more details.


