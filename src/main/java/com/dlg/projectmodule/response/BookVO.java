package com.dlg.projectmodule.response;

import com.dlg.projectmodule.entity.Book;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class BookVO {

    private String id;
    private String name;
    private Integer price;

    public BookVO(Book book){
        BeanUtils.copyProperties(book, this);
    }

}
