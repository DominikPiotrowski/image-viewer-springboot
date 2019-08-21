package com.dominikpiotrowski.imageviewerspringboot.security;

import com.dominikpiotrowski.imageviewerspringboot.models.User;
import com.dominikpiotrowski.imageviewerspringboot.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.dominikpiotrowski.imageviewerspringboot.services.UserDetailsServiceImplement;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImplement userDetailsService;
    private UserRepository userRepository;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImplement userDetailsService, UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository =  userRepository;
    }

    //creates user for practice
    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        User testUser = new User("Jan", getPasswordEncoder().encode("123"), "ROLE_USER");
        User testAdmin = new User("Olek", getPasswordEncoder().encode("321"), "ROLE_ADMIN");
        userRepository.save(testUser);
        userRepository.save(testAdmin);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .antMatchers("/gallery").hasAnyRole("ADMIN","USER")
               .antMatchers("/upload").hasRole("ADMIN")
               .and()
               .formLogin().permitAll()
               .and()
               .csrf().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(5);
    }
}
