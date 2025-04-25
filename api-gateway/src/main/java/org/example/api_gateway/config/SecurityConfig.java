package org.example.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
//Axirinci defe bu classi yaratdin amma test elememisem
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity security) throws Exception{
       security.csrf(ServerHttpSecurity.CsrfSpec::disable)
               .authorizeExchange(exchange->exchange
                       .pathMatchers("/eureka/**").permitAll()
                       .anyExchange().authenticated())
               .oauth2ResourceServer(oath2->oath2.jwt(jwt->{}));
       return security.build();
    }
}
