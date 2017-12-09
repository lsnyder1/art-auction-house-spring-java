package com.example.artauctionhouse.controllers;

import com.example.artauctionhouse.models.Art;
import com.example.artauctionhouse.models.Data.ArtDao;
import com.example.artauctionhouse.models.Data.UserDao;
import com.example.artauctionhouse.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by lonny on 11/1/2017.
 */
@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private ArtDao artDao;




    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model) {
        Iterable<Art> arts=artDao.findAll();
        model.addAttribute("arts",arts);
        model.addAttribute("title","Art Auction House");
        return "home/index";

    }
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute("title","Log in");
        return"home/login";

    }
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String logIn(HttpServletResponse response,Model model, @RequestParam String username, @RequestParam String password){
        Boolean userLoggedIn=false;
        int id=0;
        User activeUser=new User();

        for(User user:userDao.findAll()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                userLoggedIn = true;
                id = user.getId();
                activeUser=userDao.findOne(id);
                String cookieVal=Integer.toString(id);
                Cookie loggedInCookie=new Cookie("loggedInCookie",cookieVal);
                loggedInCookie.setMaxAge(1800);
                response.addCookie(loggedInCookie);
            }
        }
        if (userLoggedIn) {
            model.addAttribute("user", activeUser);

            return "redirect:/home/"+activeUser.getUsername();
        }
        else{
            model.addAttribute("error","Username and password do not match.");
            model.addAttribute("title","Log in");
            return"home/login";
        }
    }
    @RequestMapping(value="/newuser",method=RequestMethod.GET)
    public String newUserSignup(Model model){
        model.addAttribute("title","Sign Up");
        return "home/newusersignup";
    }

    @RequestMapping(value="/newuser", method = RequestMethod.POST )
    public String newUserSignupProcess(Model model,@RequestParam String username,@RequestParam String password,@RequestParam String confirmPassword,@Valid User newUser){
        model.addAttribute("title","Sign Up");
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

