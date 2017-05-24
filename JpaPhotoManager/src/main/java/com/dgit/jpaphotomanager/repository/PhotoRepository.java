package com.dgit.jpaphotomanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dgit.jpaphotomanager.entity.Photo;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, String>{

	List<Photo> findAllById(String id);
	
	Photo findByPic(String pic);

}
