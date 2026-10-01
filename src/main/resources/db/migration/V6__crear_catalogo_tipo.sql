-- Catálogo de tipos
CREATE TABLE tipo (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL UNIQUE,
    descripcion VARCHAR(255)
);

INSERT INTO tipo (nombre, descripcion) VALUES
    ('Normal',    'Pokémon versátiles sin afinidad elemental particular.'),
    ('Fuego',     'Dominan las llamas; fuertes contra Planta, Bicho, Hielo y Acero.'),
    ('Agua',      'Viven en mares y ríos; fuertes contra Fuego, Tierra y Roca.'),
    ('Planta',    'Usan el poder de la naturaleza; fuertes contra Agua, Tierra y Roca.'),
    ('Eléctrico', 'Generan electricidad; fuertes contra Agua y Volador.'),
    ('Hielo',     'Resisten el frío extremo; fuertes contra Planta, Tierra, Volador y Dragón.'),
    ('Lucha',     'Expertos en combate cuerpo a cuerpo; fuertes contra Normal, Roca, Hielo, Siniestro y Acero.');


ALTER TABLE pokemon ADD COLUMN tipo_id BIGINT REFERENCES tipo(id);
--normalizamos 0.o
UPDATE pokemon p
SET tipo_id = t.id
FROM tipo t
WHERE t.nombre = p.tipo;

ALTER TABLE pokemon ALTER COLUMN tipo_id SET NOT NULL;
ALTER TABLE pokemon DROP COLUMN tipo;
