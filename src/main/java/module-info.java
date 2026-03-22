module com.starsolutions.graphicsproject {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.web;
    requires javafx.swing;
    requires transitive java.sql;
    requires java.desktop;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires org.knowm.xchart;
    requires io;
    requires kernel;
    requires layout;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens com.starsolutions.graphicsproject to javafx.fxml;
    opens com.starsolutions.graphicsproject.model to javafx.fxml;
    opens com.starsolutions.graphicsproject.database to javafx.fxml;
    opens com.starsolutions.graphicsproject.dao to javafx.fxml;
    
    exports com.starsolutions.graphicsproject;
    exports com.starsolutions.graphicsproject.model;
    exports com.starsolutions.graphicsproject.database;
    exports com.starsolutions.graphicsproject.dao;
}