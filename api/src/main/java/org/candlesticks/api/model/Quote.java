package org.candlesticks.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
public class Quote {

    @Id @GeneratedValue
    @JsonIgnore
    private Integer id;

    private String isin;
    private Double price;
    private Instant timestamp;

    @JsonIgnore
    @Transient
    private LocalDateTime timestampLocalDateTime;

    public Quote(){}

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestampLocalDateTime() {
        return timestampLocalDateTime;
    }

    public Quote setTimestampLocalDateTime(LocalDateTime timestampLocalDateTime) {
        this.timestampLocalDateTime = timestampLocalDateTime;
        return this;
    }
}
