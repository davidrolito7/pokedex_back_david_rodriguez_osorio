CREATE TABLE pokemon (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    nivel INT NOT NULL,
    fecha_captura DATE,
    entrenador_id BIGINT REFERENCES entrenador(id),
    activo BOOLEAN NOT NULL DEFAULT TRUE
);