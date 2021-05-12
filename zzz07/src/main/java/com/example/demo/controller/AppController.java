package com.example.demo.controller;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Users;
import com.example.demo.repository.UsersRepository;

@Controller
public class AppController {
	
	final
	UsersRepository usersRepository;
	final
	JdbcTemplate jdbcTemplate;
	
	public AppController(JdbcTemplate jdbcTemplate,UsersRepository usersRepository) {
		this.jdbcTemplate = jdbcTemplate;
		this.usersRepository = usersRepository;
	}
	@RequestMapping("/")
	String index(Model model) {
		List<Users> users = usersRepository.findAll();
		model.addAttribute("users",users);
		return "index";
	}
	
	@PostMapping("/")
	String create(Model model,@ModelAttribute UserForm userForm) {
		Users user = new Users();
		user.setName(userForm.getName());
		user.setAge(userForm.getAge());
		usersRepository.save(user);
		
		List<Users> users = usersRepository.findAll();
		model.addAttribute("users",users);
		
		return "index";
	}
	@PostMapping("/update")
	String update(Model model,@ModelAttribute UserForm userForm) {
		Users user = new Users();
		user.setId(userForm.getId());
		user.setName(userForm.getName());
		user.setAge(userForm.getAge());
		usersRepository.save(user);
		
		List<Users> users = usersRepository.findAll();
		model.addAttribute("users",users);
		return "redirect:/";
	}
	@PostMapping("/delete")
	String delete(Model model,@ModelAttribute UserForm userForm) {
		usersRpository.deleteById(userForm.getId());
		
		List<Users> users = usersRepository.findAll();
		model.addAttribute("users",users);
		return "redirect:/";
	}
}
