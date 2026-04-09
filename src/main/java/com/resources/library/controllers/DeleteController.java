package com.resources.library.controllers;

import com.resources.library.models.Book;
import com.resources.library.services.BookService;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the confirmation window used to delete books.
 */
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

    /**
     * Loads the selected book information and enables delete confirmation.
     * @param selectedBook -> Book selected for deletion
     * @param bookService -> Book service
     * @param isbn -> Reference ISBN used to validate the confirmation
     */
    public void setData(Book selectedBook, BookService bookService, String isbn){
        this.txtSelectedIsbn.setText(selectedBook.getIsbn());
        this.bookService = bookService;
        this.book = selectedBook;

        // Binding to enable or disable the delete button
        btnDelete.disableProperty().bind(
                txtIsbnToDelete.textProperty().isNotEqualTo(isbn)
        );

    }

    /**
     * Executes the deletion when the retyped ISBN matches.
     */
    @FXML
    public void onConfirmDelete(){
        bookService.deleteBook(book,txtIsbnToDelete.getText());
        MainController.showAlert("Book deleted successfully.", Alert.AlertType.INFORMATION);
        Stage stage = (Stage) txtSelectedIsbn.getScene().getWindow();
        stage.close();

    }

    /**
     * Closes the confirmation window without deleting the book.
     */
    @FXML
    public void onCancelDelete(){
        Stage stage = (Stage) txtSelectedIsbn.getScene().getWindow();
        stage.close();
    }


}
