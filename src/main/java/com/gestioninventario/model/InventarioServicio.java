package com.gestioninventario.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventarioServicio {
    private final ArrayList<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        if (buscarIndice(producto.getId()) >= 0) {
            throw new IllegalArgumentException("Ya existe un producto con el ID " + producto.getId() + ".");
        }
        productos.add(producto);
    }

    public List<Producto> listarProductos() {
        return Collections.unmodifiableList(new ArrayList<>(productos));
    }

    public void actualizarProducto(String idActual, Producto productoActualizado) {
        if (idActual == null || idActual.isBlank()) {
            throw new IllegalArgumentException("El ID del producto que se actualizará es obligatorio.");
        }
        if (productoActualizado == null) {
            throw new IllegalArgumentException("El producto actualizado no puede ser nulo.");
        }

        int indice = buscarIndice(idActual);
        if (indice < 0) {
            throw new IllegalArgumentException("No existe un producto con el ID " + idActual + ".");
        }

        int indiceConNuevoId = buscarIndice(productoActualizado.getId());
        if (indiceConNuevoId >= 0 && indiceConNuevoId != indice) {
            throw new IllegalArgumentException("Ya existe un producto con el ID " + productoActualizado.getId() + ".");
        }
        productos.set(indice, productoActualizado);
    }

    public void eliminarProducto(String id) {
        int indice = buscarIndice(id);
        if (indice < 0) {
            throw new IllegalArgumentException("No existe un producto con el ID " + id + ".");
        }
        productos.remove(indice);
    }

    private int buscarIndice(String id) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
}
