package com.hzsx.controller;

import com.hzsx.entrty.Student;
import com.hzsx.util.SessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private SqlSessionFactory sqlSessionFactory;
    private Reader reader;
    @RequestMapping("/exRegister")
    public void exRegister(HttpServletRequest request, HttpServletResponse response, Model model, Student student) {
        String number = request.getParameter("number");
        String verifyCode = request.getParameter("verifyCode");
        String error = SessionUtil.validate(request.getSession(), number, verifyCode);
        if (!error.equals("")) {
            renderData(response, error);
            return;
        }
        //其他业务代码
        renderData(response, "success");
        try {
            reader = Resources.getResourceAsReader("mybatis_config.xml");//连接数据库
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);//创建会话工厂
            SqlSession session = sqlSessionFactory.openSession();//打开会话
           /*调用service进行数据库操作
           *
           * */
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    protected void renderData(HttpServletResponse response, String data) {
        try {
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                sb.append(list.get(i));
            } else {
                sb.append(list.get(i));
                sb.append(separator);
            }
        }
        return sb.toString();
    }


}


