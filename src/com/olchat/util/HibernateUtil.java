package com.olchat.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;


/**
 * Created by santong on 15/6/16.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory = null;
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

    static {
        try {
            Configuration cfg = new Configuration().configure();
            sessionFactory = cfg.buildSessionFactory(new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry());
        } catch (Exception e) {
            System.err.println("创建会话工厂失败");
            e.printStackTrace();
        }
    }

    public static Session getSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        if (session == null || !session.isOpen()) {
            if (sessionFactory == null) {
                rebuildSessionFactory();
            }
            session = (sessionFactory != null) ? sessionFactory.openSession() : null;
            threadLocal.set(session);
        }
        return session;
    }

    private static void rebuildSessionFactory() {
        try {
            Configuration cfg = new Configuration().configure();    //加载Hibernate配置文件
            sessionFactory = cfg.buildSessionFactory(new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry());
        } catch (Exception e) {
            System.err.println("创建会话工厂失败");
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeSession() throws HibernateException {
        Session session = (Session)threadLocal.get();
        threadLocal.set(null);
        if (session != null) {
            session.close();    // 关闭session
        }
    }

}
