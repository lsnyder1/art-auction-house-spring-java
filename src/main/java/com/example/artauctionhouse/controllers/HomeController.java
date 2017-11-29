package com.example.artauctionhouse.controllers;

import com.example.artauctionhouse.models.Data.UserDao;
import com.example.artauctionhouse.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * Created by lonny on 11/1/2017.
 */
@Controller
@RequestMapping("home")
public class HomeController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "home/index";

    }
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(){
        return"home/login";

    }
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String logIn(Model model,@RequestParam String username, @RequestParam String password){
        Boolean userLoggedIn=false;
        int id=0;
        User activeUser=new User();

        for(User user:userDao.findAll()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                userLoggedIn = true;
                id = user.getId();
                activeUser=userDao.findOne(id);
            }
        }
        if (userLoggedIn) {
            model.addAttribute("user", activeUser);

            return "redirect:/home/"+activeUser.getUsername();
        }
        else{
            return"home/login";
        }
    }
    @RequestMapping(value="/newuser",method=RequestMethod.GET)
    public String newUserSignup(){
        return "home/newusersignup";
    }

    @RequestMapping(value="/newuser", method = RequestMethod.POST )
    public String newUserSignupProcess(Model model,@RequestParam String username,@RequestParam String password,@RequestParam String confirmPassword,@Valid User newUser){
        Boolean doPasswordsMatch=false;
        Boolean isUsernameTaken=false;



        for (User user : userDao.findAll() ){
            if (user.getUsername().equalsIgnoreCase(username)){
                isUsernameTaken = true;
            }
        }
        if (password.equals(confirmPassword)){
            doPasswordsMatch=true;
        }
        if (doPasswordsMatch && !isUsernameTaken){
            newUser.setUsername(username);
            newUser.setPassword(password);
            userDao.save(newUser);


        }
        else{
            return "home/newusersignup";
        }
        return "loggedin/accountcreationsuccess";

    }
}

