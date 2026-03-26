package com.starsolutions.graphicsproject;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class HelloApplication extends Application {
    private static final double MIN_APP_WIDTH = 1230;
    private static final double MIN_APP_HEIGHT = 660;

    @Override
    public void start(Stage stage) throws IOException {
        Stage splashStage = new Stage();
        splashStage.initStyle(StageStyle.UNDECORATED);

        ImageView logo = new ImageView(new Image(HelloApplication.class.getResourceAsStream("icons/Logo.png")));
        logo.setFitWidth(300);
        logo.setPreserveRatio(true);

        StackPane splashPane = new StackPane(logo);
        splashPane.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #E2E8F0; -fx-border-width: 2px; -fx-border-radius: 15px; -fx-background-radius: 15px;");

        Scene splashScene = new Scene(splashPane, 450, 250);
        splashScene.setFill(Color.TRANSPARENT);
        splashStage.setScene(splashScene);
        splashStage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("icons/Logo.png")));
        splashStage.show();

        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(e -> {
            splashStage.close();
            try {
                mostrarVentanaPrincipal(stage);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        delay.play();
    }

    private void mostrarVentanaPrincipal(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MIN_APP_WIDTH, Math.max(768, MIN_APP_HEIGHT));

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