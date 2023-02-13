package org.candlesticks.api.controller;

import org.candlesticks.api.model.Candlestick;
import org.candlesticks.api.model.Quote;
import org.candlesticks.api.repository.QuoteRepository;
import org.candlesticks.api.service.CandlestickService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteRepository quoteRepository;

    public QuoteController(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @GetMapping
    public ResponseEntity<List<Candlestick>> findByISIN(@RequestParam String isin){
        List<Quote> quotes = quoteRepository.findByIsin(isin);
        return new ResponseEntity(quotes, HttpStatus.OK);
    }

}
