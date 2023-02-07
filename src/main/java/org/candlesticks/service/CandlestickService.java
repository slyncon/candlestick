package org.candlesticks.service;

import org.candlesticks.model.Candlestick;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandlestickService implements CandlestickManager{

    @Override
    public List<Candlestick> getCandleSticks(String isin) {
        return List.of(new Candlestick(), new Candlestick());
    }
}
