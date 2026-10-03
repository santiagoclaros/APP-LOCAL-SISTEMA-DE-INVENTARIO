package com.gestioninventario.model;

public class Producto {
    private String id;
    private String nombre;
    private double precio;
    private int cantidadStock;

    public Producto(String id, String nombre, double precio, int cantidadStock) {
        setId(id);
        setNombre(nombre);
        setPrecio(precio);
        setCantidadStock(cantidadStock);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El ID es obligatorio.");
        }
        this.id = id.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio.");
        }
        this.nombre = nombre.trim();
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (!Double.isFinite(precio) || precio < 0) {
            throw new IllegalArgumentException("El precio debe ser un número válido mayor o igual que cero.");
        }
        this.precio = precio;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        if (cantidadStock < 0) {
            throw new IllegalArgumentException("El stock debe ser mayor o igual que cero.");
        }
        this.cantidadStock = cantidadStock;
    }
}
