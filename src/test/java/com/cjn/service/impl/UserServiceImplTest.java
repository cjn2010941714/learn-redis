package com.cjn.service.impl;

import com.cjn.entity.User;
import com.cjn.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Administrator on 2020/5/5 0005.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        String result = userService.getString("name1");
        System.out.println(result);
    }

    @Test
    public void test2() {
        String key = "testKey";
        String value = "测试数据";
        userService.expireStr(key, value);
    }

    @Test
    public void test3() {
        String id = "1";
        User user = userService.selectById(id);
        System.out.println(user.toString());
    }
}