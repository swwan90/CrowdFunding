package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import com.google.gson.Gson;
import com.sun.org.apache.xml.internal.security.keys.content.RetrievalMethod;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 基于注解的异常处理器类
 * @author swwan
 * @create 2020-03-27 11:55
 */

// 表示当前是一个处理异常的类
@ControllerAdvice
public class CrowdExceptionResolver {

    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolveLoginFailedException(
            LoginFailedException exception,
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException{
        String viewName = "admin-login";

        return commResolve(viewName,exception,request,response);
    }

    /**
     * @author swwan
     * @Description
     * @Date 2020/3/27
     * @param viewName 异常处理完成后要去的页面
     * @param exception 实际捕获到的异常类型
     * @param request 当前请求对象
     * @param response 当前响应对象
     * @return org.springframework.web.servlet.ModelAndView
     **/
    private ModelAndView commResolve(
            String viewName,
            Exception exception,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException{

        // 1.判断当前请求类型
        boolean judgeResult = CrowdUtil.judgeRequestType(request);

        // 2.如果是 Ajax 请求
        if(judgeResult) {

            // 3.创建 ResultEntity 对象
            ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());

            // 4.创建 Gson 对象
            Gson gson = new Gson();

            // 5.将 ResultEntity 对象转换成 JSON 对象
            String json = gson.toJson(resultEntity);

            // 6.将 JSON 字符串作为响应体返回给浏览器
            response.getWriter().write(json);

            // 7.由于上面已经通过原生的 response 对象返回了响应，所以不提供 ModelAndView 对象
            return null;
        }

        // 8.如果不是 Ajax 请求则创建 ModelAndView 对象
        ModelAndView modelAndView = new ModelAndView();

        // 9.将 Exception 对象放入模型
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION, exception);

        // 10.设置对应的视图名称
        modelAndView.setViewName(viewName);

        // 11.返回 modelAndView 对象
        return modelAndView;

    }
}
