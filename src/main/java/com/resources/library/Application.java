package com.resources.library;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main JavaFX application that loads the system's initial window.
 */
public class Application extends javafx.application.Application {
    /**
     * Starts the application and shows the main catalog screen.
     * @param stage -> Primary JavaFX stage
     * @throws IOException -> Thrown if the main view cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("views/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Library Management System");
        stage.setScene(scene);
        stage.show();
    }
}
