# Pokédex API 

Una API para guardar entrenadores y sus Pokémon. Hecha con Spring Boot y PostgreSQL.

## Lo que necesitas

- Java 17
- PostgreSQL

## Arrancarlo

1. Crea la base de datos:

   ```sql
   CREATE DATABASE pokedex;
   ```

2. Pon tu usuario y contraseña de Postgres en `src/main/resources/application.yaml`.

3. Corre el proyecto:

   ```bash
   ./mvnw spring-boot:run
   ```

   En Windows: `mvnw.cmd spring-boot:run`

La API esta en **http://localhost:8081** y ya trae algunos entrenadores y Pokémon de ejemplo.

## Endpoints

### Entrenadores

| Método | Ruta | Qué hace |
|---|---|---|
| GET | `/api/entrenadores` | Ver todos |
| GET | `/api/entrenadores/{id}` | Ver uno |
| POST | `/api/entrenadores` | Crear |
| PUT | `/api/entrenadores/{id}` | Editar |
| DELETE | `/api/entrenadores/{id}` | Desactivar |
## POST/PUT REQUEST

```json
{
  "nombre": "Misty"
}
```

### Pokémon

| Método | Ruta | Qué hace |
|---|---|---|
| GET | `/api/pokemon` | Ver todos |
| GET | `/api/pokemon/{id}` | Ver uno |
| POST | `/api/pokemon` | Crear |
| PUT | `/api/pokemon/{id}` | Editar |
| DELETE | `/api/pokemon/{id}` | Desactivar |

## POST/PUT REQUEST

```json
{
  "nombre": "Mewtwo",
  "tipo": { "id": 11 },
  "nivel": 70,
  "hp": 95,
  "fechaCaptura": "2026-09-01",
  "imagenUrl": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/150.png",
  "entrenador": { "id": 4 }
}
```

- `nivel` y `hp` van del 1 al 100.
- `tipo` es obligatorio; los ids salen de `/api/tipos`.
- `fechaCaptura`, `imagenUrl` y `entrenador` son opcionales.

### Tipos

| Método | Ruta | Qué hace |
|---|---|---|
| GET | `/api/tipos` | Ver todos |
| GET | `/api/tipos/{id}` | Ver uno |




## Importante

- **Borrar no borra:** el DELETE solo desactiva el registro, y ya no aparece en la API.
