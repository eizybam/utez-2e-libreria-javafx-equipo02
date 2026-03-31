module com.resources.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires org.kordamp.bootstrapfx.core;

    opens com.resources.library to javafx.fxml;
    exports com.resources.library;

    opens com.resources.library.controllers to javafx.fxml;
    exports com.resources.library.controllers;
    
    exports com.resources.library.models;
    exports com.resources.library.repositories;
    exports com.resources.library.services;
}