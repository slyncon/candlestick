package org.candlesticks.api.model;

public class Quote {
    private String isin;
    private Double price;

    public Quote(String isin, Double price) {
        this.isin = isin;
        this.price = price;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
