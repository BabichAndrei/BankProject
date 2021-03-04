package com.bbank.bank.repo;

import com.bbank.bank.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepo extends CrudRepository<Role,Long> {
    Role findByUsername(String username);
}
