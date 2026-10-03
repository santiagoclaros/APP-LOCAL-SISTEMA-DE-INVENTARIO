package com.gestioninventario.view;

import com.gestioninventario.model.Producto;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainView extends BorderPane {
    private final TableView<Producto> tablaProductos = new TableView<>();
    private final TextField idField = new TextField();
    private final TextField nombreField = new TextField();
    private final TextField precioField = new TextField();
    private final TextField stockField = new TextField();
    private final Button agregarButton = new Button("Agregar");
    private final Button actualizarButton = new Button("Actualizar");
    private final Button eliminarButton = new Button("Eliminar");
    private final Button limpiarButton = new Button("Limpiar");
    private final Label estadoLabel = new Label(" ");

    public MainView() {
        setPadding(new Insets(16));
        setTop(crearEncabezado());
        setCenter(crearTabla());
        setBottom(crearFormulario());
        BorderPane.setMargin(tablaProductos, new Insets(12, 0, 12, 0));
    }

    private VBox crearEncabezado() {
        Label titulo = new Label("Gestión de inventario");
        titulo.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        Label subtitulo = new Label("Los productos se mantienen en memoria durante la sesión.");
        return new VBox(4, titulo, subtitulo);
    }

    private TableView<Producto> crearTabla() {
        TableColumn<Producto, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Producto, String> nombreColumn = new TableColumn<>("Nombre");
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Producto, Double> precioColumn = new TableColumn<>("Precio");
        precioColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<Producto, Integer> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("cantidadStock"));

        tablaProductos.getColumns().setAll(idColumn, nombreColumn, precioColumn, stockColumn);
        tablaProductos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);
        tablaProductos.setPlaceholder(new Label("No hay productos registrados."));
        return tablaProductos;
    }

    private VBox crearFormulario() {
        GridPane campos = new GridPane();
        campos.setHgap(10);
        campos.setVgap(8);
        campos.setAlignment(Pos.CENTER_LEFT);

        idField.setPromptText("Ej. P-001");
        nombreField.setPromptText("Nombre del producto");
        precioField.setPromptText("Ej. 19.99");
        stockField.setPromptText("Ej. 10");

        campos.add(new Label("ID"), 0, 0);
        campos.add(idField, 1, 0);
        campos.add(new Label("Nombre"), 2, 0);
        campos.add(nombreField, 3, 0);
        campos.add(new Label("Precio"), 0, 1);
        campos.add(precioField, 1, 1);
        campos.add(new Label("Stock"), 2, 1);
        campos.add(stockField, 3, 1);
        GridPane.setHgrow(idField, javafx.scene.layout.Priority.ALWAYS);
        GridPane.setHgrow(nombreField, javafx.scene.layout.Priority.ALWAYS);
        GridPane.setHgrow(precioField, javafx.scene.layout.Priority.ALWAYS);
        GridPane.setHgrow(stockField, javafx.scene.layout.Priority.ALWAYS);

        HBox botones = new HBox(8, agregarButton, actualizarButton, eliminarButton, limpiarButton);
        botones.setAlignment(Pos.CENTER_LEFT);
        return new VBox(10, campos, botones, estadoLabel);
    }

    public TableView<Producto> getTablaProductos() {
        return tablaProductos;
    }

    public TextField getIdField() {
        return idField;
    }

    public TextField getNombreField() {
        return nombreField;
    }

    public TextField getPrecioField() {
        return precioField;
    }

    public TextField getStockField() {
        return stockField;
    }

    public Button getAgregarButton() {
        return agregarButton;
    }

    public Button getActualizarButton() {
        return actualizarButton;
    }

    public Button getEliminarButton() {
        return eliminarButton;
    }

    public Button getLimpiarButton() {
        return limpiarButton;
    }

    public Label getEstadoLabel() {
        return estadoLabel;
    }
}
