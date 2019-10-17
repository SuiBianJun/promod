package com.dlg.projectmodule.serviceimpl;

import com.dlg.projectmodule.entity.User;
import com.dlg.projectmodule.exception.UserException;
import com.dlg.projectmodule.repo.UserRepo;
import com.dlg.projectmodule.request.UserInfo;
import com.dlg.projectmodule.response.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    @Autowired
    UserRepo userRepo;

    public List<UserVO> listUser(){

        List<UserVO> userVOList = new ArrayList<>();
        userRepo.findAll().forEach(user -> {
            userVOList.add(new UserVO(user));
        });

        return userVOList;
    }

    public Boolean addUser(UserInfo userInfo){

        User user = new User();

        BeanUtils.copyProperties(userInfo, user);

        userRepo.save(user);

        return true;
    }

    public UserVO user(Integer userId) throws Exception{

        // user不存在

        Optional<User> userOptional = userRepo.findById(userId);

        if(!userOptional.isPresent()){
            throw new UserException("用户ID不存在");
        }

        UserVO userVO = new UserVO(userOptional.get());

        return userVO;
    }
}
