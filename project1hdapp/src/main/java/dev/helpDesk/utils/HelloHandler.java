//package dev.helpDesk.utils;
//
//import com.sun.net.httpserver.HttpExchange;
//import com.sun.net.httpserver.HttpHandler;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.nio.charset.StandardCharsets;
//
//public class HelloHandler implements HttpHandler {
//
//    @Override
//    public void handle(HttpExchange exchange) throws IOException {
//
//        if (exchange.getRequestMethod().equals("GET")) {
//
//            String payload = "Hello, web!";
//            exchange.sendResponseHeaders(200, payload.length());
//
//            OutputStream outputStream =exchange.getResponseBody();
//            outputStream.write(payload.getBytes(StandardCharsets.UTF_8));
//            outputStream.flush();
//            outputStream.close();
//
//
//
//        } else {
//            exchange.sendResponseHeaders(405, 0);
//            exchange.getResponseBody().close();
//        }
//
//    }
//}