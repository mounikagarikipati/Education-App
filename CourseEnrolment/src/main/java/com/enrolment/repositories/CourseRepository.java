package com.enrolment.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enrolment.models.CourseModel;
import com.enrolment.models.InstructorModel;

public interface CourseRepository extends JpaRepository<CourseModel, Long> {


	List<CourseModel> findByInstructorModel(InstructorModel instructorModel);

	List<CourseModel> findByCourseName(String courseName);




}
