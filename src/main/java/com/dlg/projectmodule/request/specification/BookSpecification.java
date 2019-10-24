package com.dlg.projectmodule.request.specification;

import com.dlg.projectmodule.entity.Book;
import com.dlg.projectmodule.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BookSpecification implements Specification<Book> {

    Book book;

    public BookSpecification(Book book){
        this.book = new Book();
        BeanUtils.copyProperties(book, this.book);
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        // 条件查询

        Predicate predicate=criteriaBuilder.gt(root.get("price"), book.getPrice());

        return predicate;
    }
}
