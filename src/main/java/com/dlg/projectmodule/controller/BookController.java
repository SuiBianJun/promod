package com.dlg.projectmodule.controller;

import com.dlg.projectmodule.exception.GlobalException;
import com.dlg.projectmodule.request.BookTablePageRequest;
import com.dlg.projectmodule.response.BookVO;
import com.dlg.projectmodule.response.Response;
import com.dlg.projectmodule.serviceimpl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookServiceImpl bookServiceImpl;

    @GetMapping("/list")
    public Response<List<BookVO>> getBooks(){
        return new Response<>(bookServiceImpl.getBooks());
    }

    @GetMapping("/list_page")
    public Response<List<BookVO>> getBooks(BookTablePageRequest bookTablePageRequest){
        return new Response<>(bookServiceImpl.getBooks(bookTablePageRequest));
    }

    @GetMapping("/list_page_byprice")
    public Response<List<BookVO>> getBooksByPrice(BookTablePageRequest bookTablePageRequest){
        System.out.println("booktable pagerequest: " + bookTablePageRequest.getPrice());
        return new Response<>(bookServiceImpl.getBooksByPrice(bookTablePageRequest));
    }

    @GetMapping("/{id}")
    public Response<BookVO> getBooks(@PathVariable("id") String id) throws GlobalException {
        return new Response<>(bookServiceImpl.getBook(id));
    }

}
