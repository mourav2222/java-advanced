package com.mkm.yroki;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.form.EagerFormParsingHandler;
import io.undertow.server.handlers.form.FormData;
import io.undertow.server.handlers.form.FormDataParser;

public class MyEagerFormParsingHandler extends EagerFormParsingHandler {


    public MyEagerFormParsingHandler(HttpHandler next) {
        super(next);
    }

    public MyEagerFormParsingHandler() {
    }

    public static String getFormStringValue(String name, HttpServerExchange exchange) {

        FormData form = exchange.getAttachment(FormDataParser.FORM_DATA);

        FormData.FormValue strValue = form.getFirst(name);
        String strResult = strValue.getValue();
        return strResult;
    }


    public static int getFormIntValue(String name, HttpServerExchange exchange) {

        String strResult = getFormStringValue(name, exchange);
        int intResult = Integer.parseInt(strResult);
        return intResult;
    }


}
