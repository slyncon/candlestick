package org.candlesticks.api.service;

import org.candlesticks.api.model.Candlestick;
import org.candlesticks.api.model.Quote;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CandlestickServiceTest {

    public static List<Quote> mockListQuotesWithLessThan30Minutes(){
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
        quote.setPrice(330.00);
        quote.setTimestamp(Instant.parse("2019-03-04T08:00:18.577661+00:00"));

        quotes.add(quote);

        quote = new Quote();
        quote.setIsin("AD2222323H61");
        quote.setPrice(330.00);
        quote.setTimestamp(Instant.parse("2019-02-22T09:00:18.577661+00:00"));

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

    public static List<Quote> mockListQuotesForAMinute(){
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

        List<Quote> quotes = mockListQuotesWithLessThan30Minutes();
        Candlestick expectedCandlestick = new Candlestick()
            .setOpenTimestamp(Instant.parse("2019-03-05T13:00:00.000000+00:00"))
            .setOpenPrice(10.00)
            .setHighPrice(15.00)
            .setLowPrice(10.00)
            .setClosePrice(12.00)
            .setCloseTimestamp(Instant.parse("2019-03-05T13:01:00.000000+00:00"));

        CandlestickPerMinute candlestickPerMinute = new CandlestickPerMinute();

        List<Candlestick> actualCandleSticks = CandlestickPerMinute.validate(quotes);

        assertEquals(expectedCandlestick, actualCandleSticks.get(0));

    }

    @Test
    public void shouldRemoveQuotesBefore30MinutesFromNow(){

        List<Quote> expectedQuotes = mockListQuotesForAMinute();
        List<Quote> wrongQuotes = mockListQuotesWithLessThan30Minutes();

        CandlestickNoLongerThan30MinutesFromNow candlestickForMinute = new CandlestickNoLongerThan30MinutesFromNow();

        List<Quote> actualQuotes = candlestickForMinute.validate(wrongQuotes);

        assertEquals(expectedQuotes, actualQuotes);

    }
}
