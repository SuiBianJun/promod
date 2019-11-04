package com.dlg.projectmodule.repo;

import com.dlg.projectmodule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
// 这个是spring的事务管理,需要在启动类中显示指定@EnableTransactionManagement
//import org.springframework.transaction.annotation.Transactional;

// 这个是java自带的事务管理
import javax.transaction.Transactional;

/**
 * JpaSpecificationExecutor 条件查询，分页查询（含排序）
 */

@Repository
public interface UserRepo extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    //	取对象参数的值方法：     :#{#xx.yy}
    // rollbackOn：回滚触发条件
    @Transactional(rollbackOn = Exception.class)
    @Modifying
    // 注意表名有时候可写为类名，有时候需要写为表名
    @Query("update user set age = :#{#newUser.age} where id = :#{#newUser.id}")
    void updateUserInfo(User newUser);

}
