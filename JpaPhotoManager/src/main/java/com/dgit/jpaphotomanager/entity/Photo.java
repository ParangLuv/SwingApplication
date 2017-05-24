package com.dgit.jpaphotomanager.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Photo {

	@Id
	private String id;
	private String pic;
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_NO")
	private Member member;

	public Photo(String id, String pic) {
		super();
		this.id = id;
		this.pic = pic;
	}

	public Photo() {
		super();
	}

	

}
