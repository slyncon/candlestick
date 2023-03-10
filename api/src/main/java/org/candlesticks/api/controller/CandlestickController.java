package org.candlesticks.api.controller;

import org.candlesticks.api.model.Candlestick;
import org.candlesticks.api.service.CandlestickService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/candlesticks")
public class CandlestickController {

    private final CandlestickService service;

    public CandlestickController(CandlestickService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Candlestick>> findByISIN(@RequestParam String isin)  {

        List<Candlestick> candleSticks = new ArrayList<>();

        try {
            candleSticks = service.getCandleSticks(isin);
        } catch (Exception e) {
            return new ResponseEntity<>(candleSticks, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(candleSticks, HttpStatus.OK);
    }

}
