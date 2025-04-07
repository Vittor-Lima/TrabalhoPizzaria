package com.pizzaria.endpoint;

import com.pizzaria.service.PizzariaService;
import jakarta.xml.ws.Endpoint;

public class PizzariaPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/pizzaria", new PizzariaService());
        System.out.println("Serviço SOAP publicado em: http://localhost:8080/pizzaria?wsdl");
    }
}