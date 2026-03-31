package com.resources.library.controllers;

import com.resources.library.services.BookService;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddController {

    @FXML
    private TextField txtIsbn;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtYearPublished;

    @FXML
    private ComboBox<String> cbGenreFilter;

    @FXML
    private CheckBox chkAvailable;

    BookService service = new BookService();


    String[] genreOptions = {"Science Fiction"};
    public void initialize(){
        this.cbGenreFilter.getItems().addAll(genreOptions);
    }

    @FXML
    public void onSave(){
        try{
            String isbn = txtIsbn.getText();
            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            String yearPublished = txtYearPublished.getText();
            String genre = cbGenreFilter.getValue();
            boolean available = chkAvailable.isSelected();
            
            service.addBook(isbn, title, author, yearPublished, genre, available);

        }
        catch(IllegalArgumentException e){
            System.out.println("Error adding book: " + e.getMessage());
        }
        
    }

}
