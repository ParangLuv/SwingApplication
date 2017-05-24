package com.dgit.jpaphotomanager.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Member {
	
	@Id
	private String id;
	private String name;
	private String email;
	private String password;
	private String tel; 
	
	@OneToMany(mappedBy = "member")
	private List<Photo> photos = new ArrayList<Photo>();
	
}
