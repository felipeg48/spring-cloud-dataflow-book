# Client Tools: cURL, Httpie, jq


## Data
```json
{"title":"The Matrix","actor":"Keanu Reeves","year":1999,"genre":"fiction","stars":5}
{"title":"Memento","actor":"Guy Pearce","year":2000,"genre":"drama","stars":4}
{"title":"The Prestige","actor":"Christian Bale","year":2006,"genre":"drama","stars":3}
{"title":"Disturbia","actor":"Shia LaBeouf","year":2007,"genre":"drama","stars":3}
```


## Registering Apps
- **cURL**:
   ```shell
   curl -s -X POST \
    -d "uri=https://dataflow.spring.io/rabbitmq-maven-latest" \
    -d "force=true" \
    localhost:9393/ | jq .
   ```
- **Httpie**:
   ```shell
   http -f POST \
    :9393/apps \
    uri=https://dataflow.spring.io/rabbitmq-maven-latest \
    force=true
   ```