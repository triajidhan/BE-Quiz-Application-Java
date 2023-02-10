package com.triajiramadhan.quiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.triajiramadhan.quiz.dto.base.BaseDeleteResDto;
import com.triajiramadhan.quiz.dto.base.BaseDataGetResDto;
import com.triajiramadhan.quiz.dto.base.BaseInsertResDto;
import com.triajiramadhan.quiz.dto.base.BaseUpdateResDto;
import com.triajiramadhan.quiz.dto.usercandidate.UserCandidateDataDto;
import com.triajiramadhan.quiz.dto.usercandidate.UserCandidateInsertReqDto;
import com.triajiramadhan.quiz.dto.usercandidate.UserCandidateUpdateReqDto;
import com.triajiramadhan.quiz.model.UserCandidate;

public interface UserCandidateService extends UserDetailsService {

	BaseInsertResDto insert(UserCandidateInsertReqDto data);

	BaseUpdateResDto update(UserCandidateUpdateReqDto data);

	BaseDataGetResDto<UserCandidateDataDto> getById(String id);

	BaseDeleteResDto deleteById(String id);

	BaseDataGetResDto<List<UserCandidateDataDto>> getAll();
	
	Optional<UserCandidate> getByUserName(String userName);
	
}
