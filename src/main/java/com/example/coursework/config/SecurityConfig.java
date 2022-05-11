package com.example.coursework.config;

import com.example.coursework.config.cookie.CustomFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                .cors()
                    .disable()
                .authorizeRequests()
                    .antMatchers( "/registration", "/login", "/index", "/commodes",
                            "/stelagi", "/wardrobes", "/",
                            "/scripts/**", "/styles/**", "/images/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .addFilterBefore(new CustomFilter("/login", authenticationManager()),
                            UsernamePasswordAuthenticationFilter.class)
                    .formLogin()
                    .failureUrl("/login?error=true")
                    .defaultSuccessUrl("/contacts")
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/")
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(encoder())
                .usersByUsernameQuery(
                        "select login, password, '1' from users where login=?")
                .authoritiesByUsernameQuery(
                        "select u.login, ur.roles from users u inner join user_role " +
                                "ur on u.id = ur.user_id where u.login=?");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10);
    }
}

