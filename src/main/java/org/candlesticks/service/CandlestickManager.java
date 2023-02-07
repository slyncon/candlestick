package org.candlesticks.service;

import org.candlesticks.model.Candlestick;

import java.util.List;

public interface CandlestickManager {
    List<Candlestick> getCandleSticks(String isin);
}
