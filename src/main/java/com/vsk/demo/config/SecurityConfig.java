package com.vsk.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/details").hasAnyRole("ADMIN","USER", "adil")
                .antMatchers(HttpMethod.POST,"/saveitem").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/deleteitem").hasAnyRole("ADMIN", "adil")
                .antMatchers(HttpMethod.POST,"/deleteitem").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/additem").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/additem").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();


    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
    return new InMemoryUserDetailsManager (
            User.builder()
                    .username("admin")
                    .password(passwordEncoder().encode("admin"))
                    .roles("ADMIN")
                    .build(),

            User.builder()
                    .username("user")
                    .password(passwordEncoder().encode("user"))
                    .roles("USER")
                    .build(),


            User.builder()
                    .username("adil")
                    .password(passwordEncoder().encode("adil"))
                    .roles("adil")
                    .build()


    );

    }


    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
