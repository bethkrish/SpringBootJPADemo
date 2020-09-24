package com.vijayforvictory.demo.jpa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vijayforvictory.demo.jpa.TrackTime;
import com.vijayforvictory.demo.jpa.model.UserDto;
import com.vijayforvictory.demo.jpa.repo.User;
import com.vijayforvictory.demo.jpa.service.UserService;

/**
 * @author vijay
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {
	

	@Autowired
	UserService userService;
	
	/**
	 * @return
	 * @throws JsonProcessingException
	 */
	@GetMapping
	@TrackTime
	public String listUsers() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(userService.getUsers());		
	}
	
	/**
	 * @param dto
	 */
	@PostMapping
	@TrackTime
	public void postUsers(@RequestBody UserDto dto) {
		userService.add(dto);
	}
	
	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/{id}")
	@TrackTime
    public Optional<User> getById(@PathVariable(required = true) long id) {
        return userService.getUserById(id);
    }

	/**
	 * @param id
	 */
    @DeleteMapping("/{id}")
	@TrackTime
    public void delete(@PathVariable(required = true) long id) {
    	userService.delete(id);
    }
}
