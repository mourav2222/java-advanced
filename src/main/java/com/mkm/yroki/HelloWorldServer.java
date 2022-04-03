package com.mkm.yroki;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.io.IoCallback;
import io.undertow.io.Sender;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.OpenListener;
import io.undertow.server.handlers.Cookie;
import io.undertow.server.handlers.CookieImpl;
import io.undertow.server.handlers.PathHandler;
import io.undertow.server.handlers.form.EagerFormParsingHandler;
import io.undertow.server.handlers.form.FormData;
import io.undertow.server.handlers.form.FormDataParser;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.server.handlers.resource.ResourceHandler;
import io.undertow.util.AttachmentKey;
import io.undertow.util.Headers;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * Created by papa on 01.04.2022
 */

public class HelloWorldServer {

    static final ArrayDeque<String> emptyDeque = new ArrayDeque<>();

    static final AttachmentKey<String> StringID = AttachmentKey.create(String.class);
    private static Deque<String> EMPTY_DEQUE = new ArrayDeque<>(0);

    public static void main(String[] args) throws IOException {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_31);
        cfg.setClassLoaderForTemplateLoading(HelloWorldServer.class.getClassLoader(), "templates");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        cfg.setObjectWrapper(new BeansWrapper(Configuration.VERSION_2_3_30));

        final Template nameTemplate = cfg.getTemplate("name.xhtml");
        final Template quTemplate = cfg.getTemplate("question.xhtml");

        List<Question> questions = Arrays.asList(
                new Question("2 + 2", Arrays.asList("2", "5", "4"), 2),
                new Question("3 + 3", Arrays.asList("6", "8", "9"), 0),
                new Question("1 - 1", Arrays.asList("-1", "0", "1"), 1)
        );

        for (int i = 0; i < questions.size(); i++) {
            questions.get(i).setNum(i);

        }

        Undertow server = Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(Handlers.path()

                                .addPrefixPath("/html", new ResourceHandler(new ClassPathResourceManager(
                                        HelloWorldServer.class.getClassLoader(), "html")))

                                .addPrefixPath("/foo", new BlockingHandler(new HttpHandler() {

                                    @Override
                                    public void handleRequest(HttpServerExchange exchange) throws Exception {

                                        exchange.startBlocking();
                                        String name = exchange.getQueryParameters().getOrDefault("name", emptyDeque).poll();
                                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");

                                        try (OutputStreamWriter out = new OutputStreamWriter(exchange.getOutputStream(), "UTF-8")) {

                                            out.write("Hello ");
                                            out.write("foo222");
                                        }
                                        exchange.endExchange();
                                    }
                                }))
                                .addPrefixPath("/", exchange -> {
                                    String name = exchange.getQueryParameters().getOrDefault("name", EMPTY_DEQUE).poll();

//                            Optional<Deque<String>> opt = Optional.ofNullable(exchange.getQueryParameters().get());
//                            String name = opt.map(d -> d.peek()).orElse("default Name");

                                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                                    exchange.getResponseSender().send("Hello ", new IoCallback() {
                                        @Override
                                        public void onComplete(HttpServerExchange exchange, Sender sender) {
                                            exchange.getResponseSender().send(name);
                                        }

                                        @Override
                                        public void onException(HttpServerExchange exchange, Sender sender, IOException exception) {

                                        }
                                    });
                                })

                                .addExactPath("form", new EagerFormParsingHandler().setNext((exchange) -> {

                                    FormData form = exchange.getAttachment(FormDataParser.FORM_DATA);
                                    FormData.FormValue formValue = form.getFirst("name");
                                    String name = formValue.getValue();

//                            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
//                            exchange.getResponseSender().send("Hello " + name);

                                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
                                    HashMap<String, Object> templateData = new HashMap<>();
                                    templateData.put("name", name);

                                    StringWriter stringWriter = new StringWriter();
                                    nameTemplate.process(templateData, stringWriter);
                                    exchange.getResponseSender().send(stringWriter.toString());

                                }))
                                .addExactPath("answer", new EagerFormParsingHandler().setNext((exchange) -> {

                                    FormData form = exchange.getAttachment(FormDataParser.FORM_DATA);

                                    FormData.FormValue qValue = form.getFirst("q");
                                    String qfw = qValue.getValue();
                                    int q = Integer.parseInt(qfw);

                                    FormData.FormValue anValue = form.getFirst("answer");
                                    String answer = anValue.getValue();

                                    Question question = questions.get(q);

                                    boolean right = question.getAnswers().get(question.right).equals(answer);
                                    System.out.println("answer: " + answer + " right: " + right);

                                    int rightAnswers = 0;
                                    Cookie cookie = exchange.getRequestCookie("rightAnswers");
                                    if (cookie != null) {
                                        rightAnswers = Integer.parseInt(cookie.getValue());
                                    }
                                    if (right)
                                        rightAnswers += 1;

                                    if (q < questions.size() - 1) {
                                        exchange.setResponseCookie(new CookieImpl("rightAnswers").setValue(rightAnswers + ""));
                                        Handlers.redirect("qu?q=" + (q + 1)).handleRequest(exchange);
                                    } else {
                                        if (cookie != null) {
                                            exchange.setResponseCookie(cookie
                                                    .setValue("0")
                                                    .setMaxAge(0));
                                        }

                                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                                        exchange.getResponseSender().send("Right answers: " + rightAnswers + " from questions " + questions.size());
                                    }


                                }))

                                .addExactPath("qu", exchange -> {

                                    int q = 0;
                                    String qstring = exchange.getQueryParameters().getOrDefault("q", EMPTY_DEQUE).peek();
                                    if (qstring != null) {
                                        q = Integer.parseInt(qstring);
                                    }

                                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
                                    StringWriter stringWriter = new StringWriter();
                                    quTemplate.process(questions.get(q), stringWriter);
                                    exchange.getResponseSender().send(stringWriter.toString());
                                })

                                .addExactPath("hello", exchange -> {

                                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
                                    exchange.getResponseSender().send("Hello World");
                                })


                ).build();
        server.start();
    }

}


class BlockingHandler implements HttpHandler {

    HttpHandler next;

    public BlockingHandler(HttpHandler next) {
        this.next = next;
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {

        if (exchange.isInIoThread()) {
            exchange.dispatch(this);
            return;
        }

        next.handleRequest(exchange);

    }
}