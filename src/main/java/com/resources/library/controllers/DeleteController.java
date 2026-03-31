package com.resources.library.controllers;

import com.resources.library.models.Book;
import com.resources.library.services.BookService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DeleteController {
    @FXML
    private Label lblSelectedIsbn;

    BookService bookService;

    public void setData(Book selectedBook, BookService bookService){
        this.lblSelectedIsbn.setText(selectedBook.getIsbn());
        this.bookService = bookService;
    }

    @FXML
    public void onConfirmDelete(){

    }

}
