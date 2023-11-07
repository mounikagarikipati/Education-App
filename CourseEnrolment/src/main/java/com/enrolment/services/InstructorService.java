package com.enrolment.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.enrolment.models.InstructorModel;
import com.enrolment.models.LoginDetailsModel;
import com.enrolment.repositories.InstructorRepository;
import com.enrolment.repositories.LoginDetailsRepository;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class InstructorService {
	@Autowired
	private InstructorRepository instructorRepository;
	@Autowired
	private LoginDetailsRepository loginDetailsRepository;

	public String instructorReg(InstructorModel instructorModel) {
		List<InstructorModel>  instructorModelsList = instructorRepository.findByEmailOrPhone(instructorModel.getEmail(),instructorModel.getPhone());
		if(instructorModelsList.size() >0) {
			return "Duplicate Details";
		}
		
		List<LoginDetailsModel> loginDetailsModelsList = loginDetailsRepository.findAll();
		if(loginDetailsModelsList.size()==0) {
			LoginDetailsModel loginDetailsModelsList2 = new LoginDetailsModel();
			loginDetailsModelsList2.setEmail("admin@gmail.com");
			loginDetailsModelsList2.setPassword(new BCryptPasswordEncoder().encode("admin"));
			loginDetailsModelsList2.setRole("ROLE_ADMIN");
			loginDetailsRepository.save(loginDetailsModelsList2);
		}
		
		LoginDetailsModel loginDetailsModel = new LoginDetailsModel();
		loginDetailsModel.setEmail(instructorModel.getEmail());
		loginDetailsModel.setPassword(new BCryptPasswordEncoder().encode(instructorModel.getPassword()));
		loginDetailsModel.setRole("ROLE_INSTRUCTOR");
		loginDetailsRepository.save(loginDetailsModel);
		LoginDetailsModel loginDetailsModel2 = loginDetailsRepository.findByEmail(instructorModel.getEmail());
		instructorModel.setLoginDetailsModel(loginDetailsModel2);
	    
		instructorRepository.save(instructorModel);
		return "Instructor Registered Successfully";
	}

}
