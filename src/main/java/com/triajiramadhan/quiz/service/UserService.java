package com.triajiramadhan.quiz.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.triajiramadhan.quiz.dto.base.BaseDeleteResDto;
import com.triajiramadhan.quiz.dto.base.BaseDataGetResDto;
import com.triajiramadhan.quiz.dto.base.BaseInsertResDto;
import com.triajiramadhan.quiz.dto.base.BaseUpdateResDto;
import com.triajiramadhan.quiz.dto.user.UserDataDto;
import com.triajiramadhan.quiz.dto.user.UserInsertReqDto;
import com.triajiramadhan.quiz.dto.user.UserUpdateReqDto;
import com.triajiramadhan.quiz.model.User;

public interface UserService extends UserDetailsService {

	BaseInsertResDto insert(UserInsertReqDto data);

	BaseUpdateResDto update(UserUpdateReqDto data);

	BaseDataGetResDto<UserDataDto> getById(String id);

	BaseDeleteResDto deleteById(String id);

	BaseDataGetResDto<List<UserDataDto>> getAll();
	
	Optional<User> getByUserName(String userName);
	
}
