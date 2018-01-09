package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigDecimal;

@RestController
public class MyController {

	@Autowired
	MyService myService;

	@GetMapping("/test")
	public Object newTest(@RequestParam("a") Integer a, @RequestParam("b") Integer b){
		myService.myTest(a, b);
		return "ok";
	}


	@GetMapping("/user1")
	public Object user1(User user) {
		myService.saveDataSource1(user);
		return "ok";
	}

	@GetMapping("/user2")
	public Object user2(User user) {
		myService.saveDataSource2(user);
		return "ok";
	}

	@GetMapping("/both")
	public Object both(@RequestParam("user1") Integer id1, @RequestParam("user2")Integer id2) {
		User user1 = new User();
		user1.setId(id1);
		user1.setName("wyj");

		User user2 = new User();
		user2.setId(id2);
		user2.setName("wyj");

		myService.saveBoth(user1, user2);
		return "ok";
	}

}
