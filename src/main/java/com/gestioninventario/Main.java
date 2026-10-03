package com.gestioninventario;

import com.gestioninventario.controller.InventarioController;
import com.gestioninventario.model.InventarioServicio;
import com.gestioninventario.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        InventarioServicio inventarioServicio = new InventarioServicio();
        MainView vista = new MainView();
        new InventarioController(inventarioServicio, vista);

        stage.setTitle("Gestión de inventario");
        stage.setScene(new Scene(vista, 800, 520));
        stage.setMinWidth(650);
        stage.setMinHeight(420);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
