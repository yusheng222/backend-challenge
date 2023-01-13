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
        //String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZGVudGlmaWVyIjoiMzUyMjI3MTk5NjAyMjIxMzE1IiwiZXhwIjoxNjczMjMwNzY5fQ.6Pka3PXWT_R8uZ9TryWXkC-KzI9jhysRaRJ2kMnE11U";
        Map<String,String> map = new HashMap<>();
        map.put("identifier","352227199602221315");
        String token = JwtUtils.generateToken(map);
        TokenResult tokenResult = JwtUtils.parseToken(token);
        String identifier = tokenResult.getIdentifier();
        System.out.println(identifier);
    }

    @Test
    void generateToken() {
        Map<String,String> map = new HashMap<>();
        map.put("identifier","352227199602221315");
        String token = JwtUtils.generateToken(map);
        System.out.println(token);
    }
}