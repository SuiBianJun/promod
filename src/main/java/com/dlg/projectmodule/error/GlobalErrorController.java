package com.dlg.projectmodule.error;

import com.dlg.projectmodule.response.Response;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GlobalErrorController implements ErrorController {

    private final String ERROR_PATH = "/error";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    // 一般页面请求返回
    // 页面定制
    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public ModelAndView errorPageHandler(HttpServletRequest request, ModelAndView mav){

        System.out.println("error controller");
        Integer statusCode = getStatus(request).value();

        mav.addObject("statuc_code", statusCode);
        // 添加其他信息
        mav.setViewName("/error/4xx");// 需要后台模板支持，才能将数据推送到页面

        return mav;

    }

    // json请求错误返回
    @ResponseBody
    @RequestMapping(value = ERROR_PATH)
    public Response errorPageHandler(){

        return Response.FAILED;

    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
