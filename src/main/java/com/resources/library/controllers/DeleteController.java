package com.resources.library.controllers;

import com.resources.library.models.Book;
import com.resources.library.services.BookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DeleteController {
    @FXML
    private Label lblSelectedIsbn;

    BookService bookService;
    private Book book;

    public void setData(Book selectedBook, BookService bookService){
        this.lblSelectedIsbn.setText(selectedBook.getIsbn());
        this.bookService = bookService;
        this.book = selectedBook;
    }

    @FXML
    public void onConfirmDelete(){
        bookService.deleteBook(book);
        Stage stage = (Stage) lblSelectedIsbn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onCancelDelete(){
        Stage stage = (Stage) lblSelectedIsbn.getScene().getWindow();
        stage.close();
    }
}
