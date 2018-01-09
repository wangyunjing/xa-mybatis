package com.example.demo.service;

import com.example.demo.domain.User;

public interface MyService {

	void myTest(Integer a1, Integer a2);

	void saveDataSource1(User user);

	void saveDataSource2(User user);

	void saveBoth(User user1, User user2);
}
