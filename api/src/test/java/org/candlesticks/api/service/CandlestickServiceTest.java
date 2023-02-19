package org.candlesticks.api.service;

import org.candlesticks.api.model.Candlestick;
import org.candlesticks.api.model.Quote;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class CandlestickServiceTest {

    private static final Instant TIMESTAMP_BEFORE_30_MIN = Instant.parse("2019-03-04T08:00:18.577661+00:00");

    public static List<Quote> mockListQuotesWithLessThan30Minutes(){

        Quote quote = new Quote();
        List<Quote> quotes = new ArrayList<>();

        quote.setIsin("AD2222323H61");
        quote.setPrice(13.00);
        quote.setTimestamp(Instant.now().plus(5, ChronoUnit.SECONDS));

        quotes.add(quote);

        quote = new Quote();
        quote.setIsin("WRONG");
        quote.setPrice(9999.00);
        quote.setTimestamp(TIMESTAMP_BEFORE_30_MIN);

        quotes.add(quote);

        quote = new Quote();
        quote.setIsin("AD2222323H61");
        quote.setPrice(150.00);
        quote.setTimestamp(Instant.now().plus(5, ChronoUnit.SECONDS));

        quotes.add(quote);

        return quotes;
    }

    public static List<Quote> mockSmallQuoteForOneCandlestick(){
        Quote quote = new Quote();
        List<Quote> quotes = new ArrayList<>();

//        @2019-03-05 13:00:05 price: 10
//        @2019-03-05 13:00:06 price: 11
//        @2019-03-05 13:00:13 price: 15
//        @2019-03-05 13:00:19 price: 11
//        @2019-03-05 13:00:32 price: 13
//        @2019-03-05 13:00:49 price: 12
//        @2019-03-05 13:00:57 price: 12
//        @2019-03-05 13:01:00 price: 9

        quote.setIsin("AD2222323H61");
        quote.setPrice(10.00);
        quote.setTimestamp(Instant.parse("2019-03-05T13:00:05.577661+00:00"));

        quotes.add(quote);

        quote = new Quote();
        quote.setIsin("AD2222323H61");
        quote.setPrice(11.00);
        quote.setTimestamp(Instant.parse("2019-03-05T13:00:06.577661+00:00"));

        quotes.add(quote);

        quote = new Quote();
        quote.setIsin("AD2222323H61");
        quote.setPrice(15.00);
        quote.setTimestamp(Instant.parse("2019-03-05T13:00:13.577661+00:00"));

        quotes.add(quote);

        quote = new Quote();
        quote.setIsin("AD2222323H61");
        quote.setPrice(12.00);
        quote.setTimestamp(Instant.parse("2019-03-05T13:00:57.577661+00:00"));

        quotes.add(quote);

        quote = new Quote();
        quote.setIsin("AD2222323H61");
        quote.setPrice(11.00);
        quote.setTimestamp(Instant.parse("2019-03-05T13:00:19.577661+00:00"));

        quotes.add(quote);

        quote = new Quote();
        quote.setIsin("AD2222323H61");
        quote.setPrice(13.00);
        quote.setTimestamp(Instant.parse("2019-03-05T13:00:32.577661+00:00"));

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
    public void shouldRemoveQuotesBefore30MinutesFromNow(){

        List<Quote> wrongQuotes = mockListQuotesWithLessThan30Minutes();

        List<Quote> actualQuotes = CandlestickNoLongerThan30MinutesFromNow.validate(wrongQuotes);

        boolean timestampBefore30Min = actualQuotes.stream().
                anyMatch(quote -> quote.getTimestamp().equals(TIMESTAMP_BEFORE_30_MIN));

        assertFalse(timestampBefore30Min);
    }

    @Test
    public void shouldRetrieveAMinuteCanclestick(){

        List<Quote> quotes = mockSmallQuoteForOneCandlestick();

        Candlestick expectedCandlestick = new Candlestick()
                .setOpenTimestamp(Instant.parse("2019-03-05T13:00:00.000000+00:00"))
                .setOpenPrice(10.00)
                .setHighPrice(15.00)
                .setLowPrice(10.00)
                .setClosePrice(12.00)
                .setCloseTimestamp(Instant.parse("2019-03-05T13:01:00.000000+00:00"));

        Candlestick actualCandlestick = CandlestickPerMinute.validate(quotes).stream().findFirst().get();

        assertEquals(actualCandlestick.getOpenTimestamp(), expectedCandlestick.getOpenTimestamp() );
        assertEquals(actualCandlestick.getOpenPrice(), expectedCandlestick.getOpenPrice());
        assertEquals(actualCandlestick.getHighPrice(), expectedCandlestick.getHighPrice());
        assertEquals(actualCandlestick.getLowPrice(), expectedCandlestick.getLowPrice());
        assertEquals(actualCandlestick.getClosePrice(), expectedCandlestick.getClosePrice());
        assertEquals(actualCandlestick.getCloseTimestamp(), expectedCandlestick.getCloseTimestamp());
    }

    @Test
    public void shouldThrowExceptionWhenIsinIsNotFound(){

        List<Quote> wrongQuotes = mockListQuotesWithLessThan30Minutes();

        List<Quote> actualQuotes = CandlestickNoLongerThan30MinutesFromNow.validate(wrongQuotes);

        boolean timestampBefore30Min = actualQuotes.stream().
                anyMatch(quote -> quote.getTimestamp().equals(TIMESTAMP_BEFORE_30_MIN));

        assertFalse(timestampBefore30Min);
    }

    @Test
    public void shouldThrowExceptionWhenNoIsinIsProvided(){
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            CandlestickPerMinute.validate(new ArrayList<Quote>());
        });
    }

}
