package com.example.artauctionhouse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lonny on 11/1/2017.
 */
@Controller
public class HomeController {
    @RequestMapping(value = "")
    @ResponseBody
    public String home() {
        return "Helle";
    }
}
