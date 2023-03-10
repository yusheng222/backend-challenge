package com.sheng.backendchallenge.controller;

import com.sheng.backendchallenge.constant.CommonConstant;
import com.sheng.backendchallenge.dto.ResponseResult;
import com.sheng.backendchallenge.service.KafkaTransactionService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api("Transaction API")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Resource
    KafkaTransactionService kafkaTransactionService;

    /**
     * @param request
     * @param param
     * @return
     */
    @ApiOperation("Get Transaction List in Page list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "param", value = "a parameter Map contains date value in yyyy-MM pattern, and a PageNo that means which page that user looks for ",
            paramType = "Map")
    })
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseResult transactionList(@ApiParam(name = "request",value = "http request") HttpServletRequest request,
                                          @ApiParam(name = "param",value = "map contains date value and page number")
                                          @RequestParam Map<String,Object> param){
        long start = System.currentTimeMillis();
        String jwtToken = request.getHeader(CommonConstant.AUTHORIZATION);
        String date = (String) param.get(CommonConstant.DATE);
        Integer pageNo = Integer.parseInt(param.get(CommonConstant.PAGE_NO).toString());

        ResponseResult responseResult  = kafkaTransactionService.doTransactionProcess(jwtToken, date, Thread.currentThread().getName(), pageNo);
        System.out.println((System.currentTimeMillis()-start));
        return responseResult;
    }

}
