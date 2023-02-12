package org.candlesticks.api.service;

import org.candlesticks.api.model.Candlestick;
import org.candlesticks.api.model.Quote;
import org.candlesticks.api.repository.InstrumentRepository;
import org.candlesticks.api.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

        List<Quote> quotes = quoteRepository.findByIsin(isin);

        CandlestickNoLongerThan30MinutesFromNow.validate(quotes);
        List<Candlestick> candlesticks = CandlestickPerMinute.validate(quotes);

        return candlesticks;
    }

}
