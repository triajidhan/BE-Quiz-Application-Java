package com.triajiramadhan.quiz.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.triajiramadhan.quiz.filter.AuthorizationFilter;
import com.triajiramadhan.quiz.service.UserCandidateService;

@Configuration
public class SecurityConfig {

	@Bean
	public AuthenticationManager authenticationManager(final HttpSecurity http, final PasswordEncoder passwordEncoder,
			final UserCandidateService userCandidateService) throws Exception {
		return http.getSharedObject(AuthenticationManagerBuilder.class).userDetailsService(userCandidateService)
				.passwordEncoder(passwordEncoder).and().build();
	}
	
	@Bean
	public List<RequestMatcher> requestMatchers() {
		final List<RequestMatcher> matchers = new ArrayList<>();
		matchers.add(new AntPathRequestMatcher("/user-candidates/**", HttpMethod.POST.name()));
		matchers.add(new AntPathRequestMatcher("/login/**", HttpMethod.POST.name()));
		matchers.add(new AntPathRequestMatcher("/swagger-ui/**", HttpMethod.GET.name()));
		matchers.add(new AntPathRequestMatcher("/v3/**", HttpMethod.GET.name()));
		return matchers;
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web)->requestMatchers().forEach((r)-> web.ignoring().requestMatchers(r));
	}
	
	@Bean
	public SecurityFilterChain filterChain(final HttpSecurity http, final AuthorizationFilter authorizationFilter) throws Exception {
		http.cors();
		http.csrf().disable();
		http.addFilterAt(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
	@Bean
	public WebMvcConfigurer webMvcConfigurer() {
		return new WebMvcConfigurer() {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/**").allowedOrigins("http://localhost:3000").allowedMethods(HttpMethod.GET.toString(),
					HttpMethod.POST.toString(), HttpMethod.PUT.toString(), HttpMethod.DELETE.toString());
			WebMvcConfigurer.super.addCorsMappings(registry);
		}
		};
	}
}
