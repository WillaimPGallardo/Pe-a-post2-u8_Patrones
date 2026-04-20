# Sistema de Inventario – CQRS + Clean Architecture

## 1. Descripción

Este proyecto implementa un sistema de gestión de inventario utilizando **Spring Boot** bajo el patrón **CQRS (Command Query Responsibility Segregation)** y principios de **Clean Architecture**.

El sistema separa explícitamente las operaciones de:

* **Command (escritura / modificación del estado)**
* **Query (lectura / consulta de datos)**

Esta separación permite un diseño desacoplado, escalable y mantenible.

---

## 2. Objetivo

Aplicar una arquitectura limpia que:

* Separe responsabilidades entre lectura y escritura
* Mantenga el dominio independiente de frameworks
* Garantice reglas de negocio coherentes
* Cumpla con los lineamientos del taller académico 

---

## 3. Arquitectura

### 3.1 Domain (Núcleo)

Contiene la lógica de negocio pura:

* `Producto` → entidad con reglas de negocio
* `ProductoId` → identificador único (Value Object)
* `StockInsuficienteException` → validación de dominio

📌 Restricción:
El dominio **no depende de Spring ni de JPA**

---

### 3.2 Command Side (Write)

Responsable de modificar el estado:

* Commands:

  * `AgregarProductoCommand`
  * `ActualizarStockCommand`
  * `EliminarProductoCommand`

* Handlers:

  * `AgregarProductoHandler`
  * `ActualizarStockHandler`
  * `EliminarProductoHandler`

* Repository:

  * `ProductoWriteRepository`
  * `ProductoWriteRepositoryImpl` (en memoria)

---

### 3.3 Query Side (Read)

Responsable únicamente de consultas:

* Queries:

  * `BuscarProductoQuery`
  * `ListarProductosQuery`

* Model:

  * `ProductoView` (DTO optimizado)

* Handlers:

  * `BuscarProductoQueryHandler`
  * `ListarProductosQueryHandler`

* Repository:

  * `ProductoReadRepository`
  * `ProductoReadRepositoryImpl`

📌 No modifica datos → cumple CQRS

---

### 3.4 Adapter (Web)

* `ProductoController`
* Expone endpoints REST
* Separa rutas de Command y Query

---

## 4. Estructura del Proyecto

```plaintext id="f3nyqn"
com.example.inventariocqrs
├── domain
├── command
├── query
├── adapter
└── InventariocqrsApplication
```

---

## 5. Tecnologías Utilizadas

* Java 17
* Spring Boot 3
* Maven
* Arquitectura CQRS
* Clean Architecture

---

## 6. Ejecución del Proyecto

En la terminal:

```bash id="gtg82k"
mvn spring-boot:run
```

Servidor disponible en:

```text id="c82zqv"
http://localhost:8080
```

---

## 7. Endpoints

### 🔹 Crear producto

```http id="04cfdb"
POST /api/inventario/productos
```

**Body:**

```json id="u0h9d2"
{
  "nombre": "Laptop",
  "categoria": "Tech",
  "precioUnitario": 1500,
  "stockInicial": 10
}
```

**Respuesta:**

```json id="h42xka"
{
  "id": "uuid-generado"
}
```

---

### 🔹 Actualizar stock

```http id="c4pfap"
PATCH /api/inventario/productos/{id}/stock
```

**Body:**

```json id="j4q3l5"
{
  "delta": -2,
  "motivo": "venta"
}
```

---

### 🔹 Listar productos

```http id="1tf4rb"
GET /api/inventario/productos
```

Parámetro opcional:

```text id="78k59o"
?soloDisponibles=true
```

---

### 🔹 Buscar producto por ID

```http id="x2z9m3"
GET /api/inventario/productos/{id}
```

---

## 8. Reglas de Negocio

* El nombre del producto es obligatorio
* El precio debe ser mayor a 0
* El stock no puede ser negativo
* No se puede reducir el stock por debajo de cero

Estado del stock:

* `0` → AGOTADO
* `< 5` → BAJO
* `≥ 5` → DISPONIBLE

---

## 9. Validación de CQRS

Se cumple correctamente:

* ✔ CommandHandlers solo escriben
* ✔ QueryHandlers solo leen
* ✔ Separación completa de responsabilidades
* ✔ Dominio independiente de frameworks

---

## 10. Pruebas del Sistema

1. Crear producto (POST)<img width="1905" height="1034" alt="image" src="https://github.com/user-attachments/assets/97f4d29d-058e-4a19-8c2d-a5e4ef1d2fef" />

2. Listar productos (GET)
3. Actualizar stock (PATCH)<img width="1897" height="910" alt="image" src="https://github.com/user-attachments/assets/7b8960c9-fee0-4922-9205-5b423b53ee32" />

4. Validar reglas de negocio
<img width="1441" height="834" alt="image" src="https://github.com/user-attachments/assets/2f4e21e5-a745-4832-8ff4-85a8aa4c0d4d" />

---

## 11. Conclusión

El sistema implementa correctamente CQRS y Clean Architecture, logrando:

* Bajo acoplamiento
* Alta cohesión
* Separación de responsabilidades
* Escalabilidad

Se evidencia una clara división entre dominio, lógica de aplicación y capa de presentación, cumpliendo con las buenas prácticas de ingeniería de software.

---
