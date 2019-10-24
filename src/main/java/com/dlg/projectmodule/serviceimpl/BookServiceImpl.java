package com.dlg.projectmodule.serviceimpl;

import com.dlg.projectmodule.entity.Book;
import com.dlg.projectmodule.exception.BookException;
import com.dlg.projectmodule.exception.GlobalException;
import com.dlg.projectmodule.repo.redis.BookRepo;
import com.dlg.projectmodule.request.BookTablePageRequest;
import com.dlg.projectmodule.request.page.Page;
import com.dlg.projectmodule.request.page.PageRequest;
import com.dlg.projectmodule.request.specification.BookSpecification;
import com.dlg.projectmodule.response.BookVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl {

    @Autowired
    BookRepo bookRepo;

    public List<BookVO> getBooks(){

        List<BookVO> bookVOS = new ArrayList<>();

        bookVOS = bookRepo.findAll().stream().map(BookVO::new).sorted(Comparator.comparing(BookVO::getPrice)).collect(Collectors.toList());
        /*bookRepo.findAll().forEach(book -> {
            bookVOS.add(new BookVO(book));
        });*/

        return bookVOS;
    }

    public BookVO getBook(String id) throws GlobalException {
        Optional<Book> bookOptional = bookRepo.findById(id);

        if(!bookOptional.isPresent()){
            throw new BookException("书本ID不存在");
        }
        BookVO bookVO = new BookVO(bookOptional.get());
        return bookVO;
    }

    public List<BookVO> getBooks(BookTablePageRequest bookTablePageRequest){

        List<BookVO> bookVOS = new ArrayList<>();

        PageRequest pageRequest = new PageRequest(bookTablePageRequest);

        bookVOS = bookRepo.findAll(pageRequest).stream().map(BookVO::new).collect(Collectors.toList());

        return bookVOS;
    }

    public List<BookVO> getBooksByPrice(BookTablePageRequest bookTablePageRequest) {
        List<BookVO> bookVOS = new ArrayList<>();

        Book book = new Book();
        BeanUtils.copyProperties(bookTablePageRequest, book);

        PageRequest pageRequest = new PageRequest(bookTablePageRequest);
        BookSpecification bookSpecification = new BookSpecification(book);

        System.out.println("book price: " + book.getPrice());

        // 条件查询specification使用不了
        // bookVOS = bookRepo.findAll(bookSpecification).stream().map(BookVO::new).collect(Collectors.toList());
        bookVOS = bookRepo.findAll(pageRequest).stream().map(BookVO::new).collect(Collectors.toList());

        return bookVOS;
    }
}
