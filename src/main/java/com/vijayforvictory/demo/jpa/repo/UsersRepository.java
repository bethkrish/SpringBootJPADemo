package com.vijayforvictory.demo.jpa.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository  extends CrudRepository<User, Long>{

}
