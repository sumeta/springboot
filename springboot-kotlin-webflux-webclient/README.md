# springboot-kotlin-webflux-webclient
Template Project Springboot kotlin Coroutines API

### Base Project
- gradle (kotlin)
- kotlin
- webflux
- webclient


### CURL

#### Get IP
```
curl --location --request GET 'http://localhost:8080/host/get'
```


#### Update IP
```
curl --location --request POST 'http://localhost:8080/host/update' \
--header 'Content-Type: application/json' \
--data-raw '{
        "ip": "192.168.1.2",
    }'
```

## Build
```
./gradlew clean build
```


## Docker
```
docker build --tag=webclient:latest .
```
