package com.dlg.projectmodule.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.File;

@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)// 当属性中存在null值时不序列化属性
public class Response<T> {

    private int code;

    private String message;

    T data;

    // 操作不返回数据
    public static Response SUCCESS = new Response(0, "操作成功");
    public static Response FAILED = new Response(1, "操作失败");

    public Response(){}

    public Response(T t){
        this.code = 0;
        this.message = "操作成功";
        this.data = t;
    }

    public Response(Boolean result){
        if(result){
            this.code = 0;
            this.message = "操作成功";
        }else{
            this.code = 1;
            this.message = "操作失败";
        }
    }

    public Response(int code, String message){
        this.code = code;
        this.message = message;
    }

    public Response(int code, String message, T t){
        this.code = code;
        this.message = message;
        this.data = t;
    }

    public Response(Throwable ex){
        this.code = 2;
        this.message = ex.getMessage();
    }

}
