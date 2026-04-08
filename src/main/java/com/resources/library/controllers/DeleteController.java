package com.resources.library.controllers;

import com.resources.library.models.Book;
import com.resources.library.services.BookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteController {
    @FXML
    private TextField txtSelectedIsbn;

    @FXML
    private TextField txtIsbnToDelete;

    @FXML
    private Button btnDelete;

    BookService bookService;
    private Book book;
    private String typedIsbn;

    public void setData(Book selectedBook, BookService bookService, String isbn){
        this.txtSelectedIsbn.setText(selectedBook.getIsbn());
        this.bookService = bookService;
        this.book = selectedBook;

        // binding para habilitar/deshabilitar botón
        btnDelete.disableProperty().bind(
                txtIsbnToDelete.textProperty().isNotEqualTo(isbn)
        );

    }

    @FXML
    public void onConfirmDelete(){
        bookService.deleteBook(book,txtIsbnToDelete.getText());
        Stage stage = (Stage) txtSelectedIsbn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void onCancelDelete(){
        Stage stage = (Stage) txtSelectedIsbn.getScene().getWindow();
        stage.close();
    }

    public void setData(BookService service, String isbn) {

    }

}
