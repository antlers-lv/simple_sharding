package com.itachi.leetcode.controller;

import com.itachi.leetcode.persistence.dao.User1DAO;
import com.itachi.leetcode.persistence.po.User1PO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : xutigrong
 * @date : 2020/1/21
 */
@RestController
@RequestMapping("demo")
public class TestController {

    @Resource
    private User1DAO user1DAO;

    @RequestMapping("test")
    public void t1() {
        for (int i = 0; i < 20; i++) {
            User1PO po = new User1PO();
            po.setName("张三" + i);
            po.setAge(1);
            po.setSex(i);
            user1DAO.save(po);
        }
    }

}
