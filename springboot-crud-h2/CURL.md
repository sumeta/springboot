# Postman cURL Examples

Use these cURL commands directly, or import into Postman via Import → Raw text → paste the command.

Base URL: `http://localhost:8080`

## Create User

```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice","email":"alice@example.com"}'
```

## List Users

```bash
curl http://localhost:8080/api/users
```

## Get User by ID

```bash
curl http://localhost:8080/api/users/1
```

## Update User

```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice Updated","email":"alice@example.com"}'
```

## Delete User

```bash
curl -X DELETE http://localhost:8080/api/users/1
```

