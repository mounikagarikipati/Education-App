package com.enrolment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enrolment.models.SubCategoryModel;

public interface SubCategoryRepository extends JpaRepository<SubCategoryModel, Long> {

	List<SubCategoryModel> findBySubCategoryName(String subCategoryName);

}
