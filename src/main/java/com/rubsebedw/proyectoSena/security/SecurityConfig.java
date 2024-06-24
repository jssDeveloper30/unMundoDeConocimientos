package com.rubsebedw.proyectoSena.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain SecurityfilterChain(HttpSecurity http) throws Exception {

        http
        .authorizeHttpRequests()
        .requestMatchers(AntPathRequestMatcher.antMatcher("/images/**")).permitAll()
        .requestMatchers(AntPathRequestMatcher.antMatcher("/css/**")).permitAll()
        .requestMatchers(AntPathRequestMatcher.antMatcher("/js/**")).permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
            .loginPage("/login") // Especificar la URL de tu página de login personalizada
            .defaultSuccessUrl("/", true)
            .permitAll()
            .and()
        // Habilitar el uso de H2 Console (solo para desarrollo)
        .csrf().disable()
        .headers().frameOptions().disable();
    
    return http.build();
		}
	
	@Bean
	UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
		UserDetails user = User.withUsername("sebas").password(passwordEnconder().encode("123")).authorities("read").build();
		UserDetails user2 = User.withUsername("ruben").password(passwordEnconder().encode("123")).authorities("read").build();
		userDetailsService.createUser(user);
		userDetailsService.createUser(user2);
		return userDetailsService;
	}
	
	@Bean
	BCryptPasswordEncoder passwordEnconder() {
		return new BCryptPasswordEncoder();
	}
	
}

