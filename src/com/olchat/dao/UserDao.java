package com.olchat.dao;

import com.olchat.entity.UserEntity;
import com.olchat.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by santong on 15/6/15.
 */
public class UserDao {

    public UserEntity findUser(String username) {
        Session session = null;
        UserEntity user = null;
        try {
            // 获取session
            session = HibernateUtil.getSession();
            // 开启事务
            session.beginTransaction();
            // HQL查询语句
            String hql = "from UserEntity u where u.username = ?";
            // 获取Query对象并对HQL语句动态赋值
            Query query = session.createQuery(hql).setParameter(0, username);
            // 单值检索查询User
            user = (UserEntity) query.uniqueResult();
            // 提交事务
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            // 出错将回滚事务
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            // 关闭Session对象
            HibernateUtil.closeSession();
        }
        return user;
    }

    public UserEntity checkUser(String username, String password) {
        Session session = null;
        UserEntity user = null;
        try {
            // 获取session
            session = HibernateUtil.getSession();
            // 开启事务
            session.beginTransaction();
            // HQL查询语句
            String hql = "from UserEntity u where u.username = ? and u.password = ?";
            // 获取Query对象并对HQL语句动态赋值
            Query query = session.createQuery(hql)
                    .setParameter(0, username)
                    .setParameter(1, password);
            // 单值检索查询User
            user = (UserEntity) query.uniqueResult();
            // 提交事务
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            // 出错将回滚事务
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            // 关闭Session对象
            HibernateUtil.closeSession();
        }
        return user;
    }

    public List findUserList() {
        Session session = null;
        List userList = new ArrayList<String>();
        try {
            // 获取session
            session = HibernateUtil.getSession();
            // 开启事务
            session.beginTransaction();
            // HQL查询语句
            String hql = "select u.username from UserEntity u";
            // 获取Query对象并对HQL语句动态赋值
            Query query = session.createQuery(hql);
            userList = query.list();
            // 提交事务
            session.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            // 出错将回滚事务
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            // 关闭Session对象
            HibernateUtil.closeSession();
        }
        return userList;
    }

//    public static void main(String[] args){
//        UserDao ua = new UserDao();
//        System.out.println(ua.findUserList());
//    }

}
