package com.eduardo.salaoDeBeleza.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers("/login").permitAll();
                    req.requestMatchers("/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll();

                    funcionariosURL(req);
                    agendamentosURL(req);
                    clientesURL(req);
                    trabalhosURL(req);
                    usuariosURL(req);
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    private static void funcionariosURL(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry req) {
        req.requestMatchers(HttpMethod.GET, "/funcionarios/**").hasAnyAuthority("ROLE_USER", "USER_FUNCIONARIO", "ROLE_ADMIN");
        req.requestMatchers(HttpMethod.POST, "/funcionarios/**").hasAnyAuthority("ROLE_ADMIN");
        req.requestMatchers(HttpMethod.PUT, "/funcionarios/**").hasAnyAuthority("ROLE_ADMIN");
        req.requestMatchers(HttpMethod.DELETE, "/funcionarios/**").hasAnyAuthority("ROLE_ADMIN");
    }

    private static void agendamentosURL(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry req) {
        req.requestMatchers(HttpMethod.GET, "/agendamentos/**").hasAnyAuthority("USER_FUNCIONARIO","ROLE_ADMIN");
        req.requestMatchers(HttpMethod.POST, "/agendamentos/**").hasAnyAuthority("ROLE_USER", "USER_FUNCIONARIO", "ROLE_ADMIN");
        req.requestMatchers(HttpMethod.PUT, "/agendamentos/**").hasAnyAuthority("ROLE_USER", "USER_FUNCIONARIO","ROLE_ADMIN");
        req.requestMatchers(HttpMethod.DELETE, "/agendamentos/**").hasAnyAuthority("ROLE_USER", "USER_FUNCIONARIO","ROLE_ADMIN");
    }

    private void usuariosURL(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry req) {
        req.requestMatchers(HttpMethod.GET, "/usuarios/**").hasAnyAuthority("ROLE_ADMIN");
        req.requestMatchers(HttpMethod.POST, "/usuarios/**").hasAnyAuthority("ROLE_USER", "USER_FUNCIONARIO", "ROLE_ADMIN");
        req.requestMatchers(HttpMethod.PUT, "/usuarios/**").hasAnyAuthority("ROLE_USER", "USER_FUNCIONARIO", "ROLE_ADMIN");
        req.requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasAnyAuthority("ROLE_USER", "USER_FUNCIONARIO", "ROLE_ADMIN");
    }

    private void trabalhosURL(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry req) {
        req.requestMatchers(HttpMethod.GET, "/trabalhos/**").hasAnyAuthority("USER_FUNCIONARIO", "ROLE_ADMIN");
        req.requestMatchers(HttpMethod.POST, "/trabalhos/**").hasAnyAuthority("USER_FUNCIONARIO", "ROLE_ADMIN");
        req.requestMatchers(HttpMethod.PUT, "/trabalhos/**").hasAnyAuthority("USER_FUNCIONARIO", "ROLE_ADMIN");
        req.requestMatchers(HttpMethod.DELETE, "/trabalhos/**").hasAnyAuthority("USER_FUNCIONARIO", "ROLE_ADMIN");
    }

    private void clientesURL(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry req) {
        req.requestMatchers(HttpMethod.GET, "/clientes/{id}").hasAnyAuthority("ROLE_USER");

        req.requestMatchers(HttpMethod.GET, "/clientes/**").hasAnyAuthority("ROLE_ADMIN");
        req.requestMatchers(HttpMethod.POST, "/clientes/**").hasAnyAuthority("ROLE_USER", "USER_FUNCIONARIO", "ROLE_ADMIN");
        req.requestMatchers(HttpMethod.PUT, "/clientes/**").hasAnyAuthority("ROLE_ADMIN");
        req.requestMatchers(HttpMethod.DELETE, "/clientes/**").hasAnyAuthority("ROLE_ADMIN");
    }
}
