package com.starsolutions.graphicsproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static final double MIN_APP_WIDTH = 1230;
    private static final double MIN_APP_HEIGHT = 660;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MIN_APP_WIDTH, Math.max(768, MIN_APP_HEIGHT));
        
        // Configurar el icono de la ventana principal
        Image windowIcon = new Image(HelloApplication.class.getResourceAsStream("icons/Logo.png"));
        stage.getIcons().add(windowIcon);

        stage.setTitle("DataMex");
        stage.setMinWidth(MIN_APP_WIDTH);
        stage.setMinHeight(MIN_APP_HEIGHT);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
}
