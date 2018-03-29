package com.example.demo.controll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
public class HelloController {
	
	@Autowired 
	private UserDao userDao;
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping(value="/hi")
	public String HelloWorld() {
		return "hello";
	}

	@PostMapping(value="/add")
	public User addUser(@RequestParam("name")String name,@RequestParam("age")Integer age) {
		User user=new User();
		user.setAge(age);
		user.setName(name);
		return userDao.save(user);
	}
	
	@GetMapping("/users")
	public List<User> findAllUser(){
		return userDao.findAll();
	}
	
	@PostMapping("/userbyage")
	public List<User> findUsersByAge(@RequestParam("age")Integer age){
		return userDao.findByAge(age);
	}
	
	@PostMapping("/userpage")
	public Page<User> findUserPage(@RequestParam("page")Integer page,@RequestParam("size")Integer size){
		return userService.addTowUser(page-1,size);
	}
	
	@PostMapping("/userpagebycondition")
	public Page<User> findUserPageByCondition(@RequestParam(value="page",required=false,defaultValue="1")Integer page,@RequestParam(value="size",required=false,defaultValue="2")Integer size,@RequestParam(value="name",required=false,defaultValue="")String name,@RequestParam(value="age",required=false,defaultValue="0")Integer age){
		User user=new User();
		user.setAge(age);
		user.setName(name);
		return userService.findBookCriteria(page-1, size,user );
	}
	
	
}
