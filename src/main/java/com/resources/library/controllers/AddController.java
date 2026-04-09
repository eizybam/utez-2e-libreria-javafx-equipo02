package com.resources.library.controllers;

import com.resources.library.models.Book;
import com.resources.library.services.BookService;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the form used to register and edit books.
 */
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

    BookService service;
    private Book updatedBook;


    String[] genreOptions = {"Science Fiction", "Horror", "Comedy", "Drama", "Fantasy", "Romance", "Thriller", "Classic"};
    /**
     * Loads the genre options into the form ComboBox.
     */
    public void initialize(){
        this.cbGenreFilter.getItems().addAll(genreOptions);
    }

    /**
     * Saves a new book or updates the current book depending on the form mode.
     */
    @FXML
    public void onSave(){
        try{
            String isbn = txtIsbn.getText();
            String title = txtTitle.getText();
            String author = txtAuthor.getText();
            String yearPublished = txtYearPublished.getText();
            String genre = cbGenreFilter.getValue();
            boolean available = chkAvailable.isSelected();

            if (updatedBook == null) {
                service.addBook(isbn, title, author, yearPublished, genre, available);
            }
            else {
                updatedBook.setIsbn(isbn);
                updatedBook.setTitle(title);
                updatedBook.setAuthor(author);
                updatedBook.setYearPublished(yearPublished);
                updatedBook.setGenre(genre);
                updatedBook.setAvailable(available);

                service.updateBook(updatedBook);
            }
            MainController.showAlert("Book saved successfully.", Alert.AlertType.INFORMATION);
            Stage stage = (Stage) txtIsbn.getScene().getWindow();
            stage.close();

        }
        catch(IllegalArgumentException e){
             MainController.showAlert("Error adding book: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * Loads an existing book into the form for editing.
     * @param book -> Book to edit
     */
    public void setBookToUpdate(Book book){
        this.updatedBook = book;

        txtIsbn.setText(book.getIsbn());
        txtTitle.setText(book.getTitle());
        txtAuthor.setText(book.getAuthor());
        txtYearPublished.setText(book.getYearPublished());
        cbGenreFilter.setValue(book.getGenre());
        chkAvailable.setSelected(book.isAvailable());

        txtIsbn.setDisable(true);
    }


    /**
     * Closes the form without saving changes.
     */
    @FXML
    public void onCancelAdd(){
        Stage stage = (Stage) txtIsbn.getScene().getWindow();
        stage.close();
    }

    /**
     * Injects the service used by the form to persist changes.
     * @param service -> Book service
     */
    public void setBookService(BookService service){
        this.service =  service;
    }

}
