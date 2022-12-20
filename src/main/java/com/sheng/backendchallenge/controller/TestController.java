package com.sheng.backendchallenge.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Api(value = "Test API", tags = "Test API")
public class TestController {

    @GetMapping("/test")
    @ApiOperation("Test controller API")
    public String test(){
        return "Test controller";
    }
}
