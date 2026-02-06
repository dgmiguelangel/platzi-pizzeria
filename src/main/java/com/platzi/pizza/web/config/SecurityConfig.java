package com.platzi.pizza.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// Clase de configuración de seguridad para la aplicación web
@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http
                .csrf(AbstractHttpConfigurer::disable) // Cross Site Request Forgery deshabilitado
                .sessionManagement(smc ->
                        smc.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Configurar la gestión de sesiones como sin estado
                )
                .authorizeHttpRequests(customizeRequests -> {
                        customizeRequests
                            .requestMatchers("/api/auth/**").permitAll()
                            .requestMatchers("/api/customers/**").hasAnyRole("ADMIN", "CUSTOMER")
                            .requestMatchers(HttpMethod.GET, "/api/pizzas/**").hasAnyRole("ADMIN", "CUSTOMER")
                            .requestMatchers(HttpMethod.POST, "/api/pizzas/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                            .requestMatchers("/api/orders/random").hasAuthority("random_order")
                            .requestMatchers("/api/orders/**").hasRole("ADMIN")
                            .anyRequest()
                            .authenticated();
                    }
                ).addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // Bycrypt es un algoritmo de hashing seguro para almacenar contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //@Bean
    public UserDetailsService memoryUsers() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();

        UserDetails customer = User.builder()
                .username("customer")
                .password(passwordEncoder().encode("customer123"))
                .roles("CUSTOMER")
                //.roles("CUSTOMER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin, customer);
    }

}

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

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) {
    http
        .csrf(AbstractHttpConfigurer::disable)
        //.cors(Customizer.withDefaults())
        .authorizeHttpRequests(customizeRequests -> {
                customizeRequests
                        .requestMatchers(HttpMethod.GET, "/api/pizzas/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.PUT)
                        .denyAll()
                        .anyRequest()
                        .authenticated();
            }
        ).httpBasic(Customizer.withDefaults());

    return http.build();
}

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) {
    http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(customizeRequests -> {
                    customizeRequests
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/customers/**").hasAnyRole("ADMIN", "CUSTOMER")
                        .requestMatchers(HttpMethod.GET, "/api/pizzas/**").hasAnyRole("ADMIN", "CUSTOMER")
                        .requestMatchers(HttpMethod.POST, "/api/pizzas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT).hasRole("ADMIN")
                        .requestMatchers("/api/orders/random").hasAuthority("random_order")
                        .requestMatchers("/api/orders/**").hasRole("ADMIN")
                        .anyRequest()
                        .authenticated();
                }
            ).httpBasic(Customizer.withDefaults());

    return http.build();
}
*/