ALTER TABLE pokemon ADD COLUMN imagen_url VARCHAR(255);

UPDATE pokemon p
SET imagen_url = 'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/' || s.numero || '.png'
FROM (VALUES
    ('Bulbasaur', 1), ('Charmander', 4), ('Squirtle', 7), ('Machop', 66),
    ('Pikachu', 25), ('Vulpix', 37), ('Jigglypuff', 39), ('Psyduck', 54),
    ('Mankey', 56), ('Hitmonlee', 106), ('Snorunt', 361), ('Staryu', 120),
    ('Lapras', 131), ('Eevee', 133), ('Snorlax', 143), ('Mewtwo', 150)
) AS s(nombre, numero)
WHERE p.nombre = s.nombre;
