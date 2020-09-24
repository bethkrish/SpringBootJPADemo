package com.vijayforvictory.demo.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vijayforvictory.demo.jpa.model.UserDto;
import com.vijayforvictory.demo.jpa.repo.User;
import com.vijayforvictory.demo.jpa.repo.UsersRepository;

@Component
public class UserService {

	@Autowired
	UsersRepository repository;

	public void add(UserDto dto) {
		repository.save(toEntity(dto));
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public List<User> getUsers() {
		return (List<User>) repository.findAll();
	}

	public Optional<User> getUserById(Long id) {
		Optional<User> optionalUser = repository.findById(id);
		return optionalUser;
	}

	private User toEntity(UserDto dto) {
		User entity = new User();
		entity.setFirstName(dto.getFirstName());
		entity.setId(dto.getId());
		entity.setSurname(dto.getSurname());
		entity.setLocation(dto.getLocation());
		return entity;
	}
}
