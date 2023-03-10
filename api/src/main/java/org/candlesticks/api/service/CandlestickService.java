package org.candlesticks.api.service;

import org.candlesticks.api.model.Candlestick;
import org.candlesticks.api.model.Quote;
import org.candlesticks.api.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandlestickService implements CandlestickManager{

    private final QuoteRepository quoteRepository;

    public CandlestickService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public List<Candlestick> getCandleSticks(String isin) throws Exception {

        List<Quote> quotes = quoteRepository.findByIsin(isin);

        if (quotes.isEmpty()) {
            throw new Exception("No quotes found for that ISIN");
        }

        CandlestickNoLongerThan30MinutesFromNow.validate(quotes);
        List<Candlestick> candlesticks = CandlestickPerMinute.validate(quotes);

        return candlesticks;
    }

}
