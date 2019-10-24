package com.dlg.projectmodule;

import com.dlg.projectmodule.entity.Book;
import com.dlg.projectmodule.repo.redis.BookRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {

    /*@Autowired
    RedisTemplate redisTemplate;*/

    @Autowired
    BookRepo bookRepo;

    @Test
    public void contextLoads() {
    }

    @Test
    public void addBook(){
        Integer i = 0;
        Book book;
        for(i = 0; i < 100; i++){
            book = new Book("a", i);
            //redisTemplate.opsForHash().
            bookRepo.save(book);
        }
    }
}
