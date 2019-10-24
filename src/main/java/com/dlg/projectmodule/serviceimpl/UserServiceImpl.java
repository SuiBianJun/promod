package com.dlg.projectmodule.serviceimpl;

import com.dlg.projectmodule.entity.User;
import com.dlg.projectmodule.exception.UserException;
import com.dlg.projectmodule.repo.UserRepo;
import com.dlg.projectmodule.request.UserInfo;
import com.dlg.projectmodule.request.UserTablePageRequest;
import com.dlg.projectmodule.request.page.PageRequest;
import com.dlg.projectmodule.request.page.TablePageRequest;
import com.dlg.projectmodule.request.specification.UserSpecification;
import com.dlg.projectmodule.response.UserVO;
import com.dlg.projectmodule.response.page.PageResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    // 分页、复杂条件查询
    public List<UserVO> listUser(TablePageRequest tablePageRequest){
        // 查询条件参数
        User user = new User();
        BeanUtils.copyProperties(tablePageRequest, user);
        UserSpecification userSpecification = new UserSpecification(user);

        // 分页参数
        PageRequest pageRequest = new PageRequest(tablePageRequest);
        //PageRequest pageRequest = new PageRequest(tablePageRequest, Sort.Direction.DESC, "id");

        List<UserVO> userVOList = new ArrayList<>();
        userRepo.findAll(userSpecification, pageRequest).forEach(tempUser -> {
            userVOList.add(new UserVO(tempUser));
        });

        // 或者再进行排序处理

        return userVOList;
    }

    public PageResponse<List<UserVO>> listUser2(UserTablePageRequest userTablePageRequest) {

        // 查询条件参数
        User user = new User();
        BeanUtils.copyProperties(userTablePageRequest, user);
        UserSpecification userSpecification = new UserSpecification(user);

        // 按条件查询总数
        long count = userRepo.count(userSpecification);
        List<UserVO> userVOList = new ArrayList<>();

        // Pageable 的页数从 0 开始计算！！！
        PageRequest pageRequest = new PageRequest(userTablePageRequest);
        // 按条件和分页查询分页数据
        userRepo.findAll(userSpecification, pageRequest).forEach(tempUser -> {
            userVOList.add(new UserVO(tempUser));
        });

        PageResponse<List<UserVO>> pageResponse = new PageResponse<>(userTablePageRequest.getPage() + 1, userTablePageRequest.getLimits(), (int) count, userVOList);

        return pageResponse;
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
