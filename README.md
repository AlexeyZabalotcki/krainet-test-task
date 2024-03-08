# Krainet CV Service

Service for candidate management

The service is launched using docker-compose

PostgreSQL database

Technologies: Java, SpringBoot, Swagger for API description, Maven for building, logging, Liquibase data migration.

## Run application:

To run the application, execute the following commands:

### `git clone <username>/krainet-test-task.git`

### `docker compose up -d --build`

### `usage sample:`

### `Swagger endpoint:`
   * `http://localhost:8080/swagger-ui/index.html#/`

1. Directions 

    1.1. Get all directions: GET `http://localhost:8080/api/v1/directions?page=0&size=10&sort=title,asc&filter=`

    1.2. Create new direction: POST `http://localhost:8080/api/v1/directions`
    ```json
   {
    "title": "Back",
    "description": "Frontend side"
    }
   ```
   1.3. Update direction: PUT `http://localhost:8080/api/v1/directions/1`
    ```json
    {
    "title": "DevOps",
    "description": "Searching in containers 222"
    }
    ```

2. Candidates

   1.1. Get all candidates: GET `http://localhost:8080/api/v1/candidates?page=0&size=10&sort=description,desc&filter=`

   1.2. Create new candidate: POST `http://localhost:8080/api/v1/candidates`
    ```json
   {
    "lastName":"Ivanov",
    "firstName":"Ivan",
    "middleName":"Ivanovich",
    "description":"Java developer",
    "possibleDirectionIds":"1,2"
    }
   ```
   + add photo and cvFile as files
   
   `*`When using Swagger, this endpoint returns an `HTTP 415 Unsupported Media Type` error.
      Use Postman to test this endpoint
   
   
   1.3. Update candidate: PUT `http://localhost:8080/api/v1/candidates/1`
    
   ```json
      {
       "lastName":"Alexeev",
       "firstName":"Alexey",
       "middleName":"Alexeevich",
       "description":"Java developer",
       "possibleDirectionIds":"1,2"
       }
   ```
    
   + add photo and cvFile as files

   `*`When using Swagger, this endpoint returns an `HTTP 415 Unsupported Media Type` error.
      Use Postman to test this endpoint

3. Tests

   1.1. Get all tests: GET `http://localhost:8080/api/v1/tests?page=0&size=10&sort=description,desc&filter=`

   1.2. Create new test: POST `http://localhost:8080/api/v1/tests`
    ```json
   {
    "title": "Backend test",
    "description": "some test"
    }
   ```
   1.3. Update test: PUT `http://localhost:8080/api/v1/tests/1`
    ```json
    {
    "title": "DevOps test",
    "description": "Searching in containers"
    }
    ```

4. Candidate-Tests

   1.1. Get all Candidate-tests: GET `http://localhost:8080/api/v1/candidate-tests?page=0&size=10&sort=candidateId,desc&filter=`

   1.2. Create new Candidate-test: POST `http://localhost:8080/api/v1/candidate-tests`
    ```json
   {
    "candidateId": 1,
    "testId": 1,
    "testResults": [
        {
            "date": "2024-01-05",
            "score": 85.0
        },
        {
            "date": "2023-04-12",
            "score": 90.0
        }
    ]
    }
   ```
   1.3. Update Candidate-test: PUT `http://localhost:8080/api/v1/candidate-tests/1`
    ```json
    {
    "candidateId": 2,
    "testId": 2,
    "testResults": [
        {
            "date": "2024-01-05",
            "score": 85.0
        },
        {
            "date": "2023-04-12",
            "score": 90.0
        }
    ]
    }
    ```
