package org.candlesticks.websockets;

import org.atmosphere.wasync.Client;
import org.atmosphere.wasync.ClientFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.candlesticks.repository.InstrumentRepository;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"org.candlesticks"})
public class WebsocketsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketsApplication.class, args);
    }

    @Bean
    Client client() { return ClientFactory.getDefault().newClient(); }

}


