# Sistema de Gestión de Inventario

Aplicación de escritorio desarrollada en **Java** con **JavaFX** y **Maven** para la gestión y control de productos en inventario local.

## Características

- **Gestión de productos**: Registrar, actualizar, listar y eliminar productos.
- **Campos del producto**:
  - Identificador único (ID).
  - Nombre del producto.
  - Precio unitario.
  - Cantidad en stock.
- **Validaciones**: Verificación de campos obligatorios, valores numéricos positivos y unicidad de ID.
- **Interfaz gráfica**: Construida con JavaFX para una experiencia de usuario clara e intuitiva.

## Requisitos

- **Java JDK**: 17 o superior.
- **Maven**: 3.8+ (o el wrapper si está disponible).

## Ejecución del Proyecto

Para ejecutar la aplicación localmente usando Maven:

```bash
mvn clean javafx:run
```

Para compilar el proyecto:

```bash
mvn clean compile
```

## Estructura del Proyecto

```text
src/
└── main/
    └── java/
        └── com/gestioninventario/
            ├── Main.java               # Punto de entrada de la aplicación
            ├── controller/             # Controladores de la interfaz
            ├── model/                  # Modelos de datos y lógica de negocio
            └── view/                   # Vistas y componentes JavaFX
```
