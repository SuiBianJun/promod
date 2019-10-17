package com.dlg.projectmodule.controller;

import com.dlg.projectmodule.request.UserInfo;
import com.dlg.projectmodule.response.Response;
import com.dlg.projectmodule.response.UserVO;
import com.dlg.projectmodule.serviceimpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @GetMapping("list")
    public Response<List<UserVO>> getUser(){
        return new Response<>(userServiceImpl.listUser());
    }

    @PostMapping("add")
    public Response addUser(@RequestBody UserInfo userInfo){
        return new Response(userServiceImpl.addUser(userInfo));
    }

    @GetMapping("/{userId}")
    public Response<UserVO> getUser(@PathVariable("userId") Integer userId) throws Exception {
        return new Response<>(userServiceImpl.user(userId));
    }

}
