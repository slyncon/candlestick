package org.candlesticks.api.service;

import org.candlesticks.api.model.Candlestick;
import org.candlesticks.api.model.Quote;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CandlestickServiceTest {

    @Autowired
    public CandlestickService candlestickService;

    public static List<Quote> mockListQuotes(){
        Quote quote = new Quote();
        List<Quote> quotes = new ArrayList<>();

        quote.setIsin("AD2222323H61");
        quote.setPrice(13.00);
        quote.setTimestamp(Instant.parse("2019-03-05T13:00:32.577661+00:00"));

        quotes.add(quote);

        quote = new Quote();
        quote.setIsin("AD2222323H61");
        quote.setPrice(10.00);
        quote.setTimestamp(Instant.parse("2019-03-05T13:00:05.577661+00:00"));

        quotes.add(quote);

        quote = new Quote();
        quote.setIsin("AD2222323H61");
        quote.setPrice(150.00);
        quote.setTimestamp(Instant.parse("2019-03-05T12:59:18.577661+00:00"));

        quotes.add(quote);

        quote = new Quote();
        quote.setIsin("AD2222323H61");
        quote.setPrice(150.00);
        quote.setTimestamp(Instant.parse("2019-03-05T12:59:45.577661+00:00"));

        quotes.add(quote);

        quote = new Quote();
        quote.setIsin("AD2222323H61");
        quote.setPrice(12.00);
        quote.setTimestamp(Instant.parse("2019-03-05T13:00:57.577661+00:00"));

        quotes.add(quote);

        quote = new Quote();
        quote.setIsin("AD2222323H61");
        quote.setPrice(11.00);
        quote.setTimestamp(Instant.parse("2019-03-05T13:00:06.577661+00:00"));

        quotes.add(quote);

        quote = new Quote();
        quote.setIsin("AD2222323H61");
        quote.setPrice(11.00);
        quote.setTimestamp(Instant.parse("2019-03-05T13:00:19.577661+00:00"));

        quotes.add(quote);

        quote = new Quote();
        quote.setIsin("AD2222323H61");
        quote.setPrice(15.00);
        quote.setTimestamp(Instant.parse("2019-03-05T13:00:13.577661+00:00"));

        quotes.add(quote);

        quote = new Quote();
        quote.setIsin("AD2222323H61");
        quote.setPrice(12.00);
        quote.setTimestamp(Instant.parse("2019-03-05T13:00:49.577661+00:00"));

        quotes.add(quote);

        quote = new Quote();
        quote.setIsin("AD2222323H61");
        quote.setPrice(9.00);
        quote.setTimestamp(Instant.parse("2019-03-05T13:01:00.577661+00:00"));

        quotes.add(quote);

        return quotes;
    }

    @Test
    public void shouldRetrieveLastMinuteCandlestickForGivenQuotes(){

        List<Quote> quotes = mockListQuotes();
        Candlestick expectedCandlestick = new Candlestick()
            .setOpenTimestamp(Instant.parse("2019-03-05T13:00:00.000000+00:00"))
            .setOpenPrice(10.00)
            .setHighPrice(15.00)
            .setLowPrice(10.00)
            .setClosePrice(12.00)
            .setCloseTimestamp(Instant.parse("2019-03-05T13:01:00.000000+00:00"));

        CandlestickForMinute candlestickForMinute = new CandlestickForMinute();

        List<Candlestick> actualCandleSticks = candlestickForMinute.validate(quotes);

        /*
            @2019-03-05 13:00:05 price: 10
            @2019-03-05 13:00:06 price: 11
            @2019-03-05 13:00:13 price: 15
            @2019-03-05 13:00:19 price: 11
            @2019-03-05 13:00:32 price: 13
            @2019-03-05 13:00:49 price: 12
            @2019-03-05 13:00:57 price: 12
            @2019-03-05 13:01:00 price: 9

            openTimestamp: 2019-03-05 13:00:00
            openPrice: 10
            highPrice: 15
            lowPrice: 10
            closePrice: 12
            closeTimestamp: 13:01:00
        */
        assertEquals(expectedCandlestick, actualCandleSticks.get(0));

    }
}
