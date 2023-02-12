package org.candlesticks.api.service;

import org.candlesticks.api.model.Quote;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class CandlestickNoLongerThan30MinutesFromNow {

    protected CandlestickNoLongerThan30MinutesFromNow(){}

    public static List<Quote> validate(List<Quote> quotes) {

        List<Quote> quotesForThePast30Minutes = new ArrayList<>();

        quotes.forEach(element -> {

            Instant thirtyMinutesBeforeNow = Instant.now().minus(30, ChronoUnit.MINUTES);

            if (! element.getTimestamp().isBefore(thirtyMinutesBeforeNow)) {
                quotesForThePast30Minutes.add(element);
            }

        });

        return quotesForThePast30Minutes;
    }

}
