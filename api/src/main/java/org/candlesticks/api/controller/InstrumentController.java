package org.candlesticks.api.controller;

import org.candlesticks.api.model.Candlestick;
import org.candlesticks.api.model.Instrument;
import org.candlesticks.api.model.Quote;
import org.candlesticks.api.repository.InstrumentRepository;
import org.candlesticks.api.repository.QuoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/instruments")
public class InstrumentController {

    private final InstrumentRepository instrumentRepository;

    public InstrumentController(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    @GetMapping
    public ResponseEntity<List<Candlestick>> findByISIN(@RequestParam String isin){
        List<Instrument> instruments = instrumentRepository.findByIsin(isin);
        return new ResponseEntity(instruments, HttpStatus.OK);
    }

}
