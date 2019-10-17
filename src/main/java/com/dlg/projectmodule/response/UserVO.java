package com.dlg.projectmodule.response;

import com.dlg.projectmodule.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UserVO {

    private int id;

    private String name;

    private int age;

    private int address_id;

    public UserVO(User user){
        BeanUtils.copyProperties(user, this);
    }

}
