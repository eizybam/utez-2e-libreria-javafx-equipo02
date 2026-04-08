package com.resources.library.controllers;

import com.resources.library.models.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DetailsController {

    @FXML
    private Label lblIsbn;
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblAuthor;
    @FXML
    private Label lblYear;
    @FXML
    private Label lblGenre;
    @FXML
    private Label lblAvailable;


    public void setBookDetails(Book book) {
        lblIsbn.setText(book.getIsbn());
        lblTitle.setText(book.getTitle());
        lblAuthor.setText(book.getAuthor());
        lblYear.setText(book.getYearPublished());
        lblGenre.setText(book.getGenre());

        lblAvailable.setText(book.isAvailable() ? "Yes" : "No");
    }

    @FXML
    public void onExitDetails(){
        Stage stage = (Stage) lblIsbn.getScene().getWindow();
        stage.close();
    }
}