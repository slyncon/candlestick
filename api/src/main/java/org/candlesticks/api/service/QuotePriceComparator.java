package org.candlesticks.api.service;

import org.candlesticks.api.model.Quote;

import java.util.Comparator;

public class QuotePriceComparator implements Comparator<Quote> {
    @Override
    public int compare(Quote quote1, Quote quote2) {
        return quote1.getPrice().compareTo(quote2.getPrice());
    }
}
