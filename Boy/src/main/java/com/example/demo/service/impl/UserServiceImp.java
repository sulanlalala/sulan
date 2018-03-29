package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@Service
public class UserServiceImp implements UserService{
	@Autowired
	private UserDao userdao;

	@Override
	public Page<User> addTowUser(Integer page,Integer size) {
		// TODO Auto-generated method stub
		//分页参数（页数，个数，升序还是降序，根据哪个属性）
		Pageable pageAble=new PageRequest(page, size,Sort.Direction.DESC,"id");
		return userdao.findAll(pageAble);
	}

	@Override
	public Page<User> findBookCriteria(Integer page, Integer size, User bookQuery) {
		// TODO Auto-generated method stub
		  Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
	      Page<User> bookPage = userdao.findAll(new Specification<User>(){
				@Override
				public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					// TODO Auto-generated method stub
					 List<Predicate> list = new ArrayList<Predicate>();
					    if(null!=bookQuery.getName()&&!"".equals(bookQuery.getName())){
					        list.add((Predicate) cb.equal(root.get("name").as(String.class), bookQuery.getName()));
					    }
					    if(bookQuery.getAge()!=0){
					        list.add((Predicate) cb.equal(root.get("age").as(Integer.class), bookQuery.getAge()));
					    }
					   
					    Predicate[] p = new Predicate[list.size()];
					    return cb.and((javax.persistence.criteria.Predicate[]) list.toArray(p));
				}
	        },pageable);
	        return bookPage;
	}
	
	
	
	

}
