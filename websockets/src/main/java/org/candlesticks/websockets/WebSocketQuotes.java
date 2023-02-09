package org.candlesticks.websockets;

import com.google.gson.Gson;
import org.atmosphere.wasync.*;
import org.candlesticks.dto.QuoteDTO;
import org.candlesticks.model.Quote;
import org.candlesticks.repository.QuoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.time.Instant;

@Component
public class WebSocketQuotes implements CommandLineRunner {

    private static final String WS_QUOTES_URI = "ws://localhost:8032/quotes";
    private final QuoteRepository quoteRepository;
    private final Client client;

    public WebSocketQuotes(QuoteRepository quoteRepository, Client client) {
        this.quoteRepository = quoteRepository;
        this.client = client;
    }

    @Override
    public void run(String... args) throws Exception {
        RequestBuilder instrumentsRequest = client.newRequestBuilder()
                .method(Request.METHOD.GET)
                .uri(WS_QUOTES_URI)
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
                                QuoteDTO quoteDTO = gson.fromJson(stringEvent, QuoteDTO.class);

                                Quote quote = new Quote();
                                quote.setPrice(quoteDTO.getData().getPrice());
                                quote.setIsin(quoteDTO.getData().getIsin());
                                quote.setTimestamp(Instant.now());

                                quoteRepository.save(quote);

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
