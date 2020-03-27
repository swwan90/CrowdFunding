package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName AdminHandler
 * @Description TODO
 * @Author swwan
 * @Date 2020/3/27 16:26
 * @Version 1.0
 **/
@Controller
public class AdminHandler {

    @Autowired
    private AdminService adminService;
    /**
     * @author swwan
     * @Description 登录
     * @Date 2020/3/27
     * @param loginAcct
 * @param userPswd
 * @param session
     * @return java.lang.String
     **/
    @RequestMapping("/admin/do/login.html")
    public String doLogin(
            @RequestParam("loginAcct") String loginAcct,
            @RequestParam("userPswd") String userPswd,
            HttpSession session
    ){
        // 调用 Service 方法执行登录检查
        // 这个方法如果能够返回admin对象说明登录成功，如果账号、密码不正确会抛出异常
        Admin admin = adminService.getAdminByLoginAcct(loginAcct,userPswd);

        // 将登录成功返回的 admin 对象存入 Session 域
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);

        return "redirect:/admin/to/main/page.html";
    }

    /**
     * @author swwan
     * @Description 退出登陆
     * @Date 2020/3/27
     * @param session
     * @return java.lang.String
     **/
    @RequestMapping("/admin/do/logout.html")
    public String doLogout(HttpSession session){

        // 强制失效
        session.invalidate();

        return "redirect:/admin/to/login/page.html";
    }
}
