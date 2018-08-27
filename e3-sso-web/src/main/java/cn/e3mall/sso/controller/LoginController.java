package cn.e3mall.sso.controller;

import cn.e3mall.common.utils.CookieUtils;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.sso.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Resource
    private LoginService loginService;

    @RequestMapping("/page/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping("/user/login")
    @ResponseBody
    public E3Result login(String username,
                          String password,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        E3Result result = loginService.login(username, password);
        if (result.getStatus() == 200)
            CookieUtils.setCookie(request, response, "token", result.getData().toString());

        return result;
    }
}
