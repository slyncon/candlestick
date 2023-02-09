package org.candlesticks.controller;

import lombok.AllArgsConstructor;
import org.candlesticks.model.Candlestick;
import org.candlesticks.service.CandlestickService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/candlesticks")
@AllArgsConstructor
public class CandlestickController {

    /*

    { "data": { "description": "pulvinar nulla eius", "isin": "YH31184324D8" }, "type": "ADD" }
    { "data": { "price": 1405.569, "isin": "YH31184324D8" }, "type": "QUOTE" }
    { "data": { "price": 1377.4267, "isin": "YH31184324D8" }, "type": "QUOTE" }
    { "data": { "price": 1423.2845, "isin": "YH31184324D8" }, "type": "QUOTE" }
    { "data": { "price": 1429.1422, "isin": "YH31184324D8" }, "type": "QUOTE" }

    * */

    private final CandlestickService service;

    @GetMapping
    public ResponseEntity<List<Candlestick>> findByISIN(@RequestParam String isin){

        List<Candlestick> candleSticks = service.getCandleSticks(isin);

        return new ResponseEntity(candleSticks, HttpStatus.OK);
    }

}
