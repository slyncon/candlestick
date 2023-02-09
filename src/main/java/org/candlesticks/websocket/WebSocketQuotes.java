package org.candlesticks.websocket;

import org.atmosphere.wasync.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

@Component
public class WebSocketQuotes implements CommandLineRunner {

    private static final String WS_QUOTES = "ws://localhost:8032/quotes";

    @Override
    public void run(String... args) throws Exception {
        Client client = ClientFactory.getDefault().newClient();

        RequestBuilder quotesRequest = client.newRequestBuilder()
                .method(Request.METHOD.GET)
                .uri(WS_QUOTES)
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
                .transport(Request.TRANSPORT.LONG_POLLING);

        Socket socket = client.create();
        socket.on(new Function<Reader>() {
                    @Override
                    public void on(Reader r) {
                        char[] array = new char[1000];

                        try {
                            int read = r.read(array);
                            System.out.println(array);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }).on(new Function<IOException>() {
                    @Override
                    public void on(IOException ioe) {
                        // Some IOException occurred
                    }

                }).open(quotesRequest.build())
                .fire("echo")
                .fire("bong");
    }
}
