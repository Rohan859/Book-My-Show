package com.project.bookmyshow.bookmyshow.Controller;

import com.project.bookmyshow.bookmyshow.Entities.User;
import com.project.bookmyshow.bookmyshow.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;


    @PostMapping("/addUser")
    public String addUser(@RequestBody User user)
    {
        String result=userService.addUser(user);
        return result;
    }
}
