package com.example.hackaton_api.Config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class config extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").anonymous()
                .antMatchers(HttpMethod.POST, "/**").anonymous()
                .antMatchers(HttpMethod.PUT, "/**").anonymous()
                .antMatchers(HttpMethod.DELETE, "/**").anonymous()
                .and()
                .formLogin()
                .and()
                .logout().permitAll()
                .and()
                .csrf()
                .disable()
                .httpBasic();
    }
}
