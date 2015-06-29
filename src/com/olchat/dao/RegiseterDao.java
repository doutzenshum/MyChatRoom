package com.olchat.dao;

import com.olchat.entity.UserEntity;
import com.olchat.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Created by santong on 15/6/28.
 */
public class RegiseterDao {

    public boolean addUser(String username, String password) {
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
        return false;
    }
}
