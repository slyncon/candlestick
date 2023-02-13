# Lyncon Santos - Trade Republic Coding Challenge 

This is a web application that exposes a REST API. This application uses Maven as the build tool. The concept of multi-module maven projects were used.
You should see three projects:
- candlesticks (Parent project)
- api
- websockets


### Order for running

- docker-compose
- websockets
- api

Please run the docker-compose file first. That will create a local Postgres database using docker that will use port
`5432` as well as run the partner-service.jar

``` 
docker-compose up -d
```

After that, run the Spring Boot Main Class for the project **websockets** (port `8080` will be used) on your IDE of choice `org.candlesticks.WebsocketsApplication.java` or
with the following Maven Commands:

``` 
cd websockets  
./mvnw clean compile package
./mvnw spring-boot:run    
```


Lastly, run the Spring Boot Main Class for the project **api** (port  `9000` will be used) on your IDE of choice `org.candlesticks.api.ApiApplication.java` or
with the following Maven Commands:

``` 
cd api  
./mvnw clean compile package
./mvnw spring-boot:run    
```

That will expose the following endpoints:

The main endpoint which retrieves the candlesticks and its properties:
```
http://localhost:9000/candlesticks?isin={ISIN}
```
And additional quotes and instruments:
```
http://localhost:9000/quotes?isin={ISIN}
```
```
http://localhost:9000/instruments?isin={ISIN}
```

### Test Classes

Please check out the class `org.candlesticks.api.service.CandlestickServiceTest.java` for the Unit Test Cases

----
Thank you! :)


