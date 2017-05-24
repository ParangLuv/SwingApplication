package com.dgit.jpaphotomanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dgit.jpaphotomanager.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>{
	Member findByName(String name);
}
