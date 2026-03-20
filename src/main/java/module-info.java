module com.starsolutions.graphicsproject {
    requires transitive javafx.controls;
    requires transitive javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.web;
    requires transitive java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.starsolutions.graphicsproject to javafx.fxml;
    opens com.starsolutions.graphicsproject.model to javafx.fxml;
    opens com.starsolutions.graphicsproject.database to javafx.fxml;
    opens com.starsolutions.graphicsproject.dao to javafx.fxml;
    
    exports com.starsolutions.graphicsproject;
    exports com.starsolutions.graphicsproject.model;
    exports com.starsolutions.graphicsproject.database;
    exports com.starsolutions.graphicsproject.dao;
}