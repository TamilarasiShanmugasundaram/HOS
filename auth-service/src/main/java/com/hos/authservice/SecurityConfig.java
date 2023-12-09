package com.hos.authservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.hos.authservice.auth.AuthenticationFailure;
import com.hos.authservice.auth.AuthenticationProvider;
import com.hos.authservice.auth.AuthenticationSuccess;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
	@Bean
	public AuthenticationSuccess authenticationSuccess() {
		return new AuthenticationSuccess();
	}

	@Bean
	public AuthenticationFailure authenticationFailure() {
		return new AuthenticationFailure();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		return new AuthenticationProvider();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(Boolean.TRUE);
		config.addAllowedOriginPattern("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod(HttpMethod.HEAD);
		config.addAllowedMethod(HttpMethod.GET);
		config.addAllowedMethod(HttpMethod.PUT);
		config.addAllowedMethod(HttpMethod.POST);
		config.addAllowedMethod(HttpMethod.DELETE);
		config.addAllowedMethod(HttpMethod.PATCH);
		config.addAllowedMethod(HttpMethod.OPTIONS);
		source.registerCorsConfiguration("*" 
			+ "/" + "*", config);
		return source;
	}

    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> 
                requests.anyRequest()
				.authenticated()
        ).formLogin((form) -> form
                .loginProcessingUrl("/session").usernameParameter("username").passwordParameter("password")
                .successHandler(authenticationSuccess()).failureHandler(authenticationFailure())
        ).exceptionHandling((exception) -> exception
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
        ).logout((logout) -> logout
                .logoutUrl("/logout")//.deleteCookies("JSESSIONID").invalidateHttpSession(true)
                // .addLogoutHandler(logoutSuccess())
                //.logoutSuccessHandler(logoutSuccess())
        ).csrf((csrf) -> csrf.disable());//.sessionManagement((session) -> session
		//.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		//http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.build();
	}
}
