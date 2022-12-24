package com.sheng.backendchallenge.controller;

import com.sheng.backendchallenge.dto.ResponseResult;
import com.sheng.backendchallenge.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api("Transaction API")
@RestController
@RequestMapping("/transaction")
public class transactionController {

    @Resource
    TransactionService transactionService;

    @ApiOperation("Transaction")
    @RequestMapping(value = "/list/{date}",method = RequestMethod.GET)
    public ResponseResult transactionList(@RequestBody String jwtToken, @PathVariable("date") String date){
        transactionService.doTransactionProcess(jwtToken,date);
        return ResponseResult.success();
    }

}
