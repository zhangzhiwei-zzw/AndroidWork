package com.it.controller;

import com.it.model.Comment;
import com.it.model.User;
import com.it.servic.CommentService;
import com.it.servic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class WorkController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/alluser")
    @ResponseBody
    public Object getAllUser(){
        return userService.selectAllUser();
    }

    @RequestMapping(value = "/getUserById")
    @ResponseBody
    public Object getUserById(String account,String password){
        User user=userService.getUserById(account);
        if(user!=null){
            if(user.getPassword().equals(password)){
                return user;
            }else {
                return "密码不正确";
            }
        }else {
            return "账号尚未注册";
        }
    }


    @RequestMapping(value = "/insertUser")
    @ResponseBody
    public Object insertUser(User user1){
        User user=userService.getUserById(user1.getAccount());
        if(user!=null){
            return "该用户已经存在";
        }else {
            userService.insertUser(user1);
            return "用户注册成功";
        }
    }

    @RequestMapping(value = "/getAllComment")
    @ResponseBody
    public Object getAllComment(){
        return commentService.getAllComment();
    }
    @RequestMapping(value = "/addComment")
    @ResponseBody
    public Object addComment(HttpServletRequest request){
        System.out.println(request.getParameterNames());
        Comment comment=new Comment();
        comment.setComments(request.getParameter("comments"));
        comment.setTime(new Date());
        comment.setImageid(request.getParameter("imageid"));
        comment.setPosition(request.getParameter("position"));
        comment.setUsername(request.getParameter("username"));
//        return "添加成功";
//        comment.setTime(new Date());
//        System.out.println(comment);
        if(commentService.addComment(comment)!=null)
        {
            return "添加成功";
        }else {
            return "添加失败";
        }
    }
}
