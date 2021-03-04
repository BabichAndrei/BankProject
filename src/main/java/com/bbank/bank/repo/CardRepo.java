package com.bbank.bank.repo;

import com.bbank.bank.domain.Cards;
import org.springframework.data.repository.CrudRepository;

public interface CardRepo extends CrudRepository<Cards,Long> {
    Cards findByNum(String num);
}