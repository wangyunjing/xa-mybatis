package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.mapper1.User1Mapper;
import com.example.demo.mapper2.User2Mapper;
import com.example.demo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyServiceImpl implements MyService {

	@Autowired
	@Qualifier("first")
	JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("second")
	JdbcTemplate jdbcTemplate1;

	@Autowired
	User1Mapper user1Mapper;

	@Autowired
	User2Mapper user2Mapper;

	@Transactional
	public void myTest(Integer a1, Integer a2) {
		jdbcTemplate.execute("INSERT INTO person(id, name, age) VALUES (" + a1 + ",'aaa', 18)");
		jdbcTemplate1.execute("INSERT INTO person(id, name, age) VALUES (" + a2 + ",'aaa', 18)");
	}

	@Override
	@Transactional
	public void saveDataSource1(User user) {
		user1Mapper.saveUser(user);
	}

	@Override
	@Transactional
	public void saveDataSource2(User user) {
		user2Mapper.saveUser(user);
	}

	@Override
	@Transactional
	public void saveBoth(User user1, User user2) {
		user1Mapper.saveUser(user1);
		user2Mapper.saveUser(user2);
	}
}
