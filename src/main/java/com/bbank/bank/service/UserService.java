package com.bbank.bank.service;

import com.bbank.bank.domain.*;
import com.bbank.bank.repo.CardRepo;
import com.bbank.bank.repo.RoleRepo;
import com.bbank.bank.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SmtpMailSender mailSender;
    @Autowired
    private CardRepo cardRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =  userRepo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
    public boolean addUser(User user, Role role) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        Cards cardFromDb = cardRepo.findByNum((user.getCard()));
        if (userFromDb != null || cardFromDb == null ) {
            return false;
        };

        role.setUsername(user.getUsername());
        role.setRole(true);
         user.setPassword(Pass.getAlphaNumericString(10));
         user.setCard(cardFromDb.getNum());
         user.setName(cardFromDb.getCardholdername());
         user.setEnable(true);
         user.setBalance(getbalance());
        userRepo.save(user);
        roleRepo.save(role);

        if (!StringUtils.isEmpty(user.getUsername())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to BBANK. It's your password to your bank account: %s . Please, сhange your password in your account settings.",
                    cardFromDb.getCardholdername(),user.getPassword()

            );

            mailSender.send(user.getUsername(), "BBANK Registration", message);
        }

        return true;
    }
    public User getUser ()
    {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        final UsernamePasswordAuthenticationToken userToken =
                (UsernamePasswordAuthenticationToken) authentication;
        String username = userToken.getName();
        return (User) loadUserByUsername(username);
    }

    public boolean changepass(String oldpass,String newpass, String newpass2, User user )
    {
        if(!newpass.equals(newpass2))
        {
            return false;
        }
        User userfromDb = userRepo.findByUsername(user.getUsername());
        if(!oldpass.equals(user.getPassword()))
        {
            return false;
        }
        user.setPassword(newpass);
        userRepo.save(user);
        return true;

    }

    public int getbalance()
    {
        return (int) (Math.random() * 1000);
    }


}
