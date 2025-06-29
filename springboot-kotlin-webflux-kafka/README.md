# springboot-kotlin-webflux-kafka
Template Project Springboot kotlin Coroutines API

### Base Project
- gradle (kotlin)
- kotlin
- webflux
- kafka


### CURL


#### Send
```
curl --location --request POST 'http://localhost:8080/kafka/send' \
--header 'Content-Type: application/json' \
--data-raw '{
        "message": "message test"
    }'
```





