package com.starsolutions.graphicsproject;

import com.starsolutions.graphicsproject.dao.EducacionDAO;
import com.starsolutions.graphicsproject.dao.SaludDAO;
import com.starsolutions.graphicsproject.dao.SeguridadDAO;
import com.starsolutions.graphicsproject.database.DatabaseConnection;
import com.starsolutions.graphicsproject.database.DatabaseInitializer;
import com.starsolutions.graphicsproject.model.Educacion;
import com.starsolutions.graphicsproject.model.Salud;
import com.starsolutions.graphicsproject.model.Seguridad;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.RadarChart;
import org.knowm.xchart.RadarChartBuilder;
import org.knowm.xchart.RadarSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Font;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private static final int XCHART_RENDER_SIZE = 1200;

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

    private final SaludDAO saludDAO = new SaludDAO();
    private final SeguridadDAO seguridadDAO = new SeguridadDAO();
    private final EducacionDAO educacionDAO = new EducacionDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeDatabaseIfNeeded();

        ComboBoxOpciones.getItems().addAll("Municipal", "Estatal", "Federal", "Todos");
        ComboBoxOpciones.getSelectionModel().select("Todos");
        ComboBoxOpciones.valueProperty().addListener((observable, oldValue, newValue) -> renderCurrentVisualization());
        BotonImpresion.setOnAction(event -> generarReportePdfActual());
        BotonExcel.setOnAction(event -> generarExcelDatosVisibles());

        if (groupLateralMenu.getSelectedToggle() != null) {
            ToggleButton botonInicial = (ToggleButton) groupLateralMenu.getSelectedToggle();
            LabelSeccion.setText(botonInicial.getText());
            renderCurrentVisualization();
        }

        groupLateralMenu.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ToggleButton botonSeleccionado = (ToggleButton) newValue;
                LabelSeccion.setText(botonSeleccionado.getText());
                renderCurrentVisualization();
            } else {
                if (oldValue != null) {
                    oldValue.setSelected(true);
                }
            }
        });
    }

    private void initializeDatabaseIfNeeded() {
        DatabaseInitializer.initializeDatabse();
        try {
            if (isTableEmpty("Salud_Gobierno_Torreon")) {
                DatabaseInitializer.insertInitialData();
            }
        } catch (SQLException e) {
            System.err.println("No se pudo validar el estado de la base de datos: " + e.getMessage());
        }
    }

    private boolean isTableEmpty(String tableName) throws SQLException {
        String sql = "SELECT COUNT(*) AS total FROM " + tableName;
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return rs.next() && rs.getInt("total") == 0;
        }
    }

    private void renderCurrentVisualization() {
        ToggleButton selected = (ToggleButton) groupLateralMenu.getSelectedToggle();
        if (selected == null) {
            showMessage("Selecciona una sección del menú lateral.");
            return;
        }

        String section = selected.getText() == null ? "" : selected.getText().trim().toLowerCase(Locale.ROOT);

        try {
            if (section.contains("segur")) {
                renderSeguridadChart();
            } else if (section.contains("salud")) {
                renderSaludChart();
            } else if (section.contains("educ")) {
                renderEducacionChart();
            } else {
                showMessage("No hay gráfica disponible para esta sección.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Error al cargar la gráfica de " + selected.getText() + ": " + e.getMessage());
        }
    }

    private void renderSaludChart() {
        String ambito = obtenerAmbitoSeleccionado();

        if ("Todos".equalsIgnoreCase(ambito)) {
            renderSaludTodosMultiChart();
            return;
        }

        List<Salud> gobierno = saludDAO.obtenerSaludGobierno(ambito);
        List<Salud> ong = saludDAO.obtenerSaludONG(ambito);

        if (gobierno.isEmpty() || ong.isEmpty()) {
            showMessage("No hay datos de Salud disponibles.");
            return;
        }

        Map<String, Long> gobiernoMap = agruparSaludPorCategoria(gobierno);
        Map<String, Long> ongMap = agruparSaludPorCategoria(ong);

        setConcentricRingComparisonNode(
            "Salud (" + ambito + "): comparación por clasificación",
            gobiernoMap,
            ongMap,
            "Gobierno",
            "ONG"
        );
    }

    private void renderSaludTodosMultiChart() {
        VBox content = new VBox(12);
        content.setPadding(new Insets(4, 4, 4, 4));

        for (String ambito : List.of("Federal", "Estatal", "Municipal")) {
            Map<String, Long> gobiernoMap = agruparSaludPorCategoria(saludDAO.obtenerSaludGobierno(ambito));
            Map<String, Long> ongMap = agruparSaludPorCategoria(saludDAO.obtenerSaludONG(ambito));
            if (gobiernoMap.isEmpty() && ongMap.isEmpty()) {
                continue;
            }
            content.getChildren().add(createComparisonSectionNode(
                "Salud (" + ambito + "): comparación por clasificación",
                gobiernoMap,
                ongMap,
                "Gobierno",
                "ONG"
            ));
        }

        if (content.getChildren().isEmpty()) {
            showMessage("No hay datos de Salud disponibles.");
            return;
        }

        ScrollPane scrollPane = createContentScrollPane(content);
        setPanelContent(scrollPane);
    }

    private void renderSeguridadChart() {
        String ambito = obtenerAmbitoSeleccionado();

        if ("Todos".equalsIgnoreCase(ambito)) {
            renderSeguridadTodosMultiChart();
            return;
        }

        List<Seguridad> gobierno = seguridadDAO.obtenerSeguridadGobierno(ambito);
        List<Seguridad> ong = seguridadDAO.obtenerSeguridadONG(ambito);

        if (gobierno.isEmpty() || ong.isEmpty()) {
            showMessage("No hay datos de Seguridad disponibles.");
            return;
        }

        Map<String, Integer> gobiernoMap = agruparSeguridadPorDelito(gobierno);
        Map<String, Integer> ongMap = agruparSeguridadPorDelito(ong);

        setConcentricRingComparisonNode(
            "Seguridad (" + ambito + "): comparación por clasificación",
            gobiernoMap,
            ongMap,
            "Gobierno",
            "ONG"
        );
    }

    private void renderSeguridadTodosMultiChart() {
        VBox content = new VBox(12);
        content.setPadding(new Insets(4, 4, 4, 4));

        for (String ambito : List.of("Federal", "Estatal", "Municipal")) {
            Map<String, Integer> gobiernoMap = agruparSeguridadPorDelito(seguridadDAO.obtenerSeguridadGobierno(ambito));
            Map<String, Integer> ongMap = agruparSeguridadPorDelito(seguridadDAO.obtenerSeguridadONG(ambito));
            if (gobiernoMap.isEmpty() && ongMap.isEmpty()) {
                continue;
            }
            content.getChildren().add(createComparisonSectionNode(
                "Seguridad (" + ambito + "): comparación por clasificación",
                gobiernoMap,
                ongMap,
                "Gobierno",
                "ONG"
            ));
        }

        if (content.getChildren().isEmpty()) {
            showMessage("No hay datos de Seguridad disponibles.");
            return;
        }

        ScrollPane scrollPane = createContentScrollPane(content);
        setPanelContent(scrollPane);
    }

    private void setConcentricRingComparisonNode(String titleText,
                                                 Map<String, ? extends Number> gobiernoMap,
                                                 Map<String, ? extends Number> ongMap,
                                                 String gobiernoLabel,
                                                 String ongLabel) {
        VBox container = createComparisonSectionNode(titleText, gobiernoMap, ongMap, gobiernoLabel, ongLabel);

        setPanelContent(container);
    }

    private VBox createComparisonSectionNode(String titleText,
                                             Map<String, ? extends Number> gobiernoMap,
                                             Map<String, ? extends Number> ongMap,
                                             String gobiernoLabel,
                                             String ongLabel) {
        Label title = new Label(titleText);
        title.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #2E4057;");
        title.setWrapText(true);
        title.setMaxWidth(Double.MAX_VALUE);

        VBox cardGobierno = createConcentricRingCard(gobiernoLabel, gobiernoMap, "#1D3557");
        VBox cardOng = createConcentricRingCard(ongLabel, ongMap, "#E76F51");

        HBox chartsRow = new HBox(12, cardGobierno, cardOng);
        chartsRow.setAlignment(Pos.TOP_CENTER);
        chartsRow.setPadding(new Insets(4, 0, 0, 0));

        HBox.setHgrow(cardGobierno, Priority.ALWAYS);
        HBox.setHgrow(cardOng, Priority.ALWAYS);
        cardGobierno.setMaxWidth(Double.MAX_VALUE);
        cardOng.setMaxWidth(Double.MAX_VALUE);

        VBox container = new VBox(8, title, chartsRow);
        container.setFillWidth(true);
        chartsRow.setFillHeight(true);
        VBox.setVgrow(chartsRow, Priority.ALWAYS);

        chartsRow.prefWidthProperty().bind(container.widthProperty().subtract(2));
        chartsRow.setMinHeight(340);

        return container;
    }

    private ScrollPane createContentScrollPane(VBox content) {
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: transparent;");
        return scrollPane;
    }

    private void setPanelContent(javafx.scene.Node node) {
        PanelGrafica.getChildren().setAll(node);
        if (node instanceof javafx.scene.layout.Region region) {
            region.prefWidthProperty().bind(PanelGrafica.widthProperty());
            region.prefHeightProperty().bind(PanelGrafica.heightProperty());
            region.maxWidthProperty().bind(PanelGrafica.widthProperty());
            region.maxHeightProperty().bind(PanelGrafica.heightProperty());
        }
    }

    private VBox createConcentricRingCard(String cardTitle,
                                          Map<String, ? extends Number> values,
                                          String accentColorHex) {
        Label subtitle = new Label(cardTitle);
        subtitle.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: " + accentColorHex + ";");

        StackPane ringPane = new StackPane();
        ringPane.setMinHeight(170);
        ringPane.setPrefHeight(210);
        ringPane.setStyle("-fx-background-color: #F8FAFC; -fx-background-radius: 12; -fx-padding: 12;");

        VBox legendBox = new VBox(4);
        legendBox.setPadding(new Insets(6));
        legendBox.setFillWidth(true);

        ScrollPane legendScroll = new ScrollPane(legendBox);
        legendScroll.setFitToWidth(true);
        legendScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        legendScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        legendScroll.setMinHeight(110);
        legendScroll.setPrefHeight(140);
        legendScroll.setStyle(
            "-fx-control-inner-background: #FFFFFF;" +
            "-fx-background: #FFFFFF;" +
            "-fx-background-color: #FFFFFF;" +
            "-fx-background-radius: 8;" +
            "-fx-border-color: #E2E8F0;" +
            "-fx-border-radius: 8;"
        );

        List<Map.Entry<String, Double>> entries = sanitizeAndSortEntries(values);
        double total = entries.stream().mapToDouble(Map.Entry::getValue).sum();

        if (entries.isEmpty() || total <= 0) {
            Label empty = new Label("Sin datos");
            empty.setStyle("-fx-font-size: 13px; -fx-text-fill: #64748B;");
            ringPane.getChildren().setAll(empty);
        } else {
            Pane drawingPane = new Pane();
            drawingPane.setMinSize(240, 240);
            drawingPane.setPrefSize(280, 280);

            Label centerValue = new Label(String.format(Locale.ROOT, "Total\n%,.0f", total));
            centerValue.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-alignment: center; -fx-text-fill: #334155;");

            Color[] palette = ringPalette();
            double ringGap = 8;
            double ringThickness = entries.size() <= 4 ? 20 : 14;
            double maxRadius = 102;
            double minRadius = Math.max(28, maxRadius - entries.size() * (ringThickness + ringGap));

            for (int i = 0; i < entries.size(); i++) {
                Map.Entry<String, Double> entry = entries.get(i);
                Color ringColor = palette[i % palette.length];
                double ratio = entry.getValue() / total;
                double radius = Math.max(minRadius, maxRadius - i * (ringThickness + ringGap));

                Circle base = new Circle();
                base.setFill(Color.TRANSPARENT);
                base.setStroke(Color.web("#E2E8F0"));
                base.setStrokeWidth(ringThickness);

                Arc arc = new Arc();
                arc.setType(ArcType.OPEN);
                arc.setFill(Color.TRANSPARENT);
                arc.setStartAngle(90);
                arc.setLength(-360 * ratio);
                arc.setStroke(ringColor);
                arc.setStrokeWidth(ringThickness);
                arc.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);

                base.centerXProperty().bind(drawingPane.widthProperty().divide(2));
                base.centerYProperty().bind(drawingPane.heightProperty().divide(2));
                base.radiusProperty().bind(
                    Bindings.min(drawingPane.widthProperty(), drawingPane.heightProperty()).divide(2.9)
                        .subtract(i * (ringThickness + ringGap))
                );

                arc.centerXProperty().bind(drawingPane.widthProperty().divide(2));
                arc.centerYProperty().bind(drawingPane.heightProperty().divide(2));
                arc.radiusXProperty().bind(base.radiusProperty());
                arc.radiusYProperty().bind(base.radiusProperty());

                drawingPane.getChildren().addAll(base, arc);

                String detailLine = String.format(
                    Locale.ROOT,
                    "%s: %,.0f (%.1f%%)",
                    entry.getKey(),
                    entry.getValue(),
                    ratio * 100
                );

                Text bullet = new Text("● ");
                bullet.setFill(ringColor);
                bullet.setStyle("-fx-font-size: 12px;");

                Text detail = new Text(detailLine);
                detail.setFill(Color.web("#334155"));
                detail.setStyle("-fx-font-size: 12px;");

                TextFlow row = new TextFlow(bullet, detail);
                row.setMaxWidth(Double.MAX_VALUE);
                legendBox.getChildren().add(row);
            }

            ringPane.getChildren().setAll(drawingPane, centerValue);
            StackPane.setAlignment(centerValue, Pos.CENTER);
        }

        if (legendBox.getChildren().isEmpty()) {
            Label emptyLegend = new Label("Sin información de leyenda.");
            emptyLegend.setStyle("-fx-font-size: 12px; -fx-text-fill: #64748B;");
            legendBox.getChildren().add(emptyLegend);
        }

        VBox card = new VBox(8, subtitle, ringPane, legendScroll);
        card.setFillWidth(true);
        card.setMinHeight(330);
        VBox.setVgrow(ringPane, Priority.ALWAYS);
        VBox.setVgrow(legendScroll, Priority.ALWAYS);
        card.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 12; -fx-padding: 10; " +
            "-fx-border-color: #E2E8F0; -fx-border-radius: 12;");
        return card;
    }

    private List<Map.Entry<String, Double>> sanitizeAndSortEntries(Map<String, ? extends Number> values) {
        List<Map.Entry<String, Double>> entries = new ArrayList<>();
        for (Map.Entry<String, ? extends Number> entry : values.entrySet()) {
            double numericValue = Math.max(0, toDouble(entry.getValue()));
            if (numericValue == 0) {
                continue;
            }
            entries.add(Map.entry(entry.getKey(), numericValue));
        }
        entries.sort(Comparator.comparingDouble((Map.Entry<String, Double> e) -> e.getValue()).reversed());
        return entries;
    }

    private Color[] ringPalette() {
        return new Color[] {
            Color.web("#2563EB"),
            Color.web("#F97316"),
            Color.web("#14B8A6"),
            Color.web("#A855F7"),
            Color.web("#E11D48"),
            Color.web("#06B6D4")
        };
    }

    private void renderEducacionChart() {
        String ambito = obtenerAmbitoSeleccionado();

        if ("Todos".equalsIgnoreCase(ambito)) {
            renderEducacionChartTodosSeparado();
            return;
        }

        List<Educacion> gobierno = agruparEducacionPorNivel(educacionDAO.obtenerEducacionGobierno(ambito));
        List<Educacion> ong = agruparEducacionPorNivel(educacionDAO.obtenerEducacionONG(ambito));

        if (gobierno.isEmpty() || ong.isEmpty()) {
            showMessage("No hay datos de Educación disponibles.");
            return;
        }

        Map<String, Educacion> ongByNivel = new HashMap<>();
        for (Educacion registro : ong) {
            ongByNivel.put(registro.getNivelEducativo(), registro);
        }

        List<String> niveles = new ArrayList<>();
        List<Double> valoresGobierno = new ArrayList<>();
        List<Double> valoresOng = new ArrayList<>();

        for (Educacion eGobierno : gobierno) {
            Educacion eOng = ongByNivel.get(eGobierno.getNivelEducativo());
            if (eOng == null) {
                continue;
            }

            double ratioGobierno = alumnosPorDocente(eGobierno);
            double ratioOng = alumnosPorDocente(eOng);

            niveles.add(eGobierno.getNivelEducativo());
            valoresGobierno.add(ratioGobierno);
            valoresOng.add(ratioOng);
        }

        if (niveles.isEmpty()) {
            showMessage("No hay niveles comparables entre Gobierno y ONG en Educación.");
            return;
        }

        RadarChart radarChart = new RadarChartBuilder()
            .width(XCHART_RENDER_SIZE)
            .height(XCHART_RENDER_SIZE)
            .build();

        configurarEstiloRadarConTextoGrande(radarChart);

        String[] etiquetas = niveles.toArray(String[]::new);
        radarChart.setRadiiLabels(etiquetas);

        double[] serieGobiernoValuesRaw = toPrimitiveArray(valoresGobierno);
        double[] serieOngValuesRaw = toPrimitiveArray(valoresOng);
        double maxValue = max(serieGobiernoValuesRaw, serieOngValuesRaw);
        double[] serieGobiernoValues = normalizeToUnitRange(serieGobiernoValuesRaw, maxValue);
        double[] serieOngValues = normalizeToUnitRange(serieOngValuesRaw, maxValue);

        RadarSeries serieGobierno = radarChart.addSeries("Gobierno", serieGobiernoValues, etiquetas);
        serieGobierno.setLineColor(new java.awt.Color(29, 53, 87, 210));
        serieGobierno.setLineWidth(2.6f);
        serieGobierno.setMarker(SeriesMarkers.CIRCLE);
        serieGobierno.setMarkerColor(new java.awt.Color(29, 53, 87, 230));

        RadarSeries serieOng = radarChart.addSeries("ONG", serieOngValues, etiquetas);
        serieOng.setLineColor(new java.awt.Color(231, 111, 81, 210));
        serieOng.setLineWidth(2.6f);
        serieOng.setMarker(SeriesMarkers.DIAMOND);
        serieOng.setMarkerColor(new java.awt.Color(231, 111, 81, 230));

        BufferedImage image = BitmapEncoder.getBufferedImage(radarChart);
        ImageView chartImage = new ImageView(SwingFXUtils.toFXImage(image, null));
        chartImage.setPreserveRatio(true);
        chartImage.setSmooth(true);

        setEducacionChartNode(chartImage,
            "Educación (" + ambito + "): polígono comparativo alumnos/docente (Gobierno vs ONG)");
    }

    private void renderEducacionChartTodosSeparado() {
        List<Educacion> gobFederal = agruparEducacionPorNivel(educacionDAO.obtenerEducacionGobierno("Federal"));
        List<Educacion> gobEstatal = agruparEducacionPorNivel(educacionDAO.obtenerEducacionGobierno("Estatal"));
        List<Educacion> gobMunicipal = agruparEducacionPorNivel(educacionDAO.obtenerEducacionGobierno("Municipal"));

        List<Educacion> ongFederal = agruparEducacionPorNivel(educacionDAO.obtenerEducacionONG("Federal"));
        List<Educacion> ongEstatal = agruparEducacionPorNivel(educacionDAO.obtenerEducacionONG("Estatal"));
        List<Educacion> ongMunicipal = agruparEducacionPorNivel(educacionDAO.obtenerEducacionONG("Municipal"));

        if (gobFederal.isEmpty() && gobEstatal.isEmpty() && gobMunicipal.isEmpty() &&
            ongFederal.isEmpty() && ongEstatal.isEmpty() && ongMunicipal.isEmpty()) {
            showMessage("No hay datos de Educación disponibles.");
            return;
        }

        // Mismas aristas para todos: cada nivel educativo base.
        String[] etiquetas = {
            "Preescolar", "Primaria", "Secundaria", "Media Superior", "Superior"
        };

        Map<String, Double> gFed = ratioPorNivel(gobFederal);
        Map<String, Double> gEst = ratioPorNivel(gobEstatal);
        Map<String, Double> gMun = ratioPorNivel(gobMunicipal);
        Map<String, Double> oFed = ratioPorNivel(ongFederal);
        Map<String, Double> oEst = ratioPorNivel(ongEstatal);
        Map<String, Double> oMun = ratioPorNivel(ongMunicipal);

        double[] gFedRaw = valoresPorEtiquetas(gFed, etiquetas);
        double[] gEstRaw = valoresPorEtiquetas(gEst, etiquetas);
        double[] gMunRaw = valoresPorEtiquetas(gMun, etiquetas);
        double[] oFedRaw = valoresPorEtiquetas(oFed, etiquetas);
        double[] oEstRaw = valoresPorEtiquetas(oEst, etiquetas);
        double[] oMunRaw = valoresPorEtiquetas(oMun, etiquetas);

        double maxValue = max(gFedRaw, gEstRaw, gMunRaw, oFedRaw, oEstRaw, oMunRaw);
        double[] gFedNorm = normalizeToUnitRange(gFedRaw, maxValue);
        double[] gEstNorm = normalizeToUnitRange(gEstRaw, maxValue);
        double[] gMunNorm = normalizeToUnitRange(gMunRaw, maxValue);
        double[] oFedNorm = normalizeToUnitRange(oFedRaw, maxValue);
        double[] oEstNorm = normalizeToUnitRange(oEstRaw, maxValue);
        double[] oMunNorm = normalizeToUnitRange(oMunRaw, maxValue);

        RadarChart radarChart = new RadarChartBuilder()
            .width(XCHART_RENDER_SIZE)
            .height(XCHART_RENDER_SIZE)
            .build();

        configurarEstiloRadarConTextoGrande(radarChart);
        radarChart.setRadiiLabels(etiquetas);

        RadarSeries serieGFed = radarChart.addSeries("Gobierno Federal", gFedNorm, etiquetas);
        serieGFed.setLineColor(new java.awt.Color(15, 76, 129, 220));
        serieGFed.setLineWidth(2.3f);
        serieGFed.setMarker(SeriesMarkers.CIRCLE);
        serieGFed.setMarkerColor(new java.awt.Color(15, 76, 129, 230));
        serieGFed.setFillColor(new java.awt.Color(15, 76, 129, 45));

        RadarSeries serieGEst = radarChart.addSeries("Gobierno Estatal", gEstNorm, etiquetas);
        serieGEst.setLineColor(new java.awt.Color(44, 120, 115, 220));
        serieGEst.setLineWidth(2.3f);
        serieGEst.setMarker(SeriesMarkers.SQUARE);
        serieGEst.setMarkerColor(new java.awt.Color(44, 120, 115, 230));
        serieGEst.setFillColor(new java.awt.Color(44, 120, 115, 45));

        RadarSeries serieGMun = radarChart.addSeries("Gobierno Municipal", gMunNorm, etiquetas);
        serieGMun.setLineColor(new java.awt.Color(87, 159, 43, 220));
        serieGMun.setLineWidth(2.3f);
        serieGMun.setMarker(SeriesMarkers.TRIANGLE_UP);
        serieGMun.setMarkerColor(new java.awt.Color(87, 159, 43, 230));
        serieGMun.setFillColor(new java.awt.Color(87, 159, 43, 45));

        RadarSeries serieOFed = radarChart.addSeries("ONG Federal", oFedNorm, etiquetas);
        serieOFed.setLineColor(new java.awt.Color(188, 71, 73, 220));
        serieOFed.setLineWidth(2.3f);
        serieOFed.setMarker(SeriesMarkers.DIAMOND);
        serieOFed.setMarkerColor(new java.awt.Color(188, 71, 73, 230));
        serieOFed.setFillColor(new java.awt.Color(188, 71, 73, 45));

        RadarSeries serieOEst = radarChart.addSeries("ONG Estatal", oEstNorm, etiquetas);
        serieOEst.setLineColor(new java.awt.Color(224, 122, 95, 220));
        serieOEst.setLineWidth(2.3f);
        serieOEst.setMarker(SeriesMarkers.CROSS);
        serieOEst.setMarkerColor(new java.awt.Color(224, 122, 95, 230));
        serieOEst.setFillColor(new java.awt.Color(224, 122, 95, 45));

        RadarSeries serieOMun = radarChart.addSeries("ONG Municipal", oMunNorm, etiquetas);
        serieOMun.setLineColor(new java.awt.Color(242, 188, 46, 220));
        serieOMun.setLineWidth(2.3f);
        serieOMun.setMarker(SeriesMarkers.PLUS);
        serieOMun.setMarkerColor(new java.awt.Color(242, 188, 46, 230));
        serieOMun.setFillColor(new java.awt.Color(242, 188, 46, 45));

        BufferedImage image = BitmapEncoder.getBufferedImage(radarChart);
        ImageView chartImage = new ImageView(SwingFXUtils.toFXImage(image, null));
        chartImage.setPreserveRatio(true);
        chartImage.setSmooth(true);

        setEducacionChartNode(chartImage,
            "Educación (Todos): polígonos separados por ámbito y fuente");
    }

    private String obtenerAmbitoSeleccionado() {
        String seleccionado = ComboBoxOpciones.getSelectionModel().getSelectedItem();
        return (seleccionado == null || seleccionado.isBlank()) ? "Todos" : seleccionado;
    }

    private Map<String, Long> agruparSaludPorCategoria(List<Salud> datos) {
        Map<String, Long> acumulado = new LinkedHashMap<>();
        for (Salud registro : datos) {
            acumulado.merge(registro.getCategoriaPoblacion(), registro.getDosisAplicadas(), Long::sum);
        }
        return acumulado;
    }

    private Map<String, Integer> agruparSeguridadPorDelito(List<Seguridad> datos) {
        Map<String, Integer> acumulado = new LinkedHashMap<>();
        for (Seguridad registro : datos) {
            acumulado.merge(registro.getTipoDelito(), registro.getCasos(), Integer::sum);
        }
        return acumulado;
    }

    private List<Educacion> agruparEducacionPorNivel(List<Educacion> datos) {
        Map<String, Educacion> acumulado = new LinkedHashMap<>();
        for (Educacion registro : datos) {
            Educacion previo = acumulado.get(registro.getNivelEducativo());
            if (previo == null) {
                acumulado.put(registro.getNivelEducativo(), new Educacion(
                    registro.getNivelEducativo(),
                    registro.getEscuelasPublicas(),
                    registro.getEscuelasPrivadas(),
                    registro.getAlumnosHombres(),
                    registro.getAlumnosMujeres(),
                    registro.getDocentesHombres(),
                    registro.getDocentesMujeres(),
                    registro.getDocentesTotales()
                ));
                continue;
            }

            previo.setEscuelasPublicas(previo.getEscuelasPublicas() + registro.getEscuelasPublicas());
            previo.setEscuelasPrivadas(previo.getEscuelasPrivadas() + registro.getEscuelasPrivadas());
            previo.setAlumnosHombres(previo.getAlumnosHombres() + registro.getAlumnosHombres());
            previo.setAlumnosMujeres(previo.getAlumnosMujeres() + registro.getAlumnosMujeres());
            previo.setDocentesHombres(previo.getDocentesHombres() + registro.getDocentesHombres());
            previo.setDocentesMujeres(previo.getDocentesMujeres() + registro.getDocentesMujeres());
            previo.setDocentesTotales(previo.getDocentesTotales() + registro.getDocentesTotales());
        }
        return new ArrayList<>(acumulado.values());
    }

    private Map<String, Double> ratioPorNivel(List<Educacion> datos) {
        Map<String, Double> ratio = new HashMap<>();
        for (Educacion registro : datos) {
            ratio.put(registro.getNivelEducativo(), alumnosPorDocente(registro));
        }
        return ratio;
    }

    private double[] valoresPorEtiquetas(Map<String, Double> ratioByNivel, String[] etiquetas) {
        double[] valores = new double[etiquetas.length];
        for (int i = 0; i < etiquetas.length; i++) {
            valores[i] = ratioByNivel.getOrDefault(etiquetas[i], 0.0);
        }
        return valores;
    }

    private double toDouble(Number value) {
        return value == null ? 0.0 : value.doubleValue();
    }

    private void setEducacionChartNode(ImageView chartImage, String description) {
        Label title = new Label(description);
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2E4057;");
        title.setWrapText(true);
        title.setMaxWidth(Double.MAX_VALUE);

        StackPane chartWrapper = new StackPane(chartImage);
        chartWrapper.setStyle("-fx-padding: 8; -fx-background-color: transparent;");
        StackPane.setAlignment(chartImage, javafx.geometry.Pos.CENTER);

        VBox container = new VBox(8, title, chartWrapper);
        container.setFillWidth(true);
        VBox.setVgrow(chartWrapper, Priority.ALWAYS);

        chartImage.fitWidthProperty().bind(
            Bindings.max(
                230,
                Bindings.min(
                    780,
                    Bindings.min(
                        chartWrapper.widthProperty().multiply(0.90),
                        chartWrapper.heightProperty().multiply(0.90)
                    )
                )
            )
        );
        chartImage.fitHeightProperty().bind(chartImage.fitWidthProperty());

        PanelGrafica.getChildren().setAll(container);
        container.prefWidthProperty().bind(PanelGrafica.widthProperty());
        container.prefHeightProperty().bind(PanelGrafica.heightProperty());
        container.maxWidthProperty().bind(PanelGrafica.widthProperty());
        container.maxHeightProperty().bind(PanelGrafica.heightProperty());
    }

    private void configurarEstiloRadarConTextoGrande(RadarChart radarChart) {
        radarChart.getStyler().setLegendVisible(true);
        radarChart.getStyler().setSeriesFilled(true);
        radarChart.getStyler().setToolTipsEnabled(false);
        radarChart.getStyler().setMarkerSize(6);
        radarChart.getStyler().setChartPadding(36);
        radarChart.getStyler().setPlotContentSize(0.72);
        radarChart.getStyler().setChartBackgroundColor(java.awt.Color.WHITE);
        radarChart.getStyler().setPlotBackgroundColor(java.awt.Color.WHITE);
        radarChart.getStyler().setRadiiTickMarksCount(5);
        radarChart.getStyler().setRadiiTitlePadding(8);
        radarChart.getStyler().setPlotBorderVisible(false);
        radarChart.getStyler().setLegendFont(new Font("SansSerif", Font.BOLD, 24));
        radarChart.getStyler().setRadiiTitleFont(new Font("SansSerif", Font.PLAIN, 18));
    }

    private double alumnosPorDocente(Educacion registro) {
        int totalAlumnos = registro.getAlumnosHombres() + registro.getAlumnosMujeres();
        int totalDocentes = Math.max(1, registro.getDocentesTotales());
        return (double) totalAlumnos / totalDocentes;
    }

    private double[] toPrimitiveArray(List<Double> values) {
        double[] result = new double[values.size()];
        for (int i = 0; i < values.size(); i++) {
            result[i] = values.get(i);
        }
        return result;
    }

    private double[] normalizeToUnitRange(double[] values, double maxValue) {
        double[] normalized = new double[values.length];
        double safeMax = maxValue <= 0 ? 1.0 : maxValue;
        for (int i = 0; i < values.length; i++) {
            normalized[i] = values[i] / safeMax;
        }
        return normalized;
    }

    private double max(double[] first, double[] second) {
        double max = 0;
        for (double value : first) {
            max = Math.max(max, value);
        }
        for (double value : second) {
            max = Math.max(max, value);
        }
        return max;
    }

    private double max(double[]... arrays) {
        double max = 0;
        for (double[] array : arrays) {
            for (double value : array) {
                max = Math.max(max, value);
            }
        }
        return max;
    }

    private void showMessage(String message) {
        Label label = new Label(message);
        label.setWrapText(true);
        label.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #52606D; -fx-padding: 14;");

        StackPane wrapper = new StackPane(label);
        wrapper.prefWidthProperty().bind(PanelGrafica.widthProperty());
        wrapper.prefHeightProperty().bind(PanelGrafica.heightProperty());
        PanelGrafica.getChildren().setAll(wrapper);
    }

    private void generarReportePdfActual() {
        ToggleButton selected = (ToggleButton) groupLateralMenu.getSelectedToggle();
        String seccion = selected == null ? "General" : selected.getText();
        String ambito = obtenerAmbitoSeleccionado();

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Guardar reporte PDF");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo PDF", "*.pdf"));
        chooser.setInitialFileName(buildPdfFileName(seccion, ambito));

        Window owner = PanelGrafica.getScene() != null ? PanelGrafica.getScene().getWindow() : null;
        File outputFile = chooser.showSaveDialog(owner);
        if (outputFile == null) {
            return;
        }

        try {
            List<byte[]> chartImages = capturarGraficasActualesComoPng();
            crearPdfReporte(outputFile, seccion, ambito, chartImages);
            showMessage("Reporte PDF generado correctamente en:\n" + outputFile.getAbsolutePath());
            openPdfIfPossible(outputFile);
        } catch (Exception e) {
            showMessage("No se pudo generar el PDF: " + e.getMessage());
        }
    }

    private void generarExcelDatosVisibles() {
        ToggleButton selected = (ToggleButton) groupLateralMenu.getSelectedToggle();
        String seccion = selected == null ? "General" : selected.getText();
        String ambito = obtenerAmbitoSeleccionado();

        Map<String, Double> datosGobierno = obtenerDatosReportePorSeccion(seccion, ambito, true);
        Map<String, Double> datosOng = obtenerDatosReportePorSeccion(seccion, ambito, false);

        if (datosGobierno.isEmpty() && datosOng.isEmpty()) {
            showMessage("No hay datos visibles para exportar a Excel.");
            return;
        }

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Guardar reporte Excel");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivo Excel", "*.xlsx"));
        chooser.setInitialFileName(buildExcelFileName(seccion, ambito));

        Window owner = PanelGrafica.getScene() != null ? PanelGrafica.getScene().getWindow() : null;
        File outputFile = chooser.showSaveDialog(owner);
        if (outputFile == null) {
            return;
        }

        try {
            crearExcelReporte(outputFile, seccion, ambito, datosGobierno, datosOng);
            showMessage("Archivo Excel generado correctamente en:\n" + outputFile.getAbsolutePath());
            openPdfIfPossible(outputFile);
        } catch (Exception e) {
            showMessage("No se pudo generar el Excel: " + e.getMessage());
        }
    }

    private String buildExcelFileName(String seccion, String ambito) {
        String safeSeccion = sanitizeFilePart(seccion);
        String safeAmbito = sanitizeFilePart(ambito);
        String timestamp = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm").format(LocalDateTime.now());
        return "Reporte_" + safeSeccion + "_" + safeAmbito + "_" + timestamp + ".xlsx";
    }

    private void crearExcelReporte(File outputFile,
                                   String seccion,
                                   String ambito,
                                   Map<String, Double> datosGobierno,
                                   Map<String, Double> datosOng) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Reporte");

            int rowIndex = 0;
            Row titleRow = sheet.createRow(rowIndex++);
            titleRow.createCell(0).setCellValue("Reporte de " + seccion + " - " + ambito);

            Row dateRow = sheet.createRow(rowIndex++);
            dateRow.createCell(0).setCellValue("Fecha: " +
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(LocalDateTime.now()));

            rowIndex++;

            Row header = sheet.createRow(rowIndex++);
            header.createCell(0).setCellValue("Categoría");
            header.createCell(1).setCellValue("Gobierno");
            header.createCell(2).setCellValue("ONG");
            header.createCell(3).setCellValue("Diferencia");

            List<String> categorias = combinarCategorias(datosGobierno, datosOng);
            double totalGobierno = 0;
            double totalOng = 0;

            for (String categoria : categorias) {
                double valorGobierno = datosGobierno.getOrDefault(categoria, 0.0);
                double valorOng = datosOng.getOrDefault(categoria, 0.0);
                double diferencia = valorGobierno - valorOng;

                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(categoria);
                row.createCell(1).setCellValue(valorGobierno);
                row.createCell(2).setCellValue(valorOng);
                row.createCell(3).setCellValue(diferencia);

                totalGobierno += valorGobierno;
                totalOng += valorOng;
            }

            rowIndex++;
            Row totals = sheet.createRow(rowIndex++);
            totals.createCell(0).setCellValue("Totales");
            totals.createCell(1).setCellValue(totalGobierno);
            totals.createCell(2).setCellValue(totalOng);
            totals.createCell(3).setCellValue(totalGobierno - totalOng);

            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            try (java.io.FileOutputStream out = new java.io.FileOutputStream(outputFile)) {
                workbook.write(out);
            }
        }
    }

    private String buildPdfFileName(String seccion, String ambito) {
        String safeSeccion = sanitizeFilePart(seccion);
        String safeAmbito = sanitizeFilePart(ambito);
        String timestamp = DateTimeFormatter.ofPattern("yyyyMMdd_HHmm").format(LocalDateTime.now());
        return "Reporte_" + safeSeccion + "_" + safeAmbito + "_" + timestamp + ".pdf";
    }

    private String sanitizeFilePart(String value) {
        if (value == null || value.isBlank()) {
            return "General";
        }
        return value
            .replaceAll("[\\\\/:*?\"<>|]", "")
            .trim()
            .replace(' ', '_');
    }

    private byte[] capturarNodoComoPng(Node node) throws IOException {
        node.applyCss();
        if (node instanceof Parent parent) {
            parent.layout();
        } else {
            node.autosize();
        }

        double width = Math.max(1, node.getLayoutBounds().getWidth());
        double height = Math.max(1, node.getLayoutBounds().getHeight());

        if (width <= 1 || height <= 1) {
            width = Math.max(1, PanelGrafica.getWidth());
            height = Math.max(1, PanelGrafica.getHeight());
        }

        WritableImage fxImage = new WritableImage((int) Math.ceil(width), (int) Math.ceil(height));
        node.snapshot(new SnapshotParameters(), fxImage);
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(fxImage, null);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", out);
        return out.toByteArray();
    }

    private List<byte[]> capturarGraficasActualesComoPng() throws IOException {
        List<byte[]> images = new ArrayList<>();

        if (PanelGrafica.getChildren().isEmpty()) {
            images.add(capturarNodoComoPng(PanelGrafica));
            return images;
        }

        Node rootNode = PanelGrafica.getChildren().getFirst();
        if (rootNode instanceof ScrollPane scrollPane) {
            Node content = scrollPane.getContent();
            if (content instanceof VBox contentBox && !contentBox.getChildren().isEmpty()) {
                for (Node child : contentBox.getChildren()) {
                    images.add(capturarNodoComoPng(child));
                }
            } else if (content != null) {
                images.add(capturarNodoComoPng(content));
            } else {
                images.add(capturarNodoComoPng(scrollPane));
            }
        } else {
            images.add(capturarNodoComoPng(rootNode));
        }

        return images;
    }

    private void crearPdfReporte(File outputFile,
                                 String seccion,
                                 String ambito,
                                 List<byte[]> chartImages) throws IOException {
        try (PdfWriter writer = new PdfWriter(outputFile);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            document.setMargins(40, 40, 40, 40);

            Paragraph titulo = new Paragraph("Reporte de " + seccion)
                .setBold()
                .setFontSize(18)
                .setFontColor(ColorConstants.DARK_GRAY)
                .setTextAlignment(TextAlignment.CENTER);

            Paragraph subtitulo = new Paragraph("Ámbito: " + ambito + "  |  Fecha: " +
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").format(LocalDateTime.now()))
                .setFontSize(11)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(ColorConstants.GRAY);

            Paragraph descripcionGeneral = new Paragraph(buildDescripcionGeneral(seccion, ambito))
                .setFontSize(12)
                .setMarginTop(16)
                .setMarginBottom(8)
                .setMultipliedLeading(1.25f);

            Paragraph introGobierno = new Paragraph(
                "Se presenta la siguiente gráfica del Gobierno, con su distribución de valores por categoría " +
                "para apoyar el análisis comparativo del ámbito seleccionado."
            ).setFontSize(11).setMarginTop(8).setMarginBottom(6);

            Paragraph introOng = new Paragraph(
                "Se presenta la siguiente información de una ONG, con una visualización equivalente " +
                "que permite comparar de forma directa los resultados frente al Gobierno."
            ).setFontSize(11).setMarginTop(4).setMarginBottom(12);

            document.add(titulo);
            document.add(subtitulo);
            document.add(descripcionGeneral);
            document.add(introGobierno);
            document.add(introOng);

            if (chartImages != null && !chartImages.isEmpty()) {
                for (int i = 0; i < chartImages.size(); i++) {
                    byte[] chartImageBytes = chartImages.get(i);
                    if (chartImageBytes == null || chartImageBytes.length == 0) {
                        continue;
                    }

                    if (chartImages.size() > 1) {
                        document.add(new Paragraph("Gráfica " + (i + 1))
                            .setBold()
                            .setFontSize(12)
                            .setMarginTop(4)
                            .setMarginBottom(6));
                    }

                    ImageData imageData = ImageDataFactory.create(chartImageBytes);
                    Image image = new Image(imageData);
                    image.setAutoScale(false);
                    image.scaleToFit(520, 640);
                    image.setHorizontalAlignment(com.itextpdf.layout.properties.HorizontalAlignment.CENTER);
                    image.setMarginTop(6);
                    document.add(image);
                }
            }

            Map<String, Double> datosGobierno = obtenerDatosReportePorSeccion(seccion, ambito, true);
            Map<String, Double> datosOng = obtenerDatosReportePorSeccion(seccion, ambito, false);
            if (!datosGobierno.isEmpty() || !datosOng.isEmpty()) {
                document.add(new Paragraph("Comparativo de información (Gobierno vs ONG)")
                    .setBold()
                    .setFontSize(12)
                    .setMarginTop(12)
                    .setMarginBottom(6));

                agregarTablaComparativa(document, datosGobierno, datosOng);
                agregarResumenDiferencias(document, datosGobierno, datosOng);
            }

            Paragraph cierre = new Paragraph(
                "Este reporte resume la información visible en el panel actual de la aplicación " +
                "y conserva la gráfica generada en ese momento para fines de consulta y seguimiento."
            ).setFontSize(10).setItalic().setMarginTop(12).setFontColor(ColorConstants.GRAY);

            document.add(cierre);
        }
    }

    private String buildDescripcionGeneral(String seccion, String ambito) {
        String base = "El presente PDF contiene un resumen de la sección de " + seccion +
            " para el ámbito " + ambito + ". ";

        String detalle;
        String sectionNormalized = seccion == null ? "" : seccion.toLowerCase(Locale.ROOT);
        if (sectionNormalized.contains("salud")) {
            detalle = "La información describe el comportamiento de las categorías de salud " +
                "reportadas por Gobierno y ONG, con enfoque en su participación relativa.";
        } else if (sectionNormalized.contains("segur")) {
            detalle = "La información presenta los indicadores de seguridad por tipo de registro, " +
                "permitiendo identificar diferencias entre Gobierno y ONG.";
        } else if (sectionNormalized.contains("educ")) {
            detalle = "La información resume indicadores educativos y su comparación entre " +
                "fuentes de Gobierno y ONG en los niveles disponibles.";
        } else {
            detalle = "La información muestra la visualización activa para facilitar su análisis y comparación.";
        }

        return base + detalle;
    }

    private Map<String, Double> obtenerDatosReportePorSeccion(String seccion, String ambito, boolean gobierno) {
        String sectionNormalized = seccion == null ? "" : seccion.toLowerCase(Locale.ROOT);

        if (sectionNormalized.contains("salud")) {
            List<Salud> datos = gobierno
                ? saludDAO.obtenerSaludGobierno(ambito)
                : saludDAO.obtenerSaludONG(ambito);
            Map<String, Long> agrupado = agruparSaludPorCategoria(datos);
            return convertirMapaANumerico(agrupado);
        }

        if (sectionNormalized.contains("segur")) {
            List<Seguridad> datos = gobierno
                ? seguridadDAO.obtenerSeguridadGobierno(ambito)
                : seguridadDAO.obtenerSeguridadONG(ambito);
            Map<String, Integer> agrupado = agruparSeguridadPorDelito(datos);
            return convertirMapaANumerico(agrupado);
        }

        if (sectionNormalized.contains("educ")) {
            List<Educacion> datos = gobierno
                ? agruparEducacionPorNivel(educacionDAO.obtenerEducacionGobierno(ambito))
                : agruparEducacionPorNivel(educacionDAO.obtenerEducacionONG(ambito));
            return ratioPorNivel(datos);
        }

        return new LinkedHashMap<>();
    }

    private Map<String, Double> convertirMapaANumerico(Map<String, ? extends Number> source) {
        Map<String, Double> result = new LinkedHashMap<>();
        for (Map.Entry<String, ? extends Number> entry : source.entrySet()) {
            result.put(entry.getKey(), toDouble(entry.getValue()));
        }
        return result;
    }

    private List<String> combinarCategorias(Map<String, Double> gobierno, Map<String, Double> ong) {
        List<String> categorias = new ArrayList<>(gobierno.keySet());
        for (String categoria : ong.keySet()) {
            if (!categorias.contains(categoria)) {
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    private void agregarTablaComparativa(Document document,
                                         Map<String, Double> datosGobierno,
                                         Map<String, Double> datosOng) {
        Table table = new Table(UnitValue.createPercentArray(new float[] {4, 2, 2, 2}));
        table.setWidth(UnitValue.createPercentValue(100));

        table.addHeaderCell(new Cell().add(new Paragraph("Categoría").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Gobierno").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("ONG").setBold()));
        table.addHeaderCell(new Cell().add(new Paragraph("Diferencia").setBold()));

        List<String> categorias = combinarCategorias(datosGobierno, datosOng);
        for (String categoria : categorias) {
            double valorGobierno = datosGobierno.getOrDefault(categoria, 0.0);
            double valorOng = datosOng.getOrDefault(categoria, 0.0);
            double diferencia = valorGobierno - valorOng;

            table.addCell(new Cell().add(new Paragraph(categoria)));
            table.addCell(new Cell().add(new Paragraph(formatNumber(valorGobierno))).setTextAlignment(TextAlignment.RIGHT));
            table.addCell(new Cell().add(new Paragraph(formatNumber(valorOng))).setTextAlignment(TextAlignment.RIGHT));
            table.addCell(new Cell().add(new Paragraph(formatSigned(diferencia))).setTextAlignment(TextAlignment.RIGHT));
        }

        document.add(table);
    }

    private void agregarResumenDiferencias(Document document,
                                           Map<String, Double> datosGobierno,
                                           Map<String, Double> datosOng) {
        List<String> categorias = combinarCategorias(datosGobierno, datosOng);
        if (categorias.isEmpty()) {
            return;
        }

        double totalGobierno = 0;
        double totalOng = 0;
        String categoriaMayorBrecha = "";
        double mayorBrechaAbs = -1;
        double brechaFirmadaMayor = 0;

        for (String categoria : categorias) {
            double valorGobierno = datosGobierno.getOrDefault(categoria, 0.0);
            double valorOng = datosOng.getOrDefault(categoria, 0.0);
            double diferencia = valorGobierno - valorOng;

            totalGobierno += valorGobierno;
            totalOng += valorOng;

            double brechaAbs = Math.abs(diferencia);
            if (brechaAbs > mayorBrechaAbs) {
                mayorBrechaAbs = brechaAbs;
                brechaFirmadaMayor = diferencia;
                categoriaMayorBrecha = categoria;
            }
        }

        double diferenciaTotal = totalGobierno - totalOng;
        double porcentajeVsOng = totalOng == 0 ? 0 : (diferenciaTotal / totalOng) * 100;

        String resumen = "Diferencia total Gobierno - ONG: " + formatSigned(diferenciaTotal) +
            " (" + formatSigned(porcentajeVsOng) + "% respecto a ONG). " +
            "La mayor brecha por categoría se observa en '" + categoriaMayorBrecha +
            "' con una diferencia de " + formatSigned(brechaFirmadaMayor) + ".";

        document.add(new Paragraph("Resumen de diferencias")
            .setBold()
            .setFontSize(11)
            .setMarginTop(8)
            .setMarginBottom(4));

        document.add(new Paragraph(resumen)
            .setFontSize(10.5f)
            .setMultipliedLeading(1.2f)
            .setMarginBottom(4));
    }

    private String formatNumber(double value) {
        return String.format(Locale.ROOT, "%,.2f", value);
    }

    private String formatSigned(double value) {
        return String.format(Locale.ROOT, "%+.2f", value);
    }

    private void openPdfIfPossible(File pdfFile) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(pdfFile);
            }
        } catch (Exception ignored) {
            // Si no se puede abrir automáticamente, el archivo ya quedó guardado.
        }
    }
}