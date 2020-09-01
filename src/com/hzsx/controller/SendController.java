package com.hzsx.controller;


import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hzsx.config.Config;
import com.hzsx.util.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/message")
public class SendController {
    /**
     * 跳转发送验证码页面
     */
    @RequestMapping("/send")
    public String send() {
        return "code";
    }

    /**
     * 执行发送验证码操作
     */
    @RequestMapping("/exSend")
    public void exSend(HttpServletRequest request,HttpServletResponse response) {
        try {
            String number = request.getParameter("number");
            String templateId = request.getParameter("templateId");
            JSONObject json = null;

            //生成6位验证码
            String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
            System.out.print("验证码: " + verifyCode);
            ZhenziSmsClient client = new ZhenziSmsClient(Config.apiUrl, Config.appId, Config.appSecret);
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("number", number);
            params.put("templateId", templateId);
            String[] templateParams = {verifyCode, "5分钟内有效"};
            params.put("templateParams", templateParams);
            String result = client.send(params);
            json = JSONObject.parseObject(result);
            if (json.getIntValue("code") != 0) {//发送短信失败
                renderData(response, "获取失败:" + json.getString("data"));
                return;
            }
            //将验证码存到session中,同时存入创建时间
            HttpSession session = request.getSession();
            SessionUtil.save(session, number, verifyCode, 5 * 60);
            renderData(response, "success");
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected void renderData(HttpServletResponse response, String data){
        try {
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
