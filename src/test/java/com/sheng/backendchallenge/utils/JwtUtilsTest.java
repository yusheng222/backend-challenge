package com.sheng.backendchallenge.utils;

import com.sheng.backendchallenge.dto.TokenResult;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilsTest {

    @Test
    void parseToken() {
        //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZGVudGlmaWVyIjoiODlkM28xNzktYWJjZC00NjViLW85ZWUtZTJkNWY2b2ZFbGQ0NiIsImV4cCI6MTY3MTY5MjQ5OH0.DqyrJMhoE5B6SX7bC0Myab6GM_EnWpt9mC--XLDYYas
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZGVudGlmaWVyIjoiODlkM28xNzktYWJjZC00NjViLW85ZWUtZTJkNWY2b2ZFbGQ0NiIsImV4cCI6MTY3MTg2MjkwMH0.Z7Rz55B6jT0zaxo_w5xMlDJiYko0AY5eHDeZYQ2lpC0";
        TokenResult tokenResult = JwtUtils.parseToken(token);
        String identifier = tokenResult.getIdentifier();
        System.out.println(identifier);
    }

    @Test
    void generateToken() {
        Map<String,String> map = new HashMap<>();
        map.put("identifier","89d3o179-abcd-465b-o9ee-e2d5f6ofEld46");
        String token = JwtUtils.generateToken(map);
        System.out.println(token);
    }
}