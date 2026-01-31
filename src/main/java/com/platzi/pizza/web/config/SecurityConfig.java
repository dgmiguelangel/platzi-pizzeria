package com.platzi.pizza.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

// Clase de configuración de seguridad para la aplicación web
@Configuration
public class SecurityConfig {

    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(customizeRequests -> {
                // Definir las reglas de autorización para las solicitudes HTTP
                            customizeRequests
                                    .anyRequest()
                                    .permitAll();
                        }
                );

        return http.build();
    }
    */


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http
            .csrf(AbstractHttpConfigurer::disable)
            //.cors(Customizer.withDefaults())
            .authorizeHttpRequests(customizeRequests -> {
                    customizeRequests
                            .anyRequest()
                            .authenticated();
                }
            ).httpBasic(Customizer.withDefaults());

        return http.build();
    }

}