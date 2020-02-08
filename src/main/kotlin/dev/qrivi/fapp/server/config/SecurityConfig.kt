package dev.qrivi.fapp.server.config

import dev.qrivi.fapp.server.security.JwtAuthorizationFilter
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(JwtAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource? {
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", CorsConfiguration().applyPermitDefaultValues())
        return source
    }
}