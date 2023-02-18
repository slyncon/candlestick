# Candlesticks Graph API published by websockets

This is a web application that exposes a REST API. This application uses Maven as the build tool. The concept of multi-module maven projects were used.
You should see three projects:
- candlesticks (Parent project)
- api
- websockets

## Intro and terminology

#### Instruments and Quotes
Every asset that can be traded is represented by an “instrument”, which has a unique identifier (ISIN).
Each time the instrument price changes, an update message called “quote” is broadcasted for this instrument to inform about the change.

#### What is a candlestick?
A [candlestick](https://en.wikipedia.org/wiki/Candlestick_chart) is a representation that describes the price movement for a given instrument in a fixed amount of time, usually one minute.
We will be using a simplified version of candlesticks for this challenge.

![chart](https://t4.ftcdn.net/jpg/02/79/65/79/360_F_279657943_FALhJZ6g4shXyfqMIRifp1l6lhiwhbwm.jpg)

The basic idea is that we don't need to know about _all_ prices changes within a given timeframe.
Usually we want them grouped in 1 minute chunks, because we are more interested in some key data points within any given minute.
In theory, a candlestick “contains” all quotes, where the timestamp of the quote is higher than the openTimestamp and lower than the closeTimestamp (`openTimestamp <= quoteTimestamp < closeTimestamp`).
However, for each candle for each given minute, we only present the following data points to the user:
- the first quotes price, that was received (openPrice)
- the last quotes, that was received (closePrice)
- the highest quote price that was observed (highPrice)
- the lowest quote price that was observed (lowPrice)
- the timestamp when the candlestick was opened (openTimestamp)
- the timestamp when the candlestick was closed (closeTimestamp)

##### Example
Assume the following (simplified) data was received for an instrument:
```
@2019-03-05 13:00:05 price: 10
@2019-03-05 13:00:06 price: 11
@2019-03-05 13:00:13 price: 15
@2019-03-05 13:00:19 price: 11
@2019-03-05 13:00:32 price: 13
@2019-03-05 13:00:49 price: 12
@2019-03-05 13:00:57 price: 12
@2019-03-05 13:01:00 price: 9
```
The resulting minute candle would have these attributes:
```
openTimestamp: 2019-03-05 13:00:00
openPrice: 10
highPrice: 15
lowPrice: 10
closePrice: 12
closeTimestamp: 13:01:00
```
Note that the last received quote with a price of 9 is not part of this candlestick anymore, but belongs to the new candlestick.

### Output (Aggregated-Price History)
The output (and the main goal with this project) is the aggregated price history endpoint.
It should provide a 30 minutes quotes history in the form of minute candlesticks (check information below) for any requested instrument.

End-users of the service are interested in a specific instruments price history, and they want it in a format that is easy to read.
Hence we should provide candlesticks.
To get these candlesticks, the user needs to provide the instrument id (ISIN) as a query parameter (e.g. `http://localhost:9000/candlesticks?isin={ISIN}`).

The system only needs to return the candlesticks for the last 30 minutes, including the most recent prices.
If there weren't any quotes received for more than a minute, instead of missing candlesticks in the 30 minute window, values from the previous candle are reused.

# Order for running

- docker-compose
- websockets
- api

Run the docker-compose file first. That will create a local Postgres database using docker that will use port
`5432` as well as run the websocket.jar

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

