package com.webapp.config;
import javax.security.auth.login.AppConfigurationEntry;

//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.jaas.AuthorityGranter;
//import org.springframework.security.authentication.jaas.DefaultJaasAuthenticationProvider;
//import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//
//
//import com.webapp.loginmodule.CustomAuthorityGranter;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.util.HashMap;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    CustomAuthorityGranter customAuthorityGranter;
//    private final JwtAuthenticationFilter jwtAuthFilter;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(jaasAuthProvider());
//    }
//
//    @Bean
//    DefaultJaasAuthenticationProvider jaasAuthProvider() {
//        AppConfigurationEntry appConfig = new AppConfigurationEntry("com.akua.loginmodule.CustomLoginModule",
//                AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, new HashMap<String, Object>());
//
//        InMemoryConfiguration memoryConfig = new InMemoryConfiguration(new AppConfigurationEntry[] { appConfig });
//
//        DefaultJaasAuthenticationProvider def = new DefaultJaasAuthenticationProvider();
//
//        def.setConfiguration(memoryConfig);
//        def.setAuthorityGranters(new AuthorityGranter[] { customAuthorityGranter });
//
//        return def;
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable()
//                .authorizeHttpRequests()
//
//                .antMatchers("/swagger-ui/**").permitAll()
//                .antMatchers("/rutube.ru/admin/moderators").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/rutube.ru/movies").hasAnyRole("ADMIN", "MODERATOR")
//                .antMatchers(HttpMethod.POST, "/rutube.ru/genres").hasAnyRole("ADMIN", "MODERATOR")
//                .antMatchers("/rutube.ru/cards", "/rutube.ru/subscription").hasAnyRole("ADMIN", "MODERATOR", "USER")
//                .antMatchers(HttpMethod.GET, "/rutube.ru/movies").hasAnyRole("ADMIN", "MODERATOR", "USER")
//                .antMatchers(HttpMethod.DELETE, "/rutube.ru/video/*").hasAnyRole("ADMIN", "MODERATOR")
//                .antMatchers(HttpMethod.POST, "/rutube.ru/video/*").hasAnyRole("ADMIN", "MODERATOR", "USER")
//                .antMatchers("/rutube.ru/registration", "/rutube.ru/authentication").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(jaasAuthProvider());
//    }
//}

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.jaas.AbstractJaasAuthenticationProvider;
import org.springframework.security.authentication.jaas.JaasAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AbstractJaasAuthenticationProvider jaasAuthenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/rutube.ru/admin/moderators").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/rutube.ru/movies").hasAnyRole("ADMIN", "MODERATOR")
                .requestMatchers(HttpMethod.POST, "/rutube.ru/genres").hasAnyRole("ADMIN", "MODERATOR")
                .requestMatchers("/rutube.ru/cards", "/rutube.ru/subscription").hasAnyRole("ADMIN", "MODERATOR", "USER")
                .requestMatchers(HttpMethod.GET, "/rutube.ru/movies").hasAnyRole("ADMIN", "MODERATOR", "USER")
                .requestMatchers(HttpMethod.DELETE, "/rutube.ru/video/*").hasAnyRole("ADMIN", "MODERATOR")
                .requestMatchers(HttpMethod.POST, "/rutube.ru/video/*").hasAnyRole("ADMIN", "MODERATOR", "USER")
                .requestMatchers("/rutube.ru/registration", "/rutube.ru/authentication").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(jaasAuthenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
