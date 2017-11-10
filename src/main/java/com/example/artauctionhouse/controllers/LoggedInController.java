package com.example.artauctionhouse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lonny on 11/9/2017.
 */
@Controller
@RequestMapping (value = "/home/loggedin")
public class LoggedInController {

    @RequestMapping(value="success", method = RequestMethod.GET)
    public String loginSuccess(){
        return "loggedin/loggedin";

    }
}
