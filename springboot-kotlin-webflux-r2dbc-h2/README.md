# springboot-kotlin-webflux-r2dbc-h2
Project Template Springboot kotlin Coroutines API

### Base Project
- gradle (kotlin)
- kotlin
- webflux
- spring-data-r2dbc
- h2 datbase


### CURL

#### Get All
```
curl --location --request GET 'localhost:8888/member/get'
```


#### Get By Id
```
curl --location --request GET 'localhost:8888/member/get/1'
```


#### Add
```
curl --location --request POST 'localhost:8888/member/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id":"2",
    "firstName":"firstName2",
    "lastName":"lastName2"
}'
```

#### Edit
```
curl --location --request PATCH 'localhost:8888/member/edit' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id":"2",
    "firstName":"firstName2edit",
    "lastName":"lastName2edit"
}'
```


### Delete
```
curl --location --request DELETE 'localhost:8888/member/delete/2' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id":"2",
    "firstName":"firstName2edit",
    "lastName":"lastName2edit"
}'
```
