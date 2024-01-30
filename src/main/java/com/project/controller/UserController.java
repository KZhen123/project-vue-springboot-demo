package com.project.controller;

import com.project.constant.ResponseCodes;
import com.project.model.PageQueryResultModel;
import com.project.model.Result;
import com.project.model.dto.request.UserQuery;
import com.project.model.entity.User;
import com.project.service.UserService;
import com.project.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "selectPage", produces = "application/json")
    @ResponseBody
    public Result selectPage(@RequestBody UserQuery userQuery) {
        PageQueryResultModel<User> userVoPageQueryResultModel = userService.selectPage(userQuery);
        return Result.success(userVoPageQueryResultModel);
    }

    @RequestMapping(value = "register")
    public String register(@RequestBody User user, HttpServletRequest req){
        String username = req.getParameter("username");
        String pwd = req.getParameter("password");
        String phone = req.getParameter("phone");
        if(!verifyCode(req)){
            req.setAttribute("msg","验证码错误！");
            req.setAttribute("username",username);
            req.setAttribute("pwd",pwd);
            // 转发注册页面
            return "user/register";
        }
        User userByName = userService.getUserByName(username);
        if(userByName != null){
            req.setAttribute("msg","用户已存在！");
            req.setAttribute("username",username);
            // 跳回注册页面
            return "user/register";
        }
        int add = userService.add(user);
        if (add == 1) {
            req.setAttribute("msg","");
            return "user/login";
        } else {
            req.setAttribute("msg","注册失败！");
            req.setAttribute("username",username);
            // 跳回注册页面
            return "user/register";
        }
    }

    @PostMapping(value = "login", produces = "application/json")
    @ResponseBody
    public Result login(@RequestBody User user, HttpServletRequest req){
        String username = user.getUsername();
        String pwd = user.getPassword();
//        if(!verifyCode(req)){
//            return Result.build(ResponseCodes.PARAMETERS_ERROR,"验证码错误");
//        }

        User userByName = userService.getUserByName(username);
        if (userByName == null) {
            return Result.build(ResponseCodes.PARAMETERS_ERROR,"用户不存在");
        } else if (!MD5Util.encrypt(pwd).equals(userByName.getPassword())) {
            return Result.build(ResponseCodes.PARAMETERS_ERROR,"密码错误");
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("token",username);
            return Result.success(map);
        }
    }

    /*
    * 前端回调用该方法进行登陆验证
    * */
    @GetMapping(value = "info")
    @ResponseBody
    public Result getUserInfo(@RequestParam("token") String token) {
        User user = userService.getUserByName(token);
        if (user == null) {
            return Result.build(ResponseCodes.PARAMETERS_ERROR,"用户不存在");
        } else {
            Map<String, Object> data = new HashMap<>();
            //这个参数中的名字要与前端对应上
            data.put("name",user.getUsername());
            data.put("avatar",user.getUimage());

            //得到角色列表
//            List<String> roleList = this.baseMapper.getRoleNameByUserId(loginUser.getId());
//            data.put("roles",roleList);
            return Result.success(data);
        }
    }

    @RequestMapping(value = "logout", produces = "application/json")
    @ResponseBody
    public void logout(HttpServletRequest request) {
        // 清除session
        request.getSession().removeAttribute("user");
    }

    private boolean verifyCode(HttpServletRequest req){
        // 验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = req.getParameter("code");
        if(token==null || !token.equalsIgnoreCase(code)){
            return false;
        }
        return true;
    }

}
