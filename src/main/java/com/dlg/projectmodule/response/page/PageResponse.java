package com.dlg.projectmodule.response.page;

import lombok.Data;

// 前端分页非框架
@Data
public class PageResponse<T> {
    private int page;
    private int limits;

    private boolean first;
    private boolean last;
    private boolean haveFirst;
    private boolean haveLast;
    private int count;

    private T data;

    public PageResponse(int page, int limits, int count, T data){
        this.page = page;
        this.limits = limits;
        this.count = count;
        setIs_first();
        setIs_last();
        setHave_first();
        setHave_last();
        this.data = data;
    }

    private void setIs_first(){
        this.first = page == 1;
    }

    private void setIs_last(){
        this.last = page == count / limits;
    }

    private void setHave_first(){
        this.haveFirst = !this.first;
    }

    private void setHave_last(){
        this.haveLast = !this.last;
    }
}
