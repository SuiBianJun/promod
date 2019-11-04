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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    // spring事务管理, 整个方法执行过程中出现异常可触发回滚
    // 事务存在的问题：
    // 第一类更新丢失：（事务中不会出现，数据修改会加锁）事务A撤销，导致事务X的修改也被回滚
    // 脏读：事务A读取了事务X修改了但未提交的数据
    // 不可重复读：事务A第一次读取col_x的的值x后，事务B对col_x修改为y（不管是否提交），然后事务A再次读取col_x时变成了y,两次读取结果不一致
    // 幻读：事务A第一次获取表T的记录有x条，然后事务B对对表T的记录进行了增删，导致事务A再次读取时记录变为了y条（表级）
    // 第二类丢失更新：事务A提交的修改被事务B覆盖（事务A与事务B的执行时间有重合）
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Boolean modifyUser(Integer userId, UserInfo userInfo){

        User user = new User();
        BeanUtils.copyProperties(userInfo, user);
        user.setId(userId);
        //userRepo.updateUserInfo(user);
        userRepo.save(user);

        int a = 1 / 0;

        return true;
    }

}
