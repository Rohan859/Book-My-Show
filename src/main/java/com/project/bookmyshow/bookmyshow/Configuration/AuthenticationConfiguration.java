//package com.project.bookmyshow.bookmyshow.Configuration;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class AuthenticationConfiguration
//{
//    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)
//    {
//        UserDetails user= User
//                .withUsername("Rohan Kundu")
//                .password(passwordEncoder.encode("123"))
//                .roles("USER")
//                //.authorities("READ")
//                .build();
//
//        UserDetails admin=User
//                .withUsername("Subhajit Das")
//                .password(passwordEncoder.encode("1234"))
//                .roles("ADMIN")
//                //.authorities("READ","CREATE","DELETE")
//                .build();
//
//        return new InMemoryUserDetailsManager(user,admin);
//    }
//    @Bean
//    PasswordEncoder passwordEncoder()
//    {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
//    {
//        return httpSecurity.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers(
//                        "/movie/getAllMovie",
//                        "/movie/getMovieListByLanguage",
//                        "/movie/getMovieListByGenre")
//                .permitAll()
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/movie/addMovie","/movie/updateMovieAttribute")
//                .authenticated()
//                .and().formLogin().and().build();
//    }
//}
