package com.resources.library.controllers;

import com.resources.library.models.Book;
import com.resources.library.services.BookService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private TableView<Book> tableView;
    @FXML private TableColumn<Book, String> isbnColumn;
    @FXML private TableColumn<Book, String> titleColumn;
    @FXML private TableColumn<Book, String> authorColumn;
    @FXML private TableColumn<Book, String> yearColumn;
    @FXML private TableColumn<Book, String> genreColumn;
    @FXML private TableColumn<Book, Boolean> availableColumn;
    @FXML private TextField txtSearch;

    private BookService bookService;
    private ObservableList<Book> bookList;
    private Book searchedBook;

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
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    @FXML
    public void onDelete(){
        try {
            Book selectedBook = tableView.getSelectionModel().getSelectedItem();

            if (selectedBook == null) {
                throw new IllegalArgumentException("Select a book first.");
            }


            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/resources/library/views/delete-view.fxml"));
                Parent root = loader.load();

                DeleteController deleteController = loader.getController();
                deleteController.setData(selectedBook, bookService, selectedBook.getIsbn());

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Delete Book");
                stage.showAndWait();

                bookList.setAll(bookService.getAllBooks());
                tableView.refresh();
            } catch (Exception e) {
                showAlert("Error opening delete confirmation window: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        }
        catch (Exception e){
            showAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void onAdd(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/resources/library/views/add-view.fxml"));
            Parent root = loader.load();

            AddController addController = loader.getController();
            addController.setBookService(bookService);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add Book");
            stage.showAndWait();

            bookList.setAll(bookService.getAllBooks());
            tableView.refresh();

        } catch (IOException e) {
            showAlert("Error opening add book window: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void onUpdate() {
        try {
            Book selectedBook = tableView.getSelectionModel().getSelectedItem();

            if (selectedBook == null) {
                throw new IllegalArgumentException("Select a book first.");
            }

            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/resources/library/views/add-view.fxml")
                );

                Parent root = loader.load();

                AddController controller = loader.getController();
                controller.setBookService(bookService);
                controller.setBookToUpdate(selectedBook);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Edit Book");
                stage.showAndWait();

                bookList.setAll(bookService.getAllBooks());
                tableView.refresh();


            } catch (Exception e) {
                showAlert("Error opening edit window: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        } catch (Exception e){
            showAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void onViewDetails() {
        try {
            Book selectedBook;

            if (this.searchedBook != null) {
                selectedBook = this.searchedBook;
                this.searchedBook = null; // Reset the variable after each search
            } else {
                selectedBook = tableView.getSelectionModel().getSelectedItem();
            }

            if (selectedBook == null) {
                throw new IllegalArgumentException("Select a book first.");
            }

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/resources/library/views/details-view.fxml"));
                Parent root = loader.load();

                DetailsController controller = loader.getController();
                controller.setBookDetails(selectedBook);

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Book details");
                stage.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e){
            showAlert(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    @FXML
    public void onExport(){
        javafx.stage.FileChooser selector = new javafx.stage.FileChooser();

        selector.setTitle("Where do you want to download the report?");
        selector.setInitialFileName("report_books.csv");

        selector.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("CSV Document", "*.csv")
        );

        javafx.stage.Window currentWindow = tableView.getScene().getWindow();
        java.io.File choosedDocument = selector.showSaveDialog(currentWindow);

        if(choosedDocument != null){
            try{
                bookService.exportReport(choosedDocument);

                showAlert("Report saved on path: " + choosedDocument.getAbsolutePath(), Alert.AlertType.INFORMATION);
            } catch (Exception e){
                showAlert("Error, couldn't save report: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    public static void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);

        alert.setTitle(type == Alert.AlertType.INFORMATION ? "Success" : "Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void onSearch(){
        try {
            String isbnSearched = txtSearch.getText();

            if (isbnSearched.isBlank()){
                throw new IllegalArgumentException("Enter an ISBN to search");
            }

            this.searchedBook = bookService.findByIsbn(isbnSearched);

            if (this.searchedBook == null){
                throw new IllegalArgumentException("Book with this ISBN doesn't exist");
            }

            onViewDetails();
        }
        catch (IllegalArgumentException e){
            showAlert(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

}
