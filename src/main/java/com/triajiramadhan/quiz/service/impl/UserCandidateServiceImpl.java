package com.triajiramadhan.quiz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.triajiramadhan.quiz.dao.UserCandidateDao;
import com.triajiramadhan.quiz.dto.base.BaseDataGetResDto;
import com.triajiramadhan.quiz.dto.base.BaseDataInsertResDto;
import com.triajiramadhan.quiz.dto.base.BaseDataUpdateResDto;
import com.triajiramadhan.quiz.dto.base.BaseDeleteResDto;
import com.triajiramadhan.quiz.dto.base.BaseInsertResDto;
import com.triajiramadhan.quiz.dto.base.BaseUpdateResDto;
import com.triajiramadhan.quiz.dto.usercandidate.UserCandidateDataDto;
import com.triajiramadhan.quiz.dto.usercandidate.UserCandidateInsertReqDto;
import com.triajiramadhan.quiz.dto.usercandidate.UserCandidateUpdateReqDto;
import com.triajiramadhan.quiz.model.UserCandidate;
import com.triajiramadhan.quiz.service.UserCandidateService;

@Service
public class UserCandidateServiceImpl implements UserCandidateService {
	
	@Autowired
	private UserCandidateDao userCandidateDao;
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public BaseInsertResDto insert(UserCandidateInsertReqDto data) {		
		final BaseDataInsertResDto baseDataInsertResDto = new BaseDataInsertResDto();
		final BaseInsertResDto baseInsertResDto = new BaseInsertResDto();

		UserCandidate userCandidateInsert = new UserCandidate();
		userCandidateInsert.setFullName(data.getFullName());
		userCandidateInsert.setEmail(data.getEmail());
		userCandidateInsert.setUserName(data.getUserName());
		final String hashPwd = passwordEncoder.encode(data.getPassword());
		userCandidateInsert.setPassword(hashPwd);
		userCandidateInsert = userCandidateDao.insert(userCandidateInsert);
		
		baseDataInsertResDto.setId(userCandidateInsert.getId());		
		baseInsertResDto.setData(baseDataInsertResDto);
		baseInsertResDto.setMessage("User Candidate Added Successfully!");
		
		return baseInsertResDto;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public BaseUpdateResDto update(UserCandidateUpdateReqDto data) {
		final Optional<UserCandidate>optional = userCandidateDao.getById(data.getId());
		final BaseDataUpdateResDto baseDataUpdateResDto = new BaseDataUpdateResDto();
		final BaseUpdateResDto baseUpdateResDto = new BaseUpdateResDto();
		if(optional.isPresent()) {
			UserCandidate userCandidateUpdate = optional.get();
			if(data.getFullName() != null || !data.getFullName().equalsIgnoreCase("")) {
				if (userCandidateUpdate.getVersion() == data.getVersion()) {
					userCandidateUpdate.setFullName(data.getFullName());
					userCandidateUpdate.setIsActive(data.getIsActive());
					userCandidateUpdate = userCandidateDao.update(userCandidateUpdate);
					baseDataUpdateResDto.setVersion(userCandidateUpdate.getVersion());
					baseUpdateResDto.setData(baseDataUpdateResDto);
					baseUpdateResDto.setMessage("Updating User Candidate Success!");					
				} else {
					baseDataUpdateResDto.setVersion(userCandidateUpdate.getVersion());
					baseUpdateResDto.setData(baseDataUpdateResDto);
					baseUpdateResDto.setMessage("Version is Not Uptodate!");
				}
			} 
		}else {
			baseUpdateResDto.setData(baseDataUpdateResDto);
			baseUpdateResDto.setMessage("User Candidate Not Found!");
		}
		return baseUpdateResDto;
	}

	@Override
	public BaseDataGetResDto<UserCandidateDataDto> getById(String id) {
		final Optional<UserCandidate> optional = userCandidateDao.getById(id);
		final BaseDataGetResDto<UserCandidateDataDto> baseDataGetResDto = new BaseDataGetResDto<>();
		final UserCandidateDataDto userCandidateDataDto = new UserCandidateDataDto();
		if(optional.isPresent()) {
			userCandidateDataDto.setFullName(optional.get().getFullName());
			userCandidateDataDto.setEmail(optional.get().getEmail());
			userCandidateDataDto.setUserName(optional.get().getUserName());
			userCandidateDataDto.setId(optional.get().getId());
			userCandidateDataDto.setVersion(optional.get().getVersion());
			userCandidateDataDto.setIsActive(optional.get().getIsActive());
		}
		baseDataGetResDto.setData(userCandidateDataDto);
		return baseDataGetResDto;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public BaseDeleteResDto deleteById(String id) {
		final Optional<UserCandidate> optional = userCandidateDao.getById(id);
		final BaseDeleteResDto baseDeleteResDto = new BaseDeleteResDto();
		if (optional.isPresent()) {
			userCandidateDao.deleteById(id);
			baseDeleteResDto.setMessage("User Candidate Deleted Successfully!");
		} else {
			baseDeleteResDto.setMessage("User Candidate Deleted Fail!");
		}
		return baseDeleteResDto;
	}

	@Override
	public BaseDataGetResDto<List<UserCandidateDataDto>> getAll() {
		final List<UserCandidate> userCandidates = userCandidateDao.getAll();
		final List<UserCandidateDataDto> userCandidateDataDtos = new ArrayList<>();
		final BaseDataGetResDto<List<UserCandidateDataDto>> baseDataGetResDto = new BaseDataGetResDto<>();
		if (userCandidates.size() > 0) {
			for (UserCandidate userCandidate : userCandidates) {
				UserCandidateDataDto userCandidateDataDto = new UserCandidateDataDto();
				userCandidateDataDto.setFullName(userCandidate.getFullName());
				userCandidateDataDto.setEmail(userCandidate.getEmail());
				userCandidateDataDto.setUserName(userCandidate.getUserName());
				userCandidateDataDto.setId(userCandidate.getId());
				userCandidateDataDto.setVersion(userCandidate.getVersion());
				userCandidateDataDto.setIsActive(userCandidate.getIsActive());
				userCandidateDataDtos.add(userCandidateDataDto);
			}
		}
		baseDataGetResDto.setData(userCandidateDataDtos);
		return baseDataGetResDto;
	}

	@Override
	public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
		final Optional<UserCandidate> userOptional = userCandidateDao.getByUserName(userName);
		if (userOptional.isPresent()) {
			return new org.springframework.security.core.userdetails
					.User(userName, userOptional.get().getPassword(), 
					new ArrayList<>());
		}
		throw new  UsernameNotFoundException("Wrong Username or Password");
	}

	@Override
	public Optional<UserCandidate> getByUserName(final String userName) {
		return userCandidateDao.getByUserName(userName);
	}

}
