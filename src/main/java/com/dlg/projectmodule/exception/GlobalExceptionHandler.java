package com.dlg.projectmodule.exception;

import com.dlg.projectmodule.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // 返回json错误
    @ResponseBody
    @ExceptionHandler(GlobalException.class)
    public Response handleControllerException(HttpServletRequest request, Throwable ex){

        System.out.println("exception message: " + ex.toString());

        return new Response(ex);

    }

    // 返回json错误
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Response handleControllerExceptionOther(HttpServletRequest request, Throwable ex){

        System.out.println("exception message: " + ex.toString());

        return new Response(ex);

    }

    // 返回页面错误
    /*@ExceptionHandler(GlobalException.class) // 同一exception只能对应一种方法
    public ModelAndView handleControllerExceptionPage(HttpServletRequest request, Throwable ex, ModelAndView mav){

        System.out.println("exception message: " + ex.toString());

        mav.addObject("message", ex.getMessage());
        mav.setViewName("/error/4xx");

        return mav;

    }*/

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

}
