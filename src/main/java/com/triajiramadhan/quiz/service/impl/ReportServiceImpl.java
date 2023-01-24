package com.triajiramadhan.quiz.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triajiramadhan.quiz.dao.AnswerDao;
import com.triajiramadhan.quiz.dao.ReportDao;
import com.triajiramadhan.quiz.dao.SubmissionDao;
import com.triajiramadhan.quiz.dao.UserCandidateDao;
import com.triajiramadhan.quiz.dto.answer.AnswerDataDto;
import com.triajiramadhan.quiz.dto.base.BaseDataGetResDto;
import com.triajiramadhan.quiz.dto.base.BaseDataInsertResDto;
import com.triajiramadhan.quiz.dto.base.BaseInsertResDto;
import com.triajiramadhan.quiz.dto.report.ReportDataDto;
import com.triajiramadhan.quiz.dto.report.ReportInsertReqDto;
import com.triajiramadhan.quiz.dto.submission.SubmissionDataDto;
import com.triajiramadhan.quiz.model.Answer;
import com.triajiramadhan.quiz.model.Report;
import com.triajiramadhan.quiz.model.Submission;
import com.triajiramadhan.quiz.model.UserCandidate;
import com.triajiramadhan.quiz.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ReportDao reportDao;
	@Autowired
	private UserCandidateDao userCandidateDao;
	@Autowired
	private AnswerDao answerDao;
	@Autowired
	private SubmissionDao submissionDao;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public BaseInsertResDto insert(ReportInsertReqDto data) {
		final BaseDataInsertResDto baseDataInsertResDto = new BaseDataInsertResDto();
		final BaseInsertResDto baseInsertResDto = new BaseInsertResDto();
		final Optional<UserCandidate> optionalUserCandidate = userCandidateDao.getById(data.getUserCandidateId());
		
		Report reportInsert = new Report();
		reportInsert.setUserCandidate(optionalUserCandidate.get());
		reportInsert = reportDao.insert(reportInsert);
		
		double totalQuestions = 5;
		double totalCorrectAnswer = 0;
		for (int i = 0; i < data.getSubmissions().size(); i++) {
			final Optional<Answer> optionalAnswer = answerDao.getById(data.getSubmissions().get(i).getAnswerId());
			if (optionalAnswer.get().getAnswerKey()) {
				totalCorrectAnswer += 1;
			}	
			Submission submissionInsert = new Submission();
			submissionInsert.setReport(reportInsert);
			submissionInsert.setAnswer(optionalAnswer.get());
			submissionInsert = submissionDao.insert(submissionInsert);
		}
		final Double score = (totalCorrectAnswer / totalQuestions) * 100;
		
		reportInsert.setScore(score);
		reportInsert = reportDao.update(reportInsert);

		baseDataInsertResDto.setId(reportInsert.getId());
		baseInsertResDto.setData(baseDataInsertResDto);
		baseInsertResDto.setMessage("Submission Added Successfully!");
		return baseInsertResDto;
	}
	
	@Override
	public BaseDataGetResDto<ReportDataDto> getById(String id) {
		final Optional<Report> optional = reportDao.getById(id);
		final BaseDataGetResDto<ReportDataDto> baseDataGetResDto = new BaseDataGetResDto<>();
		final ReportDataDto reportDataDto = new ReportDataDto();
		if(optional.isPresent()) {
			reportDataDto.setFullName(optional.get().getUserCandidate().getFullName());
			reportDataDto.setScore(optional.get().getScore());
			reportDataDto.setId(optional.get().getId());
			reportDataDto.setVersion(optional.get().getVersion());
			
			final List<SubmissionDataDto> submissionDataDtos = new ArrayList<>();
			List<Submission> submissions = new ArrayList<>();
			submissions = submissionDao.getAllByReportId(id);		
			for (int i = 0; i < submissions.size(); i++) {
				final SubmissionDataDto submissionDataDto = new SubmissionDataDto();
				submissionDataDto.setQuestion(submissions.get(i).getAnswer().getQuestion().getQuestion());
				submissionDataDto.setUserAnswer(submissions.get(i).getAnswer().getAnswer());
				submissionDataDto.setResult(submissions.get(i).getAnswer().getAnswerKey());
				submissionDataDto.setId(submissions.get(i).getId());
				submissionDataDto.setIsActive(submissions.get(i).getIsActive());
				submissionDataDto.setVersion(submissions.get(i).getVersion());
				
				final List<AnswerDataDto> answerDataDtos = new ArrayList<>();
				List<Answer> answers = new ArrayList<>();
				answers = answerDao.getByQuestionId(submissions.get(i).getAnswer().getQuestion().getId());		
				for (int j = 0; j < answers.size(); j++) {
					final AnswerDataDto answerDataDto = new AnswerDataDto();
					answerDataDto.setAnswer(answers.get(i).getAnswer());
					answerDataDto.setAnswerKey(answers.get(i).getAnswerKey());
					answerDataDto.setId(answers.get(i).getId());
					answerDataDto.setIsActive(answers.get(i).getIsActive());
					answerDataDto.setVersion(answers.get(i).getVersion());
					answerDataDtos.add(answerDataDto);
				}
				submissionDataDto.setAnswers(answerDataDtos);
				submissionDataDtos.add(submissionDataDto);
			}
			reportDataDto.setSubmissions(submissionDataDtos);
		}
		baseDataGetResDto.setData(reportDataDto);
		return baseDataGetResDto;
	}

	@Override
	public BaseDataGetResDto<List<ReportDataDto>> getAll() {
		final List<Report> reports = reportDao.getAll();
		final List<ReportDataDto> reportDataDtos = new ArrayList<>();
		final BaseDataGetResDto<List<ReportDataDto>> baseDataGetResDto = new BaseDataGetResDto<>();
		if (reports.size() > 0) {
			for (Report report : reports) {
				ReportDataDto reportDataDto = new ReportDataDto();
				reportDataDto.setFullName(report.getUserCandidate().getFullName());
				reportDataDto.setScore(report.getScore());
				reportDataDto.setId(report.getId());
				reportDataDto.setVersion(report.getVersion());
				reportDataDtos.add(reportDataDto);
			}
		}
		baseDataGetResDto.setData(reportDataDtos);
		return baseDataGetResDto;
	}

	@Override
	public BaseDataGetResDto<List<ReportDataDto>> getAllByUserCandidateId(String userCandidateId) {
		final List<Report> reports = reportDao.getAllByUserCandidateId(userCandidateId);
		final List<ReportDataDto> reportDataDtos = new ArrayList<>();
		final BaseDataGetResDto<List<ReportDataDto>> baseDataGetResDto = new BaseDataGetResDto<>();
		if (reports.size() > 0) {
			for (Report report : reports) {
				ReportDataDto reportDataDto = new ReportDataDto();
				reportDataDto.setFullName(report.getUserCandidate().getFullName());
				reportDataDto.setScore(report.getScore());
				reportDataDto.setId(report.getId());
				reportDataDto.setVersion(report.getVersion());
				reportDataDtos.add(reportDataDto);
			}
		}
		baseDataGetResDto.setData(reportDataDtos);
		return baseDataGetResDto;
	}

	@Override
	public BaseDataGetResDto<List<ReportDataDto>> getHighestScore() {
		final List<Report> reports = reportDao.getAll();
		final List<ReportDataDto> reportDataDtos = new ArrayList<>();
		final BaseDataGetResDto<List<ReportDataDto>> baseDataGetResDto = new BaseDataGetResDto<>();
		if (reports.size() > 0) {
			for (Report report : reports) {
				ReportDataDto reportDataDto = new ReportDataDto();
				reportDataDto.setFullName(report.getUserCandidate().getFullName());
				reportDataDto.setScore(report.getScore());
				reportDataDto.setId(report.getId());
				reportDataDto.setVersion(report.getVersion());
				reportDataDtos.add(reportDataDto);
			}
		}
		baseDataGetResDto.setData(reportDataDtos);
		
		baseDataGetResDto.setData(baseDataGetResDto.getData()
				.stream()
                .sorted(Comparator.comparingDouble(ReportDataDto::getScore).reversed())
                .limit(3)
                .collect(Collectors.toList()));
		
		return baseDataGetResDto;
	}

}
