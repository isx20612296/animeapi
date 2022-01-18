package com.example.animeapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired private DataSource dataSource;

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/files/").permitAll()
                .mvcMatchers("/files/{id}").permitAll()
                .mvcMatchers("/users/").permitAll()
                .mvcMatchers("/users/{id}").permitAll()
                .mvcMatchers("/users/favorites").authenticated()
                .mvcMatchers("/user/follows").authenticated()
                .mvcMatchers("/animes/").permitAll()
                .mvcMatchers("/animes/{id}").permitAll()
                .mvcMatchers("/authors/").permitAll()
                .mvcMatchers("/authors/{id}").permitAll()
                .mvcMatchers("/genres/").permitAll()
                .mvcMatchers("/genres/{id}").permitAll()
                .anyRequest().permitAll()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password from users where username = ?")
                .authoritiesByUsernameQuery("select username from users where username = ?")
                .passwordEncoder(getPasswordEncoder());
    }
}
