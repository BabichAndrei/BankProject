package com.bbank.bank.service;

import com.bbank.bank.domain.Cards;
import com.bbank.bank.domain.List;
import com.bbank.bank.domain.User;
import com.bbank.bank.repo.CardRepo;
import com.bbank.bank.repo.ListRepo;
import com.bbank.bank.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    CardRepo cardRepo;
    @Autowired
    ListRepo listRepo;
    public boolean transfer(String card,String expdate, int sum, User user, List list)
    {
        try {

            Cards cardfromDb = cardRepo.findByNum(card);
            User userfromDb = userRepo.findByCard(card);
            if (userfromDb == null || !cardfromDb.getExpdate().equals(expdate)) {
                return false;
            }

            user.setBalance(user.getBalance() - sum);
            userfromDb.setBalance((userfromDb.getBalance() + sum));

            list.setNameoperation("Remittance");
            list.setMoneyopetation(sum);
            java.util.Date dt = new java.util.Date();

            java.text.SimpleDateFormat sdf =
                    new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String currentTime = sdf.format(dt);
            list.setTimeoperation(currentTime);
            list.setCardfrom(user.getCard());
            list.setCardto(cardfromDb.getNum());

            userRepo.save(user);
            userRepo.save(userfromDb);
            listRepo.save(list);

            return true;
        }
        catch (Exception exception)
        {
            return false;
        }

    }

    public java.util.List<List> getlist(User user)
    {
        try {
            User userfromDb = userRepo.findByUsername(user.getUsername());
            java.util.List<List> cardfrom = listRepo.findByCardfrom(user.getCard());
            java.util.List<List> cardto = listRepo.findByCardto(user.getCard());
            cardfrom.addAll(cardto);
            return cardfrom;
        }
        catch (Exception exception)
        {
            return  null;
        }
    }
}
