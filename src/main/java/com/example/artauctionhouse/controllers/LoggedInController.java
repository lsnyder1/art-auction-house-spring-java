package com.example.artauctionhouse.controllers;

import com.example.artauctionhouse.models.Art;
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
    public String loginSuccess(@CookieValue(value="loggedInCookie")String cookieValue, Model model,@PathVariable String username) {


        int id = Integer.parseInt(cookieValue);
        User activeUser = userDao.findOne(id);
        User pageOwner = new User();
        model.addAttribute("activeUser", activeUser);


        //renders users homepage with add/edit options
        if (username.equalsIgnoreCase(activeUser.getUsername())) {
            pageOwner = activeUser;
            
            return "loggedin/userhome";
        }

        //returns a users page that does not belong to activeUser without add/edit options
        else {
            for (User user : userDao.findAll()) {
                if (user.getUsername().equalsIgnoreCase(username)) {
                    pageOwner = user;
                    break;
                }

            }
            return "loggedin/someuserpage";

        }
    }
    @RequestMapping(value="/{username}/addart", method = RequestMethod.GET)
    public String addArtForm(@CookieValue(value="loggedInCookie")String cookieValue, Model model,@PathVariable String username,Art newArt) {
        int id = Integer.parseInt(cookieValue);
        User activeUser = userDao.findOne(id);
        model.addAttribute("activeUser", activeUser);



        //renders form to add an art piece to catalog
        if (username.equalsIgnoreCase(activeUser.getUsername())) {
            model.addAttribute("newArt",newArt);
            model.addAttribute("title","Add an art piece");
            return "loggedin/addart";
        }

        //returns a user to home that in not the owner of the page
        else {
            return "home/index";

            }


        }
    @RequestMapping(value="/{userName}/addart",method =RequestMethod.POST)
    public String processAddArtForm(@CookieValue(value="loggedInCookie")String cookieValue, Model model,@PathVariable String userName,@ModelAttribute Art newArt){
        int id = Integer.parseInt(cookieValue);
        User activeUser = userDao.findOne(id);
        model.addAttribute("activeUser", activeUser);
        newArt.setOwner(activeUser);
        artDao.save(newArt);
        return "redirect:/home/"+userName;

    }

    @RequestMapping(value="/{username}/{artTitle}",method=RequestMethod.GET)
    public String displayArtPage(@CookieValue(value="loggedInCookie")String cookieValue,Model model,@PathVariable String username,@PathVariable String artTitle){
        int id=Integer.parseInt(cookieValue);
        User activeUser=userDao.findOne(id);
        model.addAttribute("activeUser",activeUser);
        User artOwner = userDao.findByUsername(username);
        Art art = artDao.findByOwnerAndTitle(artOwner,artTitle);







        return "loggedin/displayart";
    }

}

