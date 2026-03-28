package com.resources.library.controllers;

import com.resources.library.models.Book;
import com.resources.library.services.BookService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;

public class MainController {
    @FXML
    private TableView<Book> tableView;
    @FXML private TableColumn<Book, String> isbnColumn;
    @FXML private TableColumn<Book, String> titleColumn;
    @FXML private TableColumn<Book, String> authorColumn;
    @FXML private TableColumn<Book, String> yearColumn;
    @FXML private TableColumn<Book, String> genreColumn;
    @FXML private TableColumn<Book, Boolean> availableColumn;

    private BookService bookService;
    private ObservableList<Book> bookList;

    @FXML
    public void initialize(){
        bookService = new BookService();

        isbnColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getIsbn()));
        titleColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));
        authorColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAuthor()));
        yearColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getYearPublished()));
        genreColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGenre()));
        availableColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleBooleanProperty(data.getValue().isAvailable()));

        bookList = FXCollections.observableArrayList(bookService.getAllBooks());
        tableView.setItems(bookList);
    }
}
