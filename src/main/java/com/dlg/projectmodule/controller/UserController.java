package com.dlg.projectmodule.controller;

import com.dlg.projectmodule.anno.TokenIgnore;
import com.dlg.projectmodule.request.UserInfo;
import com.dlg.projectmodule.request.UserTablePageRequest;
import com.dlg.projectmodule.request.page.TablePageRequest;
import com.dlg.projectmodule.response.Response;
import com.dlg.projectmodule.response.UserVO;
import com.dlg.projectmodule.response.page.PageResponse;
import com.dlg.projectmodule.serviceimpl.UserServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @ApiOperation(value = "获取所有用户")// 方法功能描述
    @GetMapping("list")
    public Response<List<UserVO>> getUser(){
        return new Response<>(userServiceImpl.listUser());
    }

    // 解析URL中的参数为对应的数据结构
    @GetMapping("list_page")
    public Response<List<UserVO>> getUserByPage(TablePageRequest tablePageRequest){
        return new Response<>(userServiceImpl.listUser(tablePageRequest));
    }

    // 解析URL中的参数为对应的数据结构
    // 参数中必须存在查询参数
    @GetMapping("list_page_byid")
    public Response<List<UserVO>> getUserByPage(UserTablePageRequest userTablePageRequest){
        return new Response<>(userServiceImpl.listUser(userTablePageRequest));
    }

    // 自定义分页参数返回
    @GetMapping("list_page_byid2")
    public Response<PageResponse<List<UserVO>>> getUserByPage2(UserTablePageRequest userTablePageRequest){
        return new Response<>(userServiceImpl.listUser2(userTablePageRequest));
    }

    @PostMapping("add")
    public Response addUser(@RequestBody UserInfo userInfo){
        return new Response(userServiceImpl.addUser(userInfo));
    }

    @TokenIgnore
    @GetMapping("/{userId}")
    public Response<UserVO> getUser(@PathVariable("userId") Integer userId) throws Exception {
        return new Response<>(userServiceImpl.user(userId));
    }

    // 修改user 信息
    @PostMapping("/{userId}")
    public Response modifyUser(@PathVariable("userId") Integer userId, @RequestBody UserInfo userInfo){

        return new Response(userServiceImpl.modifyUser(userId, userInfo));
    }
}
