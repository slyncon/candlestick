package org.candlesticks.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candlestick {
    private Instant openTimestamp;
    private Instant closeTimestamp;
    private Double openPrice;
    private Double highPrice;
    private Double lowPrice;
    private Double closingPrice;
}
