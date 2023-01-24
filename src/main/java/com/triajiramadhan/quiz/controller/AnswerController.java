package com.triajiramadhan.quiz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triajiramadhan.quiz.dto.answer.AnswerUpdateReqDto;
import com.triajiramadhan.quiz.dto.base.BaseDeleteResDto;
import com.triajiramadhan.quiz.dto.base.BaseUpdateResDto;
import com.triajiramadhan.quiz.service.AnswerService;

@RestController
@RequestMapping("answers")
public class AnswerController {

	@Autowired
	private AnswerService answerService;
	
	@PutMapping
	public ResponseEntity<BaseUpdateResDto> update(@Valid @RequestBody  final AnswerUpdateReqDto data) {
		final BaseUpdateResDto updateRes = answerService.update(data);
		return new ResponseEntity<>(updateRes, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<BaseDeleteResDto> delete(@PathVariable("id")  final String id) {
		final BaseDeleteResDto deleteRes = answerService.deleteById(id);
		return new ResponseEntity<>(deleteRes, HttpStatus.OK);
	}
}
