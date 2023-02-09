package org.candlesticks.api.model;

public class Instrument {
    private String isin;
    private String description;

    public Instrument(String isin, String description) {
        this.isin = isin;
        this.description = description;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
