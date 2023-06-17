package com.example.demo.controller;


import com.example.demo.common.R;
import com.example.demo.mapper.UserMapper;
import com.example.demo.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@Api(value = "User", tags = "User-API")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/hello")
    public R hello() {
        return R.ok();
    }


    @ApiOperation(value="getUserByname", notes = "getUserByname")
    @GetMapping("/name/{username}")
    public R getUserByname(@PathVariable("username") String username) {
        User user = userMapper.loadUserByUsername(username);
        System.out.println(user);
        user.setRoles(new ArrayList<>());
        return R.ok().put("user", user);
    }

    @GetMapping("/admin/hello")
    public String admin() {
        return "hello admin!";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "hello user! ";
    }

    @GetMapping(" /db/hello")
    public String dba() {
        return "hello dba!";
    }

}
