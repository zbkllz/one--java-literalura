package com.challenge.literAlura.service;

import com.challenge.literAlura.literalura.MainEntry;
import com.challenge.literAlura.literalura.ScreenReset;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BookService {
    public String getResponseBody(String url) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            ScreenReset.clear();
            System.out.println(e.getMessage());
            MainEntry.setUserInput("");
        }

        String responseBody = response != null ? response.body() : null;
        return responseBody;
    }
}