package com.triajiramadhan.quiz.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.triajiramadhan.quiz.dto.base.BaseDataGetResDto;
import com.triajiramadhan.quiz.dto.base.BaseInsertResDto;
import com.triajiramadhan.quiz.dto.report.ReportDataDto;
import com.triajiramadhan.quiz.dto.report.ReportInsertReqDto;
import com.triajiramadhan.quiz.service.ReportService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("reports")
public class ReportController {

	@Autowired
	private ReportService reportService;
	
	@PostMapping
	public ResponseEntity<BaseInsertResDto> insert(@Valid @RequestBody  final ReportInsertReqDto data) {
		final BaseInsertResDto insertRes = reportService.insert(data);
		return new ResponseEntity<>(insertRes, HttpStatus.CREATED);
	}
	

	@GetMapping("{id}")
	public ResponseEntity<BaseDataGetResDto<ReportDataDto>> getById(@PathVariable("id") final String id) {
		final BaseDataGetResDto<ReportDataDto> report = reportService.getById(id);
		return new ResponseEntity<>(report, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<BaseDataGetResDto<List<ReportDataDto>>> getAll() {
		final BaseDataGetResDto<List<ReportDataDto>> reports = reportService.getAll();
		return new ResponseEntity<>(reports, HttpStatus.OK);
	}
	
	@GetMapping("user-candidates")
	public ResponseEntity<BaseDataGetResDto<List<ReportDataDto>>> getAllByUserCandidateId(@RequestParam String id) {
		final BaseDataGetResDto<List<ReportDataDto>> reports = reportService.getAllByUserCandidateId(id);
		return new ResponseEntity<>(reports, HttpStatus.OK);
	}
	
	@GetMapping("top-3")
	public ResponseEntity<BaseDataGetResDto<List<ReportDataDto>>> getHighestScore() {
		final BaseDataGetResDto<List<ReportDataDto>> reports = reportService.getHighestScore();
		return new ResponseEntity<>(reports, HttpStatus.OK);
	}
}
