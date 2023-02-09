package org.candlesticks.model;

import java.time.Instant;


public class Candlestick {
    private Instant openTimestamp;
    private Instant closeTimestamp;
    private Double openPrice;
    private Double highPrice;
    private Double lowPrice;
    private Double closingPrice;

    public Candlestick(Instant openTimestamp, Instant closeTimestamp, Double openPrice, Double highPrice, Double lowPrice, Double closingPrice) {
        this.openTimestamp = openTimestamp;
        this.closeTimestamp = closeTimestamp;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closingPrice = closingPrice;
    }

    public Instant getOpenTimestamp() {
        return openTimestamp;
    }

    public void setOpenTimestamp(Instant openTimestamp) {
        this.openTimestamp = openTimestamp;
    }

    public Instant getCloseTimestamp() {
        return closeTimestamp;
    }

    public void setCloseTimestamp(Instant closeTimestamp) {
        this.closeTimestamp = closeTimestamp;
    }

    public Double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Double openPrice) {
        this.openPrice = openPrice;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Double getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(Double closingPrice) {
        this.closingPrice = closingPrice;
    }
}
