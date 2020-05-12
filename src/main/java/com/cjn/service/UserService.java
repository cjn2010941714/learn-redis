package com.cjn.service;

import com.cjn.entity.User;

/**
 * Created by Administrator on 2020/5/5 0005.
 */
public interface UserService {

    /**
     * Redis String 类型
     * 需求：用户输入一个key,
     * 先判断Redis中是否存在，
     * 如果存在，在redis中查询返回
     * 不存在吗，在mysql数据库查询返回
     */
    public String getString(String key);

    public void expireStr(String key, String value);

    /**
     * Redis Hash类型
     * 需求：在前端传入一个ID编写，
     * 根据ID，查询到用户对象信息
     * 如果redis中存在，直接返回，
     * 如果不存在，则查询mysql并返回
     */
    public User selectById(String id);

}
