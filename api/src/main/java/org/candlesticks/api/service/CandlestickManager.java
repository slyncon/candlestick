package org.candlesticks.api.service;

import org.candlesticks.api.model.Candlestick;

import java.util.List;

public interface CandlestickManager {
    List<Candlestick> getCandleSticks(String isin) throws Exception;
}
