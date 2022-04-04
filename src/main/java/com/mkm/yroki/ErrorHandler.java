package com.mkm.yroki;

import io.undertow.io.Sender;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class ErrorHandler implements HttpHandler {

    private final HttpHandler next;

    public ErrorHandler(HttpHandler next) {
        this.next = next;
    }

    @Override
    public void handleRequest(final HttpServerExchange exchange) throws Exception {
        try {
            next.handleRequest(exchange);
        } catch (Exception e) {
            if(exchange.isResponseChannelAvailable()) {
                if (exchange.getStatusCode() == 500) {

                    final String errorPage = "<html><head><title>Error</title></head><body>Internal Error </br>"
                            + e.getStackTrace() + "</body></html>";

                    exchange.getResponseHeaders().put(Headers.CONTENT_LENGTH, "" + errorPage.length());
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
                    Sender sender = exchange.getResponseSender();
                    sender.send(errorPage);

                }
            }
        }
    }
}