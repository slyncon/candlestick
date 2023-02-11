package org.candlesticks.api.service;

import org.candlesticks.api.model.Candlestick;
import org.candlesticks.api.model.Quote;
import org.candlesticks.api.repository.InstrumentRepository;
import org.candlesticks.api.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandlestickService implements CandlestickManager{

    private final InstrumentRepository instrumentRepository;
    private final QuoteRepository quoteRepository;

    public CandlestickService(InstrumentRepository instrumentRepository, QuoteRepository quoteRepository) {
        this.instrumentRepository = instrumentRepository;
        this.quoteRepository = quoteRepository;
    }

    @Override
    public List<Candlestick> getCandleSticks(String isin) {

//        List<Quote> all = quoteRepository.findAll();
        List<Quote> quotes = quoteRepository.findByIsin(isin);

        return List.of();
    }

    public List<Candlestick> getCandlestickForThePastMinuteFromQuotes(List<Quote> quotes){
        return null;
    }

    // indices for SQL.
    // Replica db - only for fetching and reading, another DB for writing.

}
