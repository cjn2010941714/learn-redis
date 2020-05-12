package com.cjn.service.impl;

import com.cjn.service.SetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.swing.*;
import java.util.Set;


/**
 * Created by Administrator on 2020/5/11 0011.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SetServiceImplTest {
    @Autowired
    private SetService setService;
    @Test
    public void addBooks() {
        Set<String> set = setService.addBooks();
        if (set.size() > 0) {
            System.out.println(set.size());
        }
    }

}