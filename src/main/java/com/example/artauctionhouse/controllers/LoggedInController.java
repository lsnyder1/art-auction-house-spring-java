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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;


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
    public String userHomePage(@CookieValue(value="loggedInCookie")String cookieValue, Model model,@PathVariable String username) {


        int id = Integer.parseInt(cookieValue);
        User activeUser = userDao.findOne(id);
        User pageOwner = userDao.findByUsername(username);
        model.addAttribute("activeUser", activeUser);
        model.addAttribute("pageOwner",pageOwner);


        //renders users homepage with add/edit options
        if (pageOwner.getUsername().equalsIgnoreCase(activeUser.getUsername())) {

            model.addAttribute("title",username+"'s page");
            return "loggedin/userhome";
        }

        //returns a users page that does not belong to activeUser without add/edit options
        else {
            model.addAttribute("title",username+"'s page");
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
    @RequestMapping(value="/{username}/addart",method =RequestMethod.POST)
    public String processAddArtForm(@CookieValue(value="loggedInCookie")String cookieValue, Model model, @PathVariable String username, @ModelAttribute Art newArt,
                                    @RequestParam MultipartFile file){
        int id = Integer.parseInt(cookieValue);
        User activeUser = userDao.findOne(id);
        model.addAttribute("activeUser", activeUser);
        File chkDir=new File("C://projimages/"+username);
        if (!chkDir.exists()){
            chkDir.mkdir();
        }
        File hdFile=new File("C://projimages/"+username+"/"+file.getOriginalFilename());

        if (!file.isEmpty()){
            try{
                file.transferTo(hdFile);

            }
            catch(IOException e){
                e.printStackTrace();

            }
        }
        newArt.setImageLocation("/projimages/"+username+"/"+hdFile.getName());
        newArt.setOwner(activeUser);
        artDao.save(newArt);
        return "redirect:/home/"+username+"/"+newArt.getTitle();

    }

    @RequestMapping(value="/{username}/{artTitle}",method=RequestMethod.GET)
    public String displayArtPage(@CookieValue(value="loggedInCookie")String cookieValue,Model model,@PathVariable String username,@PathVariable String artTitle){
        int id=Integer.parseInt(cookieValue);
        User activeUser=userDao.findOne(id);
        model.addAttribute("activeUser",activeUser);
        User artOwner = userDao.findByUsername(username);
        Art art = artDao.findByOwnerAndTitle(artOwner,artTitle);
        model.addAttribute("art",art);
        model.addAttribute("title",art.getTitle());

        return "loggedin/displayart";
    }

}

