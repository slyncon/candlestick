package org.candlesticks.api.service;

import org.candlesticks.api.model.Candlestick;
import org.candlesticks.api.model.Quote;
import org.springframework.cglib.core.Local;

import java.time.*;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

public class CandlestickPerMinute {

//    https://stackoverflow.com/questions/34438656/grouping-java-8-dates-in-intervals
    public static List<Candlestick> validate(List<Quote> quotes) {

        List<Candlestick> candlesticks = new ArrayList<>();


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

        timestampsPerMinute.entrySet().forEach(element -> {

            Instant openTimestamp = element.getValue().stream().findFirst().get().toInstant(ZoneOffset.UTC);

//        Candlestick candlestick = new Candlestick()
//                .setOpenTimestamp()
//                .setOpenPrice()
//                .setHighPrice()
//                .setLowPrice()
//                .setClosePrice()
//                .setCloseTimestamp();

        });


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
