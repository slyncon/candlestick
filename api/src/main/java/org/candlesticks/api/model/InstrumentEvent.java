package org.candlesticks.api.model;

public class InstrumentEvent {
    private Type type;
    private Instrument data;

    public static enum Type {
        ADD,
        DELETE;
    }

    public InstrumentEvent(Type type, Instrument data) {
        this.type = type;
        this.data = data;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Instrument getData() {
        return data;
    }

    public void setData(Instrument data) {
        this.data = data;
    }
}
