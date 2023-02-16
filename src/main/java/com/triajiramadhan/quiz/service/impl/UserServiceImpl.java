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

import com.triajiramadhan.quiz.constant.UserRole;
import com.triajiramadhan.quiz.dao.UserDao;
import com.triajiramadhan.quiz.dto.base.BaseDataGetResDto;
import com.triajiramadhan.quiz.dto.base.BaseDataInsertResDto;
import com.triajiramadhan.quiz.dto.base.BaseDataUpdateResDto;
import com.triajiramadhan.quiz.dto.base.BaseDeleteResDto;
import com.triajiramadhan.quiz.dto.base.BaseInsertResDto;
import com.triajiramadhan.quiz.dto.base.BaseUpdateResDto;
import com.triajiramadhan.quiz.dto.user.UserDataDto;
import com.triajiramadhan.quiz.dto.user.UserInsertReqDto;
import com.triajiramadhan.quiz.dto.user.UserUpdateReqDto;
import com.triajiramadhan.quiz.model.User;
import com.triajiramadhan.quiz.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	@Autowired
    private PasswordEncoder passwordEncoder;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public BaseInsertResDto insert(UserInsertReqDto data) {		
		final BaseDataInsertResDto baseDataInsertResDto = new BaseDataInsertResDto();
		final BaseInsertResDto baseInsertResDto = new BaseInsertResDto();

		User userInsert = new User();
		userInsert.setFullName(data.getFullName());
		userInsert.setEmail(data.getEmail());
		userInsert.setUserName(data.getUserName());
		userInsert.setRoleCode(data.getRoleCode());
		final String hashPwd = passwordEncoder.encode(data.getPassword());
		userInsert.setPassword(hashPwd);
		userInsert = userDao.insert(userInsert);
		
		baseDataInsertResDto.setId(userInsert.getId());		
		baseInsertResDto.setData(baseDataInsertResDto);
		baseInsertResDto.setMessage("Register Successfully!");
		
		return baseInsertResDto;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public BaseUpdateResDto update(UserUpdateReqDto data) {
		final Optional<User>optional = userDao.getById(data.getId());
		final BaseDataUpdateResDto baseDataUpdateResDto = new BaseDataUpdateResDto();
		final BaseUpdateResDto baseUpdateResDto = new BaseUpdateResDto();
		if(optional.isPresent()) {
			User userUpdate = optional.get();
			if(data.getFullName() != null || !data.getFullName().equalsIgnoreCase("")) {
				if (userUpdate.getVersion() == data.getVersion()) {
					userUpdate.setFullName(data.getFullName());
					userUpdate.setIsActive(data.getIsActive());
					userUpdate = userDao.update(userUpdate);
					baseDataUpdateResDto.setId(userUpdate.getId());
					baseDataUpdateResDto.setVersion(userUpdate.getVersion());
					baseUpdateResDto.setData(baseDataUpdateResDto);
					baseUpdateResDto.setMessage("Updating User Success!");					
				} else {
					baseDataUpdateResDto.setVersion(userUpdate.getVersion());
					baseUpdateResDto.setData(baseDataUpdateResDto);
					baseUpdateResDto.setMessage("Version is Not Uptodate!");
				}
			} 
		}else {
			baseUpdateResDto.setData(baseDataUpdateResDto);
			baseUpdateResDto.setMessage("UserNot Found!");
		}
		return baseUpdateResDto;
	}

	@Override
	public BaseDataGetResDto<UserDataDto> getById(String id) {
		final Optional<User> optional = userDao.getById(id);
		final BaseDataGetResDto<UserDataDto> baseDataGetResDto = new BaseDataGetResDto<>();
		final UserDataDto userDataDto = new UserDataDto();
		if(optional.isPresent()) {
			userDataDto.setFullName(optional.get().getFullName());
			userDataDto.setEmail(optional.get().getEmail());
			userDataDto.setUserName(optional.get().getUserName());
			final String roleCode = optional.get().getRoleCode();
			userDataDto.setRoleCode(roleCode);
			if (roleCode.equalsIgnoreCase(UserRole.CANDIDATE.getRoleCode())) {
				userDataDto.setRoleName(UserRole.CANDIDATE.getRoleName());				
			} else {
				userDataDto.setRoleName(UserRole.ADMIN.getRoleName());
			}
			userDataDto.setId(optional.get().getId());
			userDataDto.setVersion(optional.get().getVersion());
			userDataDto.setIsActive(optional.get().getIsActive());
		}
		baseDataGetResDto.setData(userDataDto);
		return baseDataGetResDto;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public BaseDeleteResDto deleteById(String id) {
		final Optional<User> optional = userDao.getById(id);
		final BaseDeleteResDto baseDeleteResDto = new BaseDeleteResDto();
		if (optional.isPresent()) {
			userDao.deleteById(id);
			baseDeleteResDto.setMessage("User Deleted Successfully!");
		} else {
			baseDeleteResDto.setMessage("User Deleted Fail!");
		}
		return baseDeleteResDto;
	}

	@Override
	public BaseDataGetResDto<List<UserDataDto>> getAll() {
		final List<User> users = userDao.getAll();
		final List<UserDataDto> userDataDtos = new ArrayList<>();
		final BaseDataGetResDto<List<UserDataDto>> baseDataGetResDto = new BaseDataGetResDto<>();
		if (users.size() > 0) {
			for (User user : users) {
				UserDataDto userDataDto = new UserDataDto();
				userDataDto.setFullName(user.getFullName());
				userDataDto.setEmail(user.getEmail());
				userDataDto.setUserName(user.getUserName());
				final String roleCode = user.getRoleCode();
				userDataDto.setRoleCode(roleCode);
				if (roleCode.equalsIgnoreCase(UserRole.CANDIDATE.getRoleCode())) {
					userDataDto.setRoleName(UserRole.CANDIDATE.getRoleName());
				} else {
					userDataDto.setRoleName(UserRole.ADMIN.getRoleName());
				}
				userDataDto.setId(user.getId());
				userDataDto.setVersion(user.getVersion());
				userDataDto.setIsActive(user.getIsActive());
				userDataDtos.add(userDataDto);
			}
		}
		baseDataGetResDto.setData(userDataDtos);
		return baseDataGetResDto;
	}

	@Override
	public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
		final Optional<User> userOptional = userDao.getByUserName(userName);
		if (userOptional.isPresent()) {
			return new org.springframework.security.core.userdetails
					.User(userName, userOptional.get().getPassword(), 
					new ArrayList<>());
		}
		throw new  UsernameNotFoundException("Wrong Username or Password");
	}

	@Override
	public Optional<User> getByUserName(final String userName) {
		return userDao.getByUserName(userName);
	}

}
