package com.cjn.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.JedisPool;

/**
 * Created by Administrator on 2020/5/5 0005.
 */
@SpringBootTest
class UserControllerTest {

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void test() {
        System.out.println(jedisPool);
    }
}