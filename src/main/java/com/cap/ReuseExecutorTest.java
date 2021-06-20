package com.cap;

import com.cap.dao.RoleDao;
import com.cap.entity.Role;
import com.cap.util.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

/**
 * @author C.H
 * @date 2021/6/10 14:31
 */
@Slf4j
public class ReuseExecutorTest {

    public static void main(String[] args) {
        // Role role = MyUtil.callMapper(RoleDao.class, mapper -> mapper.getRole(1L));
        // System.out.println(role);

        // 没测明白
        SqlSessionFactory sqlSessionFactory = MyUtil.SQL_SESSION_FACTORY;
        Configuration configuration = sqlSessionFactory.getConfiguration();
        System.out.println(configuration.getDefaultExecutorType());
        configuration.setDefaultExecutorType(ExecutorType.REUSE);
        System.out.println(configuration.getDefaultExecutorType());
        try (SqlSession session = MyUtil.SQL_SESSION_FACTORY.openSession(true)) {
            RoleDao roleMapper = session.getMapper(RoleDao.class);
            Role role = roleMapper.getRole(1L);
            System.out.println(role);

            role = roleMapper.getRole(1L);
            System.out.println(role);
        }

        System.out.println();
        try (SqlSession session = MyUtil.SQL_SESSION_FACTORY.openSession(true)) {
            RoleDao roleMapper = session.getMapper(RoleDao.class);
            Role role = roleMapper.getRole(1L);
            System.out.println(role);

            role = roleMapper.getRole(1L);
            System.out.println(role);
        }
    }
}
