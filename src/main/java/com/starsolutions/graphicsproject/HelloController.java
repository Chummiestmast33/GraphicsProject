package com.starsolutions.graphicsproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    // --- Parte Superior ---
    @FXML
    private Label LabelSeccion;

    // --- Área Principal ---
    @FXML
    private Pane PanelGrafica;

    // --- Controles de la Derecha ---
    @FXML
    private ComboBox<String> ComboBoxOpciones;
    @FXML
    private Button BotonGrafica;
    @FXML
    private Button BotonExcel; // Asumo que hiciste la corrección en el FXML para que el ID esté en el botón
    @FXML
    private Button BotonImpresion;

    // --- Menú Lateral ---
    @FXML
    private ToggleGroup groupLateralMenu;
    @FXML
    private ToggleButton BottonSeguridad;
    @FXML
    private ToggleButton BottonVivienda;
    @FXML
    private ToggleButton BottonVias;
    @FXML
    private ToggleButton BottonEducacion;
    @FXML
    private ToggleButton BottonSalud;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // 1. Forzar que el panel de la gráfica sea siempre un cuadrado (Ratio 1:1)
        PanelGrafica.prefWidthProperty().bind(PanelGrafica.heightProperty());
        // 2. Inicializar las opciones del ComboBox
        ComboBoxOpciones.getItems().addAll("Municipal", "Estatal", "Federal");
        ComboBoxOpciones.getSelectionModel().selectFirst(); // Selecciona "Municipal" por defecto

        // 3. Detectar clics en el menú lateral
        groupLateralMenu.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Obtener el botón que el usuario acaba de clickear
                ToggleButton botonSeleccionado = (ToggleButton) newValue;

                // Actualizar el Label superior con el nombre de la sección
                LabelSeccion.setText(botonSeleccionado.getText());

                // Limpiar lo que haya en la pantalla (la gráfica anterior)
                PanelGrafica.getChildren().clear();

                System.out.println("Cambiando a sección: " + botonSeleccionado.getText());

            } else {
                // Si el usuario vuelve a hacer clic en el botón ya seleccionado, evita que se deseleccione
                if (oldValue != null) {
                    oldValue.setSelected(true);
                }
            }
        });

        // 4. Acción para el botón de "Gráfica"
        BotonGrafica.setOnAction(event -> {
            String opcionSeleccionada = ComboBoxOpciones.getValue();
            System.out.println("Generando gráfica: " + opcionSeleccionada + " para " + LabelSeccion.getText());

            // Aquí limpiaremos el panel y agregaremos la gráfica generada por código
            PanelGrafica.getChildren().clear();
        });

        if (groupLateralMenu.getSelectedToggle() != null) {
            ToggleButton botonInicial = (ToggleButton) groupLateralMenu.getSelectedToggle();
            LabelSeccion.setText(botonInicial.getText());
        }

        // 2. Detectar cada vez que el usuario hace clic en otro botón del menú
        groupLateralMenu.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Obtener el botón que el usuario acaba de clickear
                ToggleButton botonSeleccionado = (ToggleButton) newValue;

                // ¡Esta es la línea que cambia el texto del Label!
                LabelSeccion.setText(botonSeleccionado.getText());

                // Limpiar el panel de la gráfica anterior
                PanelGrafica.getChildren().clear();

                System.out.println("Sección actual: " + botonSeleccionado.getText());
            } else {
                // Evitar que el botón se deseleccione si lo vuelven a clickear
                if (oldValue != null) {
                    oldValue.setSelected(true);
                }
            }
        });
    }
}