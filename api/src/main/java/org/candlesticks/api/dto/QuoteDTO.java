package org.candlesticks.api.dto;

public class QuoteDTO {

    private QuoteEventDTO data;
    private Type type;

    public static enum Type {
        QUOTE,
        DELETE;
    }

    public QuoteEventDTO getData() {
        return data;
    }

    public void setData(QuoteEventDTO data) {
        this.data = data;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
