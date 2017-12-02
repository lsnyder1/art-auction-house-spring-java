package com.example.artauctionhouse.controllers;

import com.example.artauctionhouse.models.Data.ArtDao;
import com.example.artauctionhouse.models.Data.UserDao;
import com.example.artauctionhouse.models.User;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by lonny on 11/9/2017.
 */
@Controller
@RequestMapping (value = "/home")
public class LoggedInController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private ArtDao artDao;

    @RequestMapping(value="/{username}", method = RequestMethod.GET)
    public String loginSuccess(@CookieValue(value="loggedInCookie")String cookieValue, Model model){

        int id =Integer.parseInt(cookieValue);
        User activeUser=userDao.findOne(id);
        model.addAttribute("activeUser",activeUser);
        return "loggedin/userhome";

    }
}
