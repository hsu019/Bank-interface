package com.example.demo.controller;


import com.example.demo.common.R;
import com.example.demo.mapper.TransactionMapper;
import com.example.demo.models.Transaction;
import com.example.demo.models.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api(value = "transaction", tags = "transaction-API")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private TransactionMapper transactionMapper;

    @ApiOperation(value="SelectTransaction", notes = "SelectTransaction")
    @GetMapping("/list/{token}/{month}/{pageNum}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", dataType = "String", paramType = "query", required = true, example = ""),
            @ApiImplicitParam(name = "month", value = "month", dataType = "int", paramType = "query", required = true, example = "1"),
            @ApiImplicitParam(name = "pageNum", value = "pageNum", dataType = "int", paramType = "query", required = true, example = "0")
    })
    public R list(@PathVariable("token") String token,
                  @PathVariable("month") int month,
                  @PathVariable("pageNum") int pageNum) {
        ArrayList<Transaction> list = new ArrayList<>();
        User user = userService.selectUserFromToken(token);
        // 根据用户信息查询所有卡号
        List<String> card_list = userService.selectCardFromUserId(user.getId());
        // 根据月份和卡号从卡夫卡topic中查询数据
        for (String item : card_list) {
            list.addAll(transactionMapper.selectTransaction(item, month));
        }
        // 得到数据进行分页
        ArrayList<Transaction> result = new ArrayList<>();
        for (int i = (pageNum - 1) * 10; i <= pageNum * 10 - 1 && i < list.size(); i++) {
            // 调取外部API进行处理
            list.get(i).setNum((int) (list.get(i).getNum() * Util.rateConversion(list.get(i).getMoneyType())));
            result.add(list.get(i));
        }
        return R.ok().put("list", result);
    }

    //    test
    @GetMapping("/hello")
    public R hello(){
        return R.ok().put("page", 1);
    }
}
