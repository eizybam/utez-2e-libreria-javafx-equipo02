package com.resources.library.repositories;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.resources.library.controllers.MainController;
import com.resources.library.models.Book;

/**
 * Repository responsible for loading and saving books in a local CSV file.
 */
public class BookRepository {
    private final String FILE_PATH = "books.csv";

    /**
     * Loads all books stored in the local file.
     * @return List<Book> -> List of books read from the file
     */
    public List<Book> loadBooks(){
        List<Book> books = new ArrayList<>();

        File file = new File(FILE_PATH);
        if (!file.exists()){
            return books;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null){
                String[] data = line.split(",");

                if (data.length == 6){
                    Book book = new Book(
                            data[0], // ISBN
                            data[1], // Title
                            data[2], // Author
                            data[3], // Year Published
                            data[4], // Genre
                            Boolean.parseBoolean(data[5]) // Available
                    );
                    books.add(book);
                }
            }
        }
        catch (IOException e){
            MainController.showAlert("Error loading books: " + e.getMessage(), javafx.scene.control.Alert.AlertType.ERROR);
        }
        return books;
    }

    /**
     * Saves the full book list to the local file.
     * @param books -> List of books to persist
     */
    public void saveBooks(List<Book> books) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {

            for (Book book : books) {
                String line = String.join(",",
                        book.getIsbn(),
                        book.getTitle(),
                        book.getAuthor(),
                        String.valueOf(book.getYearPublished()),
                        book.getGenre(),
                        String.valueOf(book.isAvailable())
                );

                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            MainController.showAlert("Error saving books: " + e.getMessage(), javafx.scene.control.Alert.AlertType.ERROR);
        }
    }

}