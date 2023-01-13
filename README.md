# jwt-demo
Demonstrates Spring Boot configuration for using JWT

Pre-configured user:

Username: `priyank` 
Password: `pass`

Commandline usage:

Get the token

```export TOKEN=$(curl -X POST -u priyank:pass localhost:8080/token)```

Use token to authenticate

```curl -v -H 'Authorization: Bearer '$TOKEN'' localhost:8080/```
