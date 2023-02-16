package com.triajiramadhan.quiz.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.triajiramadhan.quiz.dto.error.ErrorResDto;
import com.triajiramadhan.quiz.service.JwtService;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtService jwtService;
	@Autowired
	private List<RequestMatcher> requestMatchers;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
			final FilterChain filterChain) throws ServletException, IOException {
		final Long count = requestMatchers.stream().filter(m -> m.matches(request)).collect(Collectors.counting());

		if (!request.getRequestURI().equals("login") && count == 0) {
			final String header = request.getHeader("Authorization");
			if (header != null) {
				final String[] parts = header.split(" ");
				try {
					final Map<String, Object> parse = jwtService.parseJwt(parts[1]);
					final Authentication auth = new UsernamePasswordAuthenticationToken(parse.get("id"), null);
					SecurityContextHolder.getContext().setAuthentication(auth);
				} catch (Exception e) {
					e.printStackTrace();
					final ErrorResDto<String> errorResDto = new ErrorResDto<>();
					errorResDto.setMessage("Invalid Token");
					response.getWriter().append(objectMapper.writeValueAsString(errorResDto));
					response.setContentType("application/json");
					response.setStatus(HttpStatus.UNAUTHORIZED.value());
					return;
				}
			} else {
				throw new RuntimeException("Expired Token");
			}

		}
		filterChain.doFilter(request, response);
	}

}
