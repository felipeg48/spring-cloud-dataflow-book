# Custom Stream Apps

## API Endpoint

- `/v1/api/movies`

## Request Object

```json
{
  "MovieRequest": {
    "action": "create",
    "movies": [
      {
        "id": "tt0133093",
        "title": "The Matrix",
        "actor": "Keanu Reeves",
        "year": 1999,
        "genre": "fiction",
        "stars": 5
      },
      {
        "id": "tt0209144",
        "title": "Memento",
        "actor": "Guy Pearce",
        "year": 2000,
        "genre": "drama",
        "stars": 4
      }
    ]
  }
}
```

## Response Object

```json
{
    "MovieResponse": {
        "code": 200,
        "message": "Movies processing..",
        "responseTime": "2020-07-08T12:27:01.75"
    }
}
```