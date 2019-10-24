package com.dlg.projectmodule.repo;

import com.dlg.projectmodule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * JpaSpecificationExecutor 条件查询，分页查询（含排序）
 */

@Repository
public interface UserRepo extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
}
