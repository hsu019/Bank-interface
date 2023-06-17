package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


class UserControllerTest {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    MockMvc mockMvc;

    //初始化mockmvc
    @BeforeEach
    void setUp(){
        //自己指定要测试的Controller
        mockMvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
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


    @Test
    void admin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        //访问的URL和参数
                        .get("/user/admin/hello")
                        //允许的编码格式
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                //输出请求和响应结果
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void user() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        //访问的URL和参数
                        .get("/user/user/hello")
                        //允许的编码格式
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                //输出请求和响应结果
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void dba() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        //访问的URL和参数
                        .get("/user/db/hello")
                        //允许的编码格式
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                //输出请求和响应结果
                .andDo(MockMvcResultHandlers.print());
    }
}