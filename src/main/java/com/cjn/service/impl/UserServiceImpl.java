package com.cjn.service.impl;

import com.cjn.config.JedisConfig;
import com.cjn.entity.User;
import com.cjn.service.UserService;
import com.cjn.utils.JedisUtil;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2020/5/5 0005.
 */
@Service
public class UserServiceImpl implements UserService{
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private JedisUtil jedisUtil;

    /**
     * Redis String 类型
     * 需求：用户输入一个key,
     * 先判断Redis中是否存在，
     * 如果存在，在redis中查询返回
     * 不存在吗，在mysql数据库查询返回
     */
    @Override
    public String getString(String key) {
        // 1.得到jedis对象
        Jedis jedis = jedisPool.getResource();
        // 2.判断redis中是否存在key
        String val = null;
        if (jedis.exists(key)) {
            logger.info("查询redis中的数据");
            val = jedis.get(key);
        } else {
            logger.info("查询MYSQL的数据");
            val = "cjn";
            jedis.set(key, val);
        }
        // 3.关闭连接
        jedis.close();
        return val;
    }

    /**
     * 测试String类型
     * 需求：用户输入一个jedis数据。
     * 该key有效期为24小时
     */
    public void expireStr(String key, String value) {
        Jedis jedis = jedisUtil.getJedis();
        jedis.set(key, value);
        jedis.expire(key, 20);
        logger.info("{}\t设置值为：{}", key, value);
        jedisUtil.close(jedis);
    }
    /**
     * Redis Hash类型
     * 需求：在前端传入一个ID编写，
     * 根据ID，查询到用户对象信息
     * 如果redis中存在，直接返回，
     * 如果不存在，则查询mysql并返回
     */
    @Override
    public User selectById(String id) {
        String key = "user:" + id; // 实体类名:id
        // 1.获取jedis对象
        Jedis jedis = jedisUtil.getJedis();
        User user = new User();
        // 2.实现业务逻辑
        if (jedis.exists(key)) {
            logger.info("----------查询的是redis---------");
            Map<String,String> map = jedis.hgetAll(key);
            user.setId(map.get("id"));
            user.setName(map.get("name"));
            user.setAge(Integer.parseInt(map.get("age")));
        } else {
            user = new User(id, "Sky", 20);
            logger.info("----------查询的是mysql---------{}",user);
            // 存入redis
            logger.info("----------存入redis---------");
            Map<String, String> map = new HashMap<>();
            map.put("id", user.getId());
            map.put("name", user.getName());
            map.put("age", String.valueOf(user.getAge()));
            jedis.hmset(key, map);

        }
        // 3.关闭连接
        jedisUtil.close(jedis);
        return user;
    }
}
