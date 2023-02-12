package org.candlesticks.api.service;

import org.candlesticks.api.model.Candlestick;
import org.candlesticks.api.model.Quote;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

public class CandlestickPerMinute {

//    https://stackoverflow.com/questions/34438656/grouping-java-8-dates-in-intervals
    public static List<Candlestick> validate(List<Quote> quotes) {

        String isin = quotes.stream().findFirst().get().getIsin();

        List<Candlestick> candlesticks = new ArrayList<>();

        Collections.sort(quotes, Comparator.comparing(Quote::getTimestamp));

        quotes.forEach(element -> {
            element.setTimestampLocalDateTime(LocalDateTime.ofInstant(element.getTimestamp(), ZoneOffset.UTC));
        });

        Map<String, Map<Integer, List<Quote>>> collect = quotes.stream()
                .collect(Collectors.groupingBy(
                        Quote::getIsin,
                        Collectors.groupingBy(x -> x.getTimestampLocalDateTime().get(ChronoField.MINUTE_OF_DAY))));

        collect.get(isin).values().forEach(element -> {
            System.out.println(element);
        });

//        Candlestick candlestick = new Candlestick()
//                .setOpenTimestamp()
//                .setOpenPrice()
//                .setHighPrice()
//                .setLowPrice()
//                .setClosePrice()
//                .setCloseTimestamp();

        return candlesticks;
    }

}
