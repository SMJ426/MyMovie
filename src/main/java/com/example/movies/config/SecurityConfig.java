package com.example.movies.config;

import com.example.movies.service.PrincipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig{
    private final PrincipalService principalService;
    private final DataSource dataSource;
    public SecurityConfig(PrincipalService principalService, DataSource dataSource) {
        this.principalService = principalService;
        this.dataSource = dataSource;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web)->web.ignoring()
                .requestMatchers("/resources");
    }
    /*@Bean
    PersistentTokenRepository tokenRepository(){
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
        try {
            repository.removeUserTokens("1");
        }catch (Exception e){
            repository.setCreateTableOnStartup(true);
        }
        return tokenRepository();
    }*/
    /*@Bean
    PersistentTokenBasedRememberMeServices rememberMeServices(){
        return new PersistentTokenBasedRememberMeServices("hi"
        , principalService,tokenRepository());
    }*/
    @Autowired
    protected void configuration(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(principalService);
    }
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                //페이지 권한 설정
                .authorizeHttpRequests(authorize-> authorize
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/img/**").permitAll()
                        //.requestMatchers("/user/like").hasRole("Member")
                        .requestMatchers("/admin/**").hasRole("Admin")

                        .anyRequest().permitAll()
                )
                //폼 로그인
                .formLogin(login->login
                        .loginPage("/loginPage")
                        .loginProcessingUrl("/user/login")
                        .defaultSuccessUrl("/",false)
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .failureUrl("/user/login"))
                //로그 아웃
                .logout(logout->logout
                                .logoutUrl("/user/logout")
                            .logoutSuccessUrl("/")
                                .deleteCookies("JSESSIONID")
                                .invalidateHttpSession(true)
                            );

                //.rememberMe(r-> r.rememberMeServices(rememberMeServices()));
        return http.build();
    }
}
