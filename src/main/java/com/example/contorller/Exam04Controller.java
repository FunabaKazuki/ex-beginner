package com.example.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.UserForm;

@Controller
@RequestMapping ("/exam4")
public class Exam04Controller {
	@ModelAttribute
	public UserForm setUpFor() {
		return new UserForm();
	}
	
	@RequestMapping("")
	public String index() {
		return "exam04";
	}
	
	@RequestMapping("/result")
	public String result(@Validated UserForm userForm ,BindingResult result ,Model model) {
		
		if(result.hasErrors()) {
			return index();
		}
		User user = new User();
		user.setName(userForm.getName());
		user.setAge(userForm.getIntAge());
		user.setComment(userForm.getComment());
		model.addAttribute("user",user);
		
		return "exam04-result";
	}
}
