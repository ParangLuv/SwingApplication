package com.dgit.jpaphotomanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgit.jpaphotomanager.entity.Member;
import com.dgit.jpaphotomanager.repository.MemberRepository;

@Service
public class MemberService {

	
	@Autowired
	MemberRepository memberRepository;
	
	public void insertMember(Member member){
		memberRepository.save(member);	
	}

	public Member selectById(String id) {
		return memberRepository.findOne(id);
	}
	
}
