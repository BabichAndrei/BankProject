package com.bbank.bank.repo;

import com.bbank.bank.domain.User;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Long> {
    User findByUsername(String username);
    User findByCard (String card);
}
