package com.bbank.bank.repo;

import com.bbank.bank.domain.List;
import org.springframework.data.repository.CrudRepository;

public interface ListRepo extends CrudRepository<List,Long> {
    java.util.List<List> findByCardfrom (String cardfrom);
    java.util.List<List> findByCardto (String cardto);
}
