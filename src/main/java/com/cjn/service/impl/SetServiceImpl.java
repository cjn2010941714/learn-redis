package com.cjn.service.impl;

import com.cjn.entity.User;
import com.cjn.service.SetService;
import com.cjn.utils.JedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2020/5/11 0011.
 */
@Service
public class SetServiceImpl implements SetService{
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public Set<String> addBooks() {
        try {
            Jedis jedis = jedisUtil.getJedis();
            jedis.sadd("books", "三国演义", "红楼梦", "西游记");
            Set<String > set = jedis.smembers("books");
            jedisUtil.close(jedis);
            return set;
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
       return null;
    }
}
