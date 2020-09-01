package com.hzsx.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisUtil {
    //sqlSessionFactory对象名用final修饰，指明该对象创建后不能改变
    private final static SqlSessionFactory sqlSessionFactory;
    static {
        String resource = "mybatis_config.xml";
        Reader reader = null;
        try {
            //使用MyBatis提供的Resources类加载MyBatis配置文件
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //构建SqlSessionFactory工厂
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }
    //获取SqlSession对象的静态方法
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }
}
