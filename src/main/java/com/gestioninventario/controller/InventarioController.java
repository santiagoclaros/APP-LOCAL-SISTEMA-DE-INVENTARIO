package com.gestioninventario.controller;

import com.gestioninventario.model.InventarioServicio;
import com.gestioninventario.model.Producto;
import com.gestioninventario.view.MainView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class InventarioController {
    private final InventarioServicio inventarioServicio;
    private final MainView vista;
    private final ObservableList<Producto> productos;

    public InventarioController(InventarioServicio inventarioServicio, MainView vista) {
        this.inventarioServicio = inventarioServicio;
        this.vista = vista;
        this.productos = FXCollections.observableArrayList(inventarioServicio.listarProductos());
        vista.getTablaProductos().setItems(productos);
        configurarEventos();
    }

    private void configurarEventos() {
        vista.getAgregarButton().setOnAction(event -> agregarProducto());
        vista.getActualizarButton().setOnAction(event -> actualizarProducto());
        vista.getEliminarButton().setOnAction(event -> eliminarProducto());
        vista.getLimpiarButton().setOnAction(event -> limpiarFormulario());
        vista.getTablaProductos().getSelectionModel().selectedItemProperty()
                .addListener((observable, anterior, seleccionado) -> mostrarProducto(seleccionado));
    }

    private void agregarProducto() {
        try {
            inventarioServicio.agregarProducto(leerProductoFormulario());
            actualizarTabla();
            limpiarFormulario();
            mostrarEstado("Producto agregado.");
        } catch (IllegalArgumentException exception) {
            mostrarError(exception.getMessage());
        }
    }

    private void actualizarProducto() {
        Producto seleccionado = vista.getTablaProductos().getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarError("Selecciona un producto de la tabla para actualizarlo.");
            return;
        }

        try {
            inventarioServicio.actualizarProducto(seleccionado.getId(), leerProductoFormulario());
            actualizarTabla();
            limpiarFormulario();
            mostrarEstado("Producto actualizado.");
        } catch (IllegalArgumentException exception) {
            mostrarError(exception.getMessage());
        }
    }

    private void eliminarProducto() {
        Producto seleccionado = vista.getTablaProductos().getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarError("Selecciona un producto de la tabla para eliminarlo.");
            return;
        }

        try {
            inventarioServicio.eliminarProducto(seleccionado.getId());
            actualizarTabla();
            limpiarFormulario();
            mostrarEstado("Producto eliminado.");
        } catch (IllegalArgumentException exception) {
            mostrarError(exception.getMessage());
        }
    }

    private Producto leerProductoFormulario() {
        String id = vista.getIdField().getText();
        String nombre = vista.getNombreField().getText();
        String precioTexto = vista.getPrecioField().getText();
        String stockTexto = vista.getStockField().getText();

        if (id == null || id.isBlank() || nombre == null || nombre.isBlank()
                || precioTexto == null || precioTexto.isBlank()
                || stockTexto == null || stockTexto.isBlank()) {
            throw new IllegalArgumentException("Completa todos los campos.");
        }

        try {
            double precio = Double.parseDouble(precioTexto.trim());
            int stock = Integer.parseInt(stockTexto.trim());
            return new Producto(id, nombre, precio, stock);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Precio o stock tienen un formato numérico inválido.");
        }
    }

    private void mostrarProducto(Producto producto) {
        if (producto == null) {
            return;
        }
        vista.getIdField().setText(producto.getId());
        vista.getNombreField().setText(producto.getNombre());
        vista.getPrecioField().setText(Double.toString(producto.getPrecio()));
        vista.getStockField().setText(Integer.toString(producto.getCantidadStock()));
        mostrarEstado("Producto seleccionado.");
    }

    private void actualizarTabla() {
        productos.setAll(inventarioServicio.listarProductos());
    }

    private void limpiarFormulario() {
        vista.getTablaProductos().getSelectionModel().clearSelection();
        vista.getIdField().clear();
        vista.getNombreField().clear();
        vista.getPrecioField().clear();
        vista.getStockField().clear();
        vista.getEstadoLabel().setText(" ");
    }

    private void mostrarEstado(String mensaje) {
        vista.getEstadoLabel().setText(mensaje);
    }

    private void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("No se pudo completar la operación");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
