package com.example.artauctionhouse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lonny on 11/1/2017.
 */
@Controller
@RequestMapping("home")
public class HomeController {


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "home/index";
    }
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(){
        return"home/login";
    }
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String loggedIn(@RequestParam String username, @RequestParam String password){
        //username=username;
        if (username!="" && password!=""){
            return "redirect:/home/loggedin/success" ;

        }
        else{
            return"home/login";
        }
    }
    @RequestMapping(value="/newuser")
    public String newUserSignup(){
        return "home/newusersignup";
    }
}

