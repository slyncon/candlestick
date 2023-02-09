package org.candlesticks.model;

public class QuoteEvent {
    private Quote data;

    public QuoteEvent(Quote data) {
        this.data = data;
    }

    public Quote getData() {
        return data;
    }

    public void setData(Quote data) {
        this.data = data;
    }
}
