package com.kuropatin.zenbooking.config;

import com.kuropatin.zenbooking.model.Roles;
import com.kuropatin.zenbooking.security.filter.JwtAuthenticationFilter;
import com.kuropatin.zenbooking.security.service.CustomUserDetailsService;
import com.kuropatin.zenbooking.security.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final List<String> AUTHORIZED_ENDPOINTS = List.of("/profile", "/hosting", "/booking", "/order", "/review", "/moderation", "/administration");

    private final CustomUserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilterBean(final AuthenticationManager authenticationManager) {
        final JwtAuthenticationFilter authenticationTokenFilter = new JwtAuthenticationFilter(jwtUtils, userDetailsService);
        authenticationTokenFilter.setAuthenticationManager(authenticationManager);
        return authenticationTokenFilter;
    }

    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:8080");
        config.addAllowedOrigin("http://localhost:8787");
        config.addAllowedOrigin("http://localhost:63342");
        config.setAllowedHeaders(List.of(CorsConfiguration.ALL));
        config.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Autowired
    public void configureAuthentication(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .httpBasic().disable()
                .csrf().disable()
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthenticationFilterBean(authenticationManagerBean()), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/error/**").permitAll()
                .antMatchers("/api/v1/register").permitAll()
                .antMatchers("/api/v1/login").permitAll()
                .antMatchers("/api/v1/profile/**").hasRole(Roles.ROLE_USER.label)
                .antMatchers("/api/v1/hosting/**").hasRole(Roles.ROLE_USER.label)
                .antMatchers("/api/v1/booking/**").hasRole(Roles.ROLE_USER.label)
                .antMatchers("/api/v1/order/**").hasRole(Roles.ROLE_USER.label)
                .antMatchers("/api/v1/review/**").hasRole(Roles.ROLE_USER.label)
                .antMatchers("/api/v1/moderation/**").hasRole(Roles.ROLE_MODER.label)
                .antMatchers("/api/v1/administration/**").hasRole(Roles.ROLE_ADMIN.label)
                .anyRequest().authenticated();
    }
}