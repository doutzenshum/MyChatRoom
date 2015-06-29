package com.olchat.action;

import com.olchat.dao.UserDao;
import com.olchat.entity.UserEntity;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import java.util.Map;

/**
 * Created by santong on 15/6/16.
 */
public class LoginAction extends ActionSupport {

    private String username;
    private String password;
    private List userlist;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String isCorrect() {
        // 实例化UserDao
        UserDao dao = new UserDao();
        // 查询获取User对象
        UserEntity user = dao.checkUser(username, password);
        userlist = dao.findUserList();
        if (user != null){
            ActionContext ctx = ActionContext.getContext();
            Map app = ctx.getApplication();
            ctx.getSession().put("username", user.getUsername());
            return SUCCESS;
        }
        return LOGIN;
    }

    public List getUserlist() {
        return userlist;
    }

    public void setUserlist(List userlist) {
        this.userlist = userlist;
    }
}
