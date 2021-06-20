package com.cap;

import com.cap.dao.RoleDao;
import com.cap.entity.Role;
import com.cap.entity.RoleWithUsers;
import com.cap.util.MyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.util.List;

/**
 * @author C.H
 * @date 2021/6/10 14:31
 */
@Slf4j
public class CollectionTest {

    public static void main(String[] args) {
        RoleWithUsers role = MyUtil.callMapper(RoleDao.class, mapper -> mapper.getRoleWithUsers(1L));
        System.out.println(role);
    }

    @Test
    public void testInclude(){
        List<Role> list = MyUtil.callMapper(RoleDao.class, mapper -> mapper.getRoleWithUsers2(1L));
        System.out.println(list);
    }

    @Test
    public void testTrimSqlNode(){
        List<Role> list = MyUtil.callMapper(RoleDao.class, mapper -> mapper.selectByIdAndRoleName(1L,"管理员角色"));
        System.out.println(list);
    }
}
