package com.gameofthronesapp.connectivity;


import com.gameofthronesapp.datamodel.Book;
import com.gameofthronesapp.mappers.BookMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;

public class BooksAPIHandler {
    private final HttpClient client = HttpClient.newBuilder().build();
    private final String BOOKS_API_URL = "https://www.anapioficeandfire.com/api/books";

    public Book getSingleBook(int bookNumber) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BOOKS_API_URL + "/" + bookNumber))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return BookMapper.mapSingleBook(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Book> listAllBooks() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BOOKS_API_URL))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return BookMapper.mapBookList(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return Collections.emptyList();
    }
}
