package org.candlesticks.api.service;

import org.candlesticks.api.model.Candlestick;
import org.candlesticks.api.model.Quote;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CandlestickForMinute implements CandlestickValidation{

//    https://stackoverflow.com/questions/34438656/grouping-java-8-dates-in-intervals
    @Override
    public List<Candlestick> validate(List<Quote> quotes) {

//        Set<Quote> quoteSetOrderedByTimestamp = new TreeSet<Quote>(new QuoteTimestampComparator());
//        quoteSetOrderedByTimestamp.addAll(quotes);
//
//        Set<Quote> quoteSetByPrice = new TreeSet<Quote>(new QuotePriceComparator());
//        quoteSetByPrice.addAll(quotes);

//        Collections.sort(quotes, new QuoteTimestampComparator());
        Collections.sort(quotes, Comparator.comparing(Quote::getTimestamp));

        List<LocalDateTime> timestamps = new ArrayList<>();

        quotes.forEach(element -> {
            timestamps.add(LocalDateTime.ofInstant(element.getTimestamp(), ZoneOffset.UTC));
        });

        Map<Integer, List<LocalDateTime>> timestampsPerMinute =
                timestamps.stream()
                        .collect(Collectors.groupingBy(x -> x.get(ChronoField.MINUTE_OF_DAY) / 1 ));

        /*
            openTimestamp: 2019-03-05 13:00:00
            openPrice: 10
            highPrice: 15
            lowPrice: 10
            closePrice: 12
            closeTimestamp: 13:01:00

            @2019-03-05 13:00:05 price: 10
            @2019-03-05 13:00:06 price: 11
            @2019-03-05 13:00:13 price: 15
            @2019-03-05 13:00:19 price: 11
            @2019-03-05 13:00:32 price: 13
            @2019-03-05 13:00:49 price: 12
            @2019-03-05 13:00:57 price: 12
            @2019-03-05 13:01:00 price: 9


        */

        return null;
    }

}
