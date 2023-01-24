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
import com.triajiramadhan.quiz.dto.usercandidate.UserCandidateDataDto;
import com.triajiramadhan.quiz.dto.usercandidate.UserCandidateInsertReqDto;
import com.triajiramadhan.quiz.dto.usercandidate.UserCandidateUpdateReqDto;
import com.triajiramadhan.quiz.service.UserCandidateService;



@RestController
@RequestMapping("user-candidates")
public class UserCandidateController {

	@Autowired
	private UserCandidateService userCandidateService;
	
	@PostMapping
	public ResponseEntity<BaseInsertResDto> insert(@Valid @RequestBody  final UserCandidateInsertReqDto data) {
		final BaseInsertResDto insertRes = userCandidateService.insert(data);
		return new ResponseEntity<>(insertRes, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<BaseUpdateResDto> update(@Valid @RequestBody  final UserCandidateUpdateReqDto data) {
		final BaseUpdateResDto updateRes = userCandidateService.update(data);
		return new ResponseEntity<>(updateRes, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<BaseDataGetResDto<UserCandidateDataDto>> getById(@PathVariable("id") final String id) {
		final BaseDataGetResDto<UserCandidateDataDto> userCandidate = userCandidateService.getById(id);
		return new ResponseEntity<>(userCandidate, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<BaseDeleteResDto> delete(@PathVariable("id")  final String id) {
		final BaseDeleteResDto deleteRes = userCandidateService.deleteById(id);
		return new ResponseEntity<>(deleteRes, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<BaseDataGetResDto<List<UserCandidateDataDto>>> getAll() {
		final BaseDataGetResDto<List<UserCandidateDataDto>> userCandidates = userCandidateService.getAll();
		return new ResponseEntity<>(userCandidates, HttpStatus.OK);
	}
}
