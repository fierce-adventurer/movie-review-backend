# Movies API

A Spring Boot REST API for managing movie information and reviews using MongoDB.

## Overview

This project is a RESTful API that allows users to:
- Retrieve a list of all movies
- Fetch details about a specific movie using its IMDB ID
- Add reviews to movies

The application uses MongoDB for data storage and follows a layered architecture pattern with controllers, services, and repositories.

## Technologies Used

- Java 17
- Spring Boot 3.x
- Spring Data MongoDB
- MongoDB
- Project Lombok
- Maven

## Project Structure

```
├── src/main/java/dev/fierceadventurer/movies/
│   ├── GlobalExceptionHandler.java    # Global exception handling
│   ├── Movie.java                     # Movie entity
│   ├── MovieController.java           # REST endpoints for movies
│   ├── MovieRepository.java           # Data access for movies
│   ├── MovieService.java              # Business logic for movies
│   ├── MoviesApplication.java         # Application entry point
│   ├── Review.java                    # Review entity
│   ├── ReviewController.java          # REST endpoints for reviews
│   ├── ReviewRepository.java          # Data access for reviews
│   └── ReviewService.java             # Business logic for reviews
└── src/main/resources/
    └── application.properties         # Application configuration
```

## API Endpoints

### Movies

- `GET /api/v1/movies` - Get all movies
- `GET /api/v1/movies/{imdbId}` - Get a specific movie by IMDB ID

### Reviews

- `POST /api/v1/movies/{imdbId}/reviews` - Add a review to a movie

## Setup Instructions

### Prerequisites

- Java 17 or higher
- Maven
- MongoDB (or Docker to run MongoDB in a container)

### Running MongoDB

#### Option 1: Using locally installed MongoDB

Ensure MongoDB is installed and running on your system (default port: 27017).

#### Option 2: Using Docker

```bash
docker run -d -p 27017:27017 --name mongodb mongo:latest
```

### Building and Running the Application

1. Clone the repository:
```bash
git clone https://github.com/yourusername/movies-api.git
cd movies-api
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

The API will be available at http://localhost:8080

## Example Requests

### Get All Movies

```bash
curl -X GET http://localhost:8080/api/v1/movies
```

### Get a Specific Movie

```bash
curl -X GET http://localhost:8080/api/v1/movies/tt1234567
```

### Add a Review

```bash
curl -X POST http://localhost:8080/api/v1/movies/tt1234567/reviews \
  -H "Content-Type: application/json" \
  -d '{"reviewBody": "This movie was fantastic! I loved the cinematography and acting."}'
```

## Database Schema

### Movies Collection

```json
{
  "_id": ObjectId(),
  "imdbId": "String",
  "title": "String",
  "releaseDate": "String",
  "trailerLink": "String",
  "poster": "String",
  "genres": ["String"],
  "backdrops": ["String"],
  "reviewIds": [DBRef to Review]
}
```

### Reviews Collection

```json
{
  "_id": ObjectId(),
  "body": "String"
}
```

## Error Handling

The application implements global exception handling for:
- Invalid requests (400 Bad Request)
- Database connection issues (503 Service Unavailable)
- Internal server errors (500 Internal Server Error)

## Future Enhancements

- User authentication and authorization
- Rate limiting
- Pagination for movie listings
- Advanced search/filtering capabilities
- Movie ratings in addition to text reviews

## License

[MIT License](https://opensource.org/licenses/MIT)