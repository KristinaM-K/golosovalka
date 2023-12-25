package com.golosovalka.config;

import com.golosovalka.security.CustomUserDetailsService;
import com.golosovalka.security.JwtAuthenticationEntryPoint;
import com.golosovalka.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//@Configuration - указывает на то, что это класс конфигурации Spring
@Configuration
//@EnableWebSecurity - включает поддержку безопасности на уровне веб-приложения
@EnableWebSecurity
//@EnableGlobalMethodSecurity - включает поддержку безопасности на уровне методов
// , позволяет использовать аннотации @Secured, @RolesAllowed и @PreAuthorize
@EnableGlobalMethodSecurity(
        securedEnabled = true, //securedEnabled = true - включает использование аннотации @Secured для ограничения доступа к методам
        jsr250Enabled = true,   //jsr250Enabled = true - включает использование аннотаций JSR-250, таких как @RolesAllowed, для ограничения доступа к методам
        prePostEnabled = true   //prePostEnabled = true - включает использование аннотаций @PreAuthorize и @PostAuthorize для ограничения доступа к методам до и после их выполнения соответственно.
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //@Autowired - позволяет Spring автоматически связывать зависимости
    @Autowired
    CustomUserDetailsService customUserDetailsService;
    //@Autowired - позволяет Spring автоматически связывать зависимости
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
    //@Bean - указывает на то, что метод создает новый объект и его можно использовать в других частях приложения
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
    //@Override - указывает на то, что метод переопределяет метод из суперкласса или интерфейса
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }
    //@Bean - указывает на то, что метод создает новый объект и его можно использовать в других частях приложения
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    //@authenticated - требует аутентификации для доступа
    //@cors - включает поддержку CORS (Cross-Origin Resource Sharing)
    //@csrf - отключает CSRF (Cross-Site Request Forgery) защиту
    //@exceptionHandling - конфигурирует обработку исключений
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .csrf()
                    .disable()
                .exceptionHandling()
                    .authenticationEntryPoint(unauthorizedHandler)
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .authorizeRequests()
                    .antMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                        .permitAll()
                    .antMatchers("/api/auth/**")
                        .permitAll()
                    .antMatchers("/api/user/checkUsernameAvailability", "/api/user/checkEmailAvailability")
                        .permitAll()
                    .antMatchers(HttpMethod.GET, "/api/polls/**", "/api/users/**")
                        .permitAll()
                    .anyRequest()
                        .authenticated();

        //@addFilterBefore - добавляет фильтр безопасности перед указанным фильтром. В данном случае,
        // добавляется фильтр для JWT-аутентификации перед фильтром аутентификации по имени пользователя и паролю.
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}