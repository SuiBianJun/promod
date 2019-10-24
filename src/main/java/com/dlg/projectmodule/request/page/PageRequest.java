package com.dlg.projectmodule.request.page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class PageRequest extends Page implements Pageable{

    private Sort sort;

    public PageRequest(Page page){
        this.page = page.getPage();
        this.limits = page.getLimits();
        this.sort = Sort.unsorted();
    }
    public PageRequest(Page page, Sort sort){
        this.page = page.getPage();
        this.limits = page.getLimits();
        this.sort = sort;
    }

    // 带排序功能
    public PageRequest(Page page, Direction direction, String... properties) {
        this(page, Sort.by(direction, properties));
    }

    @Override
    public int getPageNumber() {
        return this.page;
    }

    @Override
    public int getPageSize() {
        return this.limits;
    }

    @Override
    public long getOffset() {
        return this.page * this.limits;
    }

    @Override
    public Sort getSort() {
        return this.sort;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return page > 0;
    }
}
