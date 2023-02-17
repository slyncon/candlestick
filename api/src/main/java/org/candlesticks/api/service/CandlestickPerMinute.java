package org.candlesticks.api.service;

import org.candlesticks.api.model.Candlestick;
import org.candlesticks.api.model.Quote;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;

public class CandlestickPerMinute {

    public static List<Candlestick> validate(List<Quote> quotes) {

        List<Candlestick> candlesticks = new ArrayList<>();

        String isin = quotes.stream()
                .findFirst()
                .map(Quote::getIsin)
                .orElseThrow();

        List<Quote> orderedQuotes = quotes.stream()
                .map(element -> element.setTimestampLocalDateTime(LocalDateTime.ofInstant(element.getTimestamp(), ZoneOffset.UTC)))
                .sorted(comparing(Quote::getTimestamp))
                .toList();

        Map<String, Map<Integer, List<Quote>>> quotesMapGroupedByMinute = orderedQuotes.stream()
                .collect(Collectors.groupingBy(
                        Quote::getIsin,
                        Collectors.groupingBy(x -> x.getTimestampLocalDateTime().get(ChronoField.MINUTE_OF_DAY))));

        quotesMapGroupedByMinute.get(isin).values().stream().forEach(list -> {

            Quote firstQuote = list.stream().findFirst().get();
            Instant openTimestamp = firstQuote.getTimestamp().truncatedTo(ChronoUnit.MINUTES);
            Double openPrice = firstQuote.getPrice();

            Double highPrice = Collections.max(list, Comparator.comparing(Quote::getPrice)).getPrice();
            Double lowPrice = Collections.min(list, Comparator.comparing(Quote::getPrice)).getPrice();

            Quote lastQuote = list.stream().reduce((prev, next) -> next).orElse(null);
            Double closePrice = lastQuote.getPrice();
            Instant closeTimestamp = lastQuote.getTimestamp().truncatedTo(ChronoUnit.MINUTES).plusSeconds(60);

            Candlestick candlestick = new Candlestick()
                    .setOpenTimestamp(openTimestamp)
                    .setOpenPrice(openPrice)
                    .setHighPrice(highPrice)
                    .setLowPrice(lowPrice)
                    .setClosePrice(closePrice)
                    .setCloseTimestamp(closeTimestamp)
                    ;

            candlesticks.add(candlestick);

        });

        return candlesticks;
    }

}
