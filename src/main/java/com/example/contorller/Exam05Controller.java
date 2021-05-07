package com.example.contorller;

import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Member;
import com.example.repository.MemberRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/exam5")
public class Exam05Controller {
	@Autowired
	private MemberRepository repository;
	
	@RequestMapping("")
	public String index() {
		return "exam05";
	}
	
	@RequestMapping("/result")
	public String result(String name, Model model) {
		List<Member> list = repository.findByName(name);
		model.addAttribute("list",list);
		
		return "exam05-result";
	}

}
