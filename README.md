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

2. As Admin/User you can work with currency information:

    2.1. Get all currencies requests for today: GET `http://localhost:8000/client/date/<YYYY-MM-DD>`
    
    2.2. Get all currencies requests by rate: GET `http://localhost:8000/client/rate/<rate>`

    2.3. Get all currencies requests by amount: GET `http://localhost:8000/client/between?from=1&to=5`

    2.4. Create new request to third-party server: POST `http://localhost:8000/client/new`
    ```json
    {
    "from": "USD",
    "to": "BYN",
    "amount": 10,
    "subscribe": true
    }
    ```

3. As Admin you can work with currency information:
    
    3.1. Get a specific currency by id: GET `http://localhost:8000/client/id/<id>`

    3.2. Get first currency request: GET `http://localhost:8000/client/first`

    3.3. Get last currency request: GET `http://localhost:8000/client/last`

    3.4. Delete currency request by their id: DELETE `http://localhost:8000/client/<id>`

### Diagram:

![Diagram.gif](Diagram.gif)