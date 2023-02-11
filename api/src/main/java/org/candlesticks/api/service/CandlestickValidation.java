package org.candlesticks.api.service;

import org.candlesticks.api.model.Candlestick;
import org.candlesticks.api.model.Quote;

import java.util.List;

public interface CandlestickValidation {
    List<Candlestick> validate(List<Quote> quotes);
}
