package com.cap;

import com.cap.dao.RoleDao;
import com.cap.entity.Role;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Captain·H
 * @date 2020/6/19 16:11
 */
public class Main {
    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 默认创建DefaultSqlSessionFactory C.H_20210228
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = null;
        try {
            Configuration configuration = sqlSessionFactory.getConfiguration();
            sqlSession = sqlSessionFactory.openSession();
            // 创建RoleDao代理,默认用JDK代理创建 C.H_20210228
            RoleDao roleMapper = sqlSession.getMapper(RoleDao.class);
            Role role = roleMapper.getRole(1L);
            System.out.println(role.getId() + ":" + role.getRoleName() + ":" + role.getNote());
            sqlSession.commit();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
}
