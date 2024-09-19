package com.spring.implementation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.spring.implementation.model.Users;
import com.spring.implementation.model.UsersDTO;
import com.spring.implementation.repository.UserRepository;


@org.springframework.web.bind.annotation.RestController
public class RestController {
	@Autowired
	UserRepository productRepo;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Jouher-REST endpoint is working";
	}
	
	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody UsersDTO productDTO) {
		Users product = new Users();
		product.setName(productDTO.getName());
		product.setRole(productDTO.getRole());
		Users prod = productRepo.save(product);
		return generateResponse("Hurray - Items saved successfully !!", HttpStatus.OK, prod);
	}

	 public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
	        Map<String, Object> map = new HashMap<String, Object>();
	            map.put("message", message);
	            map.put("status", status.value());
	            map.put("data", responseObj);

	            return new ResponseEntity<Object>(map,status);
	    }

	 @GetMapping("/getItems")
	 public ResponseEntity<Object> getItems(){
		 List<Users> items = productRepo.findAll();
		 return generateResponse("Oh God - Complete Data is Here !!", HttpStatus.OK, items);
	 }


}
