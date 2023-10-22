package unnoba.poo.QRPriceTag.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.ignoringRequestMatchers("/login", "/register"))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/webjars/**", "/resources/**","/css/**", "/img/**", "/js/**", "/register", "/login").permitAll()
                        .anyRequest().fullyAuthenticated()
                )
                .formLogin((form) -> form
                        .loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?error=true")
                        .loginProcessingUrl("/login").usernameParameter("email").passwordParameter("password"))
                .logout((logout) -> logout
                        .permitAll().logoutSuccessUrl("/login?logout"));

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
