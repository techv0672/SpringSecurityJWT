package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.filters.JwtCheckerFilter;
import com.example.demo.service.JwtUtil;
import com.example.demo.service.MyUserDetailsService;
import jakarta.servlet.DispatcherType;

@EnableWebSecurity
@Configuration
@EnableGlobalAuthentication
@RestController
public class SecurityConfig {

	@Autowired
	MyUserDetailsService userDetailsService;

	@Autowired
	JwtUtil jwtService;

	@Autowired
	BCryptPasswordEncoder encoder;

	@Autowired
	JwtCheckerFilter jwtFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(CsrfConfigurer::disable)
				.authorizeHttpRequests(
						requests -> requests.dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR)
								.permitAll().requestMatchers("/getJwtToken").permitAll().requestMatchers("/empInfo/*")
								.hasAuthority("ADMIN").requestMatchers("/extractJwt").permitAll())
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder);
		return authProvider;
	}

}
