package com.cap.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @author C.H
 * @date 2021/6/10 14:32
 */
public class MyUtil {

    private static final Log log = LogFactory.getLog(MyUtil.class);

    public static final SqlSessionFactory SQL_SESSION_FACTORY = build();

    private static SqlSessionFactory build() {
        log.debug("=========init SqlSessionFactory===========");
        try {
            return new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    public interface SessionCall<O> {
        O call(SqlSession session);
    }

    @FunctionalInterface
    public interface MapperCall<T, O> {
        O doBus(T mapper) ;
    }

    public static <T, O> O callMapper(Class<T> tClass, MapperCall<T, O> mapper) {
        return call(session -> mapper.doBus(session.getMapper(tClass)));
    }

    public static <O> O call(SessionCall<O> sessionCall) {
        try (SqlSession session = SQL_SESSION_FACTORY.openSession(true)) {
            return sessionCall.call(session);
        }
    }
}
