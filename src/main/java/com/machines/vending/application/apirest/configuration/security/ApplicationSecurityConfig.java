package com.machines.vending.application.apirest.configuration.security;

import com.machines.vending.domain.models.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationEntryPoint authenticationEntryPoint;

    public ApplicationSecurityConfig(final AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();

        httpSecurity
                .authorizeRequests()
                .antMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/user").permitAll()
                .antMatchers("/deposit").hasRole(Role.BUYER.name())
                .antMatchers("/products").hasAnyRole(Role.BUYER.name(), Role.SELLER.name())
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll();

        httpSecurity.httpBasic().authenticationEntryPoint(authenticationEntryPoint);

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
