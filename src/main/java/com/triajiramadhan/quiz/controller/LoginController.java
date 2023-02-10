package com.triajiramadhan.quiz.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triajiramadhan.quiz.dto.login.LoginReqDto;
import com.triajiramadhan.quiz.dto.login.LoginResDto;
import com.triajiramadhan.quiz.model.UserCandidate;
import com.triajiramadhan.quiz.service.JwtService;
import com.triajiramadhan.quiz.service.UserCandidateService;


@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserCandidateService userCandidateService;
	@Autowired
	private JwtService jwtService;
	
	
	@PostMapping
	public ResponseEntity<LoginResDto> login(@RequestBody final LoginReqDto data) {
		final Authentication auth = new UsernamePasswordAuthenticationToken(data.getUserName(), data.getPassword());
		authenticationManager.authenticate(auth);
		final Optional<UserCandidate> user = userCandidateService.getByUserName(data.getUserName());		
		final Map<String, Object> claims = new HashMap<>();
		
		final Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, 1);
		claims.put("exp", cal.getTime());
		
		claims.put("id", user.get().getId());
		
		final LoginResDto loginResDto = new LoginResDto();
		loginResDto.setId(user.get().getId());
		loginResDto.setFullName(user.get().getFullName());
		loginResDto.setEmail(user.get().getEmail());
		loginResDto.setUserName(user.get().getUserName());
		loginResDto.setToken(jwtService.generateJwt(claims));
		
		return new ResponseEntity<LoginResDto>(loginResDto, HttpStatus.OK);
	}
}
