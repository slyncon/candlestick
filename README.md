# Candlesticks Graph API for last 30 minutes quotes, grouped by 1 minute and read from websockets

This is a web application that exposes a REST API. This application uses Maven as the build tool. The concept of multi-module maven projects were used.
You should see three projects:
- candlesticks (Parent project)
- api
- websockets

## Introduction and terminology

#### Quotes and Instruments
Every tradeable asset is represented by a "instrument," which is identified by a specific number (ISIN).
An update message named "quote" is broadcast for this instrument every time the price of the instrument changes to let other users know about the change.

#### A andlestick?
A [candlestick](https://en.wikipedia.org/wiki/Candlestick_chart) is a visual representation of the price movement for a specific instrument over a predetermined period of time, often one minute.
For this task, we'll be employing a streamlined version of candlesticks.

![chart](https://www.investopedia.com/thmb/pWBTORzzifDoVLg_mw8NmvQKccg=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/UnderstandingBasicCandlestickCharts-01_2-4d7b49098a0e4515bbb0b8f62cc85d77.png)


The fundamental tenet is that we don't have to be aware of _all_ price changes that occur during a certain period of time.
Since we are more interested in a few critical data points inside each minute, we typically prefer them to be clustered into 1-minute intervals.
Theoretically, every quote that has a timestamp that is more than the openTimestamp and less than the closeTimestamp (`openTimestamp <= quoteTimestamp < closeTimestamp`).
Nevertheless, we just show the user the following information for each candle for each minute:
- The price of the initial quote that was received (openPrice)
- the latest quotes that were submitted (closePrice)
- The recorded highest quote price (highPrice)
- The recorded lowest quote price (lowPrice)
- the time the candlestick was opened, in seconds (openTimestamp)
  At the instant the candlestick was closed, in timestamp (closeTimestamp)

##### Example
Assuming that an instrument got the (simplified) data below:
```
2020-11-25 09:00:04 price: 7
2020-11-25 09:00:06 price: 8
2020-11-25 09:00:16 price: 8
2020-11-25 09:00:23 price: 10
2020-11-25 09:00:31 price: 5
2020-11-25 09:00:46 price: 6
2020-11-25 09:00:52 price: 8
2020-11-25 09:01:00 price: 12
```
The qualities of the produced minute candle would be:
```
openTimestamp: 2020-11-25 09:00:00
openPrice: 7
highPrice: 10
lowPrice: 5
closePrice: 8
closeTimestamp: 09:01:00
```

Notice the latest recent quote you received, with a price of 12, no longer belongs to this candlestick and is now a member of the new candlestick.

### History of Aggregated Prices - Output
The aggregated price history endpoint is the primary objective.
For any specified instrument, it must produce a 30-minute quotes history presented as minute candlesticks (see details below).

Customers of the service are interested in the price history of a certain instrument, and they want information in an accessible format. Therefore, candlesticks
must be provided.
The user must enter the instrument id (ISIN) as a query parameter in order to obtain the candlesticks (e.g. `http://localhost:9000/candlesticks?isin={ISIN}`).

The system just needs to return the candlesticks and most recent prices for the previous 30 minutes.
Instead of missing candlesticks in the 30-minute window if no quotes were received for more than a minute, values from the prior candle are used.

# Running order

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

