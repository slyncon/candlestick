package org.candlesticks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstrumentEvent {
    private Type type;
    private Instrument data;

    public static enum Type {
        ADD,
        DELETE;
    }
}
