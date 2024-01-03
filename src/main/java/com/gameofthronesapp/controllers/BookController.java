package com.gameofthronesapp.controllers;

import com.gameofthronesapp.connectivity.BooksAPIHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
    private final BooksAPIHandler apiHandler = new BooksAPIHandler();

    @RequestMapping("/books")
    public String getBookList(Model model) {
        model.addAttribute("booksList", apiHandler.listAllBooks());
        return "books";
    }

    @RequestMapping("/book")
    public String getSingleBook(Model model, @RequestParam Integer id) {
        model.addAttribute("book", apiHandler.getSingleBook(id));
        return "book";
    }
}
