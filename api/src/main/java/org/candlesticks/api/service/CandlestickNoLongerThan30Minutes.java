package org.candlesticks.api.service;

import org.candlesticks.api.model.Quote;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class CandlestickNoLongerThan30Minutes {

//      https://stackoverflow.com/questions/52623282/how-to-get-30-min-back-instance-object-instance-now-30-min

    public List<Quote> quotesNoLessThan30MinutesFromNow(List<Quote> quotes) {

        List<Quote> quotesForThePast30Minutes = new ArrayList<>();

        Instant now = Instant.now();

        quotes.forEach(element -> {

            Instant thirtyMinutesBeforeNow = now.minus(30, ChronoUnit.MINUTES);

            if (! element.getTimestamp().isBefore(thirtyMinutesBeforeNow)) {
                quotesForThePast30Minutes.add(element);
            }

        });

        return quotesForThePast30Minutes;

    }

}
