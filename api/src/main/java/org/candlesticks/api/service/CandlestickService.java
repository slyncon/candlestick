package org.candlesticks.api.service;

import org.candlesticks.api.model.Candlestick;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandlestickService implements CandlestickManager{

    @Override
    public List<Candlestick> getCandleSticks(String isin) {
        return List.of();
    }

    // indices for SQL.
    // Replica db - only for fetching and reading, another DB for writing.

}
