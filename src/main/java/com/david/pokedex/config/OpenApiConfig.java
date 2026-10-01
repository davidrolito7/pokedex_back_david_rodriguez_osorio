package com.david.pokedex.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI pokedexOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pokédex API")
                        .description("API para gestionar entrenadores, Pokémon y tipos")
                        .version("1.0"));
    }
}
