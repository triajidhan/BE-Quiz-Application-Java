package com.triajiramadhan.quiz.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triajiramadhan.quiz.dto.base.BaseDataGetResDto;
import com.triajiramadhan.quiz.dto.base.BaseInsertResDto;
import com.triajiramadhan.quiz.dto.base.BaseUpdateResDto;
import com.triajiramadhan.quiz.dto.question.QuestionDataDto;
import com.triajiramadhan.quiz.dto.question.QuestionInsertReqDto;
import com.triajiramadhan.quiz.dto.question.QuestionUpdateReqDto;
import com.triajiramadhan.quiz.service.QuestionService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("questions")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@PostMapping
	public ResponseEntity<BaseInsertResDto> insert(@Valid @RequestBody  final QuestionInsertReqDto data) {
		final BaseInsertResDto insertRes = questionService.insert(data);
		return new ResponseEntity<>(insertRes, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<BaseUpdateResDto> update(@Valid @RequestBody  final QuestionUpdateReqDto data) {
		final BaseUpdateResDto updateRes = questionService.update(data);
		return new ResponseEntity<>(updateRes, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<BaseDataGetResDto<QuestionDataDto>> getById(@PathVariable("id") final String id) {
		final BaseDataGetResDto<QuestionDataDto> question = questionService.getById(id);
		return new ResponseEntity<>(question, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<BaseDataGetResDto<List<QuestionDataDto>>> getAll() {
		final BaseDataGetResDto<List<QuestionDataDto>> questions = questionService.getAll();
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}
	
	@GetMapping("random")
	public ResponseEntity<BaseDataGetResDto<List<QuestionDataDto>>> getRandomQuestion() {
		final BaseDataGetResDto<List<QuestionDataDto>> questions = questionService.getRandomQuestion();
		return new ResponseEntity<>(questions, HttpStatus.OK);
	}
}
