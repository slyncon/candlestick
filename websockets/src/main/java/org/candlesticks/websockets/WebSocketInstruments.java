package org.candlesticks.websockets;

import com.google.gson.Gson;
import org.candlesticks.dto.InstrumentDTO;
import org.candlesticks.model.Instrument;
import org.atmosphere.wasync.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.candlesticks.repository.InstrumentRepository;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.time.Instant;

@Component
public class WebSocketInstruments implements CommandLineRunner {

    private static final String WS_INSTRUMENTS_URI = "ws://localhost:8032/instruments";
    private final InstrumentRepository instrumentRepository;
    private final Client client;

    public WebSocketInstruments(InstrumentRepository instrumentRepository, Client client) {
        this.instrumentRepository = instrumentRepository;
        this.client = client;
    }

    @Override
    public void run(String... args) throws Exception {
        RequestBuilder instrumentsRequest = client.newRequestBuilder()
                .method(Request.METHOD.GET)
                .uri(WS_INSTRUMENTS_URI)
                .encoder(new Encoder<String, Reader>() {        // Stream the request body
                    @Override
                    public Reader encode(String s) {
                        return new StringReader(s);
                    }
                })
                .decoder(new Decoder<String, Reader>() {
                    @Override
                    public Reader decode(Event type, String s) {
                        return new StringReader(s);
                    }
                })
                .transport(Request.TRANSPORT.WEBSOCKET)                        // Try WebSocket
                .transport(Request.TRANSPORT.LONG_POLLING);                    // Fallback to Long-Polling


        Socket socket = client.create();
        socket.on(new Function<Reader>() {
                    @Override
                    public void on(Reader r) {
                        try {
                            if (r.ready()) {

                                char[] array = new char[150];
                                int read = r.read(array);
                                String stringEvent = String.valueOf(array).trim();

                                Gson gson = new Gson();
                                InstrumentDTO instrumentDTO = gson.fromJson(stringEvent, InstrumentDTO.class);

                                Instrument instrument = new Instrument();
                                instrument.setDescription(instrumentDTO.getData().getDescription());
                                instrument.setIsin(instrumentDTO.getData().getIsin());
                                instrument.setType(instrumentDTO.getType().name());
                                instrument.setTimestamp(Instant.now());

                                instrumentRepository.save(instrument);

                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }).on(new Function<IOException>() {
                    @Override
                    public void on(IOException ioe) {
                        // Some IOException occurred
                    }

                }).open(instrumentsRequest.build())
                .fire("echo")
                .fire("bong");

    }
}
