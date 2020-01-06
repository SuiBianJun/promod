package com.dlg.projectmodule.request.specification;

import com.dlg.projectmodule.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class UserSpecification implements Specification<User> {

    User user;

    public UserSpecification(User user){
        this.user = new User();
        BeanUtils.copyProperties(user, this.user);
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        // 条件查询
        // 多条件组合
        Predicate predicate = criteriaBuilder.conjunction();
        predicate.getExpressions().add(criteriaBuilder.gt(root.get("id"), user.getId()));
        return predicate;
    }
}
