package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.entity.User;

public interface UserService {
	
	public Page<User> addTowUser(Integer page,Integer size);
	
	public Page<User> findBookCriteria(Integer page, Integer size, final User bookQuery);
	
	

} 


