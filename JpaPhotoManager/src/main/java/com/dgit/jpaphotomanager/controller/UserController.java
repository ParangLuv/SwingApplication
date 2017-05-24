package com.dgit.jpaphotomanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dgit.jpaphotomanager.entity.Member;
import com.dgit.jpaphotomanager.entity.Photo;
import com.dgit.jpaphotomanager.service.MemberService;
import com.dgit.jpaphotomanager.service.PhotoService;

@Controller
public class UserController {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PhotoService photoService;

	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String registerGet() {
		return "register";
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerPOST(@ModelAttribute Member mem) throws Exception {
		memberService.insertMember(mem);
		return new HomeController().home();
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String loginGet() {
		return "login";
	}

	@RequestMapping(value = "pic", method = RequestMethod.GET)
	public String picGet(HttpSession session, Model model) throws Exception {
		List<Photo> list = photoService.selectPhotoList(session.getAttribute("id").toString());
		System.out.println("id : " + session.getAttribute("id").toString());
		Map<String, String> map = new HashMap<String, String>();
		for (Photo p : list) {
			
		}

		model.addAttribute("list", photoService.selectPhotoList(session.getAttribute("id").toString()));
		return "pic";
	}

	@RequestMapping(value = "idcheck", method = RequestMethod.POST)
	public ResponseEntity<String> idcheck(String id) throws Exception {
		ResponseEntity<String> entity = null;
		String flag = "";
		if (memberService.selectById(id) != null) {
			flag = "duplicated";
		} else {
			flag = "success";
		}
		entity = new ResponseEntity<String>(flag, HttpStatus.OK);
		return entity;
	}

	@RequestMapping(value = "loginCheck", method = RequestMethod.POST)
	public ResponseEntity<String> loginCheck(String id, String password, HttpSession session) throws Exception {
		ResponseEntity<String> entity = null;
		String flag = "";
		Member member = memberService.selectById(id);
		if (member != null) {
			if (member.getPassword().equals(password)) {
				session.setAttribute("id", id);
				flag = "success";
			} else {
				flag = "fail";
			}
		} else {
			flag = "noId";
		}
		entity = new ResponseEntity<String>(flag, HttpStatus.OK);
		return entity;
	}

}
