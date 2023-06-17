package com.example.demo.common;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class RTest {



    @Test
    void error() {
        R r = new R();
        R.error(400, "error!");
        System.out.println(r);
    }

    @Test
    void testError() {
        R r = new R();
        R.error();
        System.out.println(r);
    }

    @Test
    void testError1() {
        R r = new R();
        R.error("error!");
        System.out.println(r);
    }

    @Test
    void ok() {
        R r = new R();
        R.ok();
        System.out.println(r);
    }

    @Test
    void testOk() {
    }

    @Test
    void testOk1() {
    }

    @Test
    void put() {
        R r = new R();
        r.put("info", "111");
        System.out.println(r);
    }
}