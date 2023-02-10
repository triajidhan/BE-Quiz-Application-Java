package com.triajiramadhan.quiz.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triajiramadhan.quiz.dto.base.BaseDataGetResDto;
import com.triajiramadhan.quiz.dto.base.BaseDeleteResDto;
import com.triajiramadhan.quiz.dto.base.BaseInsertResDto;
import com.triajiramadhan.quiz.dto.base.BaseUpdateResDto;
import com.triajiramadhan.quiz.dto.user.UserDataDto;
import com.triajiramadhan.quiz.dto.user.UserInsertReqDto;
import com.triajiramadhan.quiz.dto.user.UserUpdateReqDto;
import com.triajiramadhan.quiz.service.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;


@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<BaseInsertResDto> insert(@Valid @RequestBody  final UserInsertReqDto data) {
		final BaseInsertResDto insertRes = userService.insert(data);
		return new ResponseEntity<>(insertRes, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<BaseUpdateResDto> update(@Valid @RequestBody  final UserUpdateReqDto data) {
		final BaseUpdateResDto updateRes = userService.update(data);
		return new ResponseEntity<>(updateRes, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<BaseDataGetResDto<UserDataDto>> getById(@PathVariable("id") final String id) {
		final BaseDataGetResDto<UserDataDto> user = userService.getById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<BaseDeleteResDto> delete(@PathVariable("id")  final String id) {
		final BaseDeleteResDto deleteRes = userService.deleteById(id);
		return new ResponseEntity<>(deleteRes, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<BaseDataGetResDto<List<UserDataDto>>> getAll() {
		final BaseDataGetResDto<List<UserDataDto>> users = userService.getAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
