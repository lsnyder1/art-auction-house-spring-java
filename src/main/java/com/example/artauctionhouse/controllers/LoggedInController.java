package com.example.artauctionhouse.controllers;

import com.example.artauctionhouse.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by lonny on 11/9/2017.
 */
@Controller
@RequestMapping (value = "/home/{username}")
public class LoggedInController {

    @RequestMapping(value="success", method = RequestMethod.GET)
    public String loginSuccess(Model model){
        return "loggedin/loggedin";

    }
}
