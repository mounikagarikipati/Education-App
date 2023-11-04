package com.enrolment.controllers;

import java.io.File;
import java.io.FileOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.enrolment.models.InstructorModel;
import com.enrolment.services.InstructorService;


@RestController
public class InstructorController {
    @Autowired private InstructorService instructorService;
    @Value("${instructorImagePath}")
	String instructorImagePath;
    @RequestMapping(value = "InstructorReg", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
     public String instructorReg(
    		@RequestParam(name="profielPicture") MultipartFile multipartFile,
    		@RequestParam String name,
    		@RequestParam String email,
    		@RequestParam String phone,
    		@RequestParam String password,
    		@RequestParam String address,
    		@RequestParam String about
    		 
    		 ) {
    	try {
			File uploadedFile = new File(instructorImagePath, multipartFile.getOriginalFilename());
			uploadedFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(uploadedFile);
			fos.write(multipartFile.getBytes());
			fos.close();
			
			InstructorModel instructorModel = new InstructorModel();
			instructorModel.setName(name);
			instructorModel.setEmail(email);
			instructorModel.setPhone(phone);
			instructorModel.setPassword(password);
			instructorModel.setAbout(about);
			instructorModel.setAddress(address);
			instructorModel.setProfielPicture(multipartFile.getOriginalFilename());
			
			return instructorService.instructorReg(instructorModel);
		} catch (Exception e) {
			System.out.println(e);
			return "Fail to upload";
		}
    	
    	
    }
}
