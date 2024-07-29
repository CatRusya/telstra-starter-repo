# Telstra Task from 

# Overview:
This project is a microservice built with Java 11, designed to manage SIM card activations through RESTful APIs. It interfaces with another service, the SimCardActuator, to perform the actual activation process and stores transaction details in a H2 database.

# REST Controller:
Forwards requests to the SimCardActuator and returns activation status. 
POST request to `/activate-sim`:

Request Body:
```
{
"iccid": "string",
"customerEmail": "string"
}
```

GET request to `/sim-card`:

Response:
```
{
"iccid": "string",
"customerEmail": "string",
"active": "boolean"
}
```

# SimCardActuator:

Endpoint: http://localhost:8444/actuate

Request Body:

```
{
  "iccid": "string"
}
```
Response:
```
{
  "success": "boolean"
}
```

# Environment:

- Java 11
- Spring Boot
- H2 database

