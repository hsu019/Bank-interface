package com.example.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class TransactionControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    MockMvc mockMvc;


    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }


    @Test
    void contextLoads() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        //访问的URL和参数
                        .get("/hello")
                        //允许的编码格式
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                //输出请求和响应结果
                .andDo(MockMvcResultHandlers.print());
    }


    @Test
    void list() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        //访问的URL和参数
                        .get("/list/231423/10/1")
                        //允许的编码格式
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                //输出请求和响应结果
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void hello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        //访问的URL和参数
                        .get("/hello")
                        //允许的编码格式
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                //输出请求和响应结果
                .andDo(MockMvcResultHandlers.print());
    }
}