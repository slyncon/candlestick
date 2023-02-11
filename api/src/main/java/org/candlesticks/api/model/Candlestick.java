package org.candlesticks.api.model;

import java.time.Instant;


public class Candlestick {
    private Instant openTimestamp;
    private Instant closeTimestamp;
    private Double openPrice;
    private Double highPrice;
    private Double lowPrice;
    private Double closePrice;

    public Candlestick(Instant openTimestamp, Instant closeTimestamp, Double openPrice, Double highPrice, Double lowPrice, Double closePrice) {
        this.openTimestamp = openTimestamp;
        this.closeTimestamp = closeTimestamp;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
    }

    public Candlestick(){}

    public Instant getOpenTimestamp() {
        return openTimestamp;
    }

    public Candlestick setOpenTimestamp(Instant openTimestamp) {
        this.openTimestamp = openTimestamp;
        return this;
    }

    public Instant getCloseTimestamp() {
        return closeTimestamp;
    }

    public Candlestick setCloseTimestamp(Instant closeTimestamp) {
        this.closeTimestamp = closeTimestamp;
        return this;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public Candlestick setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
        return this;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public Candlestick setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
        return this;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public Candlestick setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
        return this;
    }

    public Double getClosePrice() {
        return closePrice;
    }

    public Candlestick setClosePrice(Double closePrice) {
        this.closePrice = closePrice;
        return this;
    }
}
