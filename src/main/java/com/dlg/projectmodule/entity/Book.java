package com.dlg.projectmodule.entity;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@RedisHash("book")
@Data
public class Book {

    @Id
    private String id;

    private String name;

    private Integer price;

    public Book(){}

    public Book(String name, Integer price){
        this.name = name;
        this.price = price;
    }

}
