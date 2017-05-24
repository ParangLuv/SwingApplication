package com.dgit.jpaphotomanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgit.jpaphotomanager.entity.Photo;
import com.dgit.jpaphotomanager.repository.PhotoRepository;

@Service
public class PhotoService {

	@Autowired
	PhotoRepository photoRepository;
	
	public void insertPhoto(Photo photo) {
		photoRepository.save(photo);
	}

	public void deletePhoto(Photo photo) {
		photoRepository.delete(photo);
	}

	public List<Photo> selectPhotoList(String id){
		return photoRepository.findAllById(id);
	}
	
	public Photo selectPhoto(String pic){
		return photoRepository.findByPic(pic);
	}
	
}
