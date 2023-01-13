package com.sheng.backendchallenge.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sheng.backendchallenge.constant.CommonConstant;
import com.sheng.backendchallenge.dto.ExchangeRateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExchangeRateUtils {

    @Autowired
    RestTemplate restTemplate;
    @Value("${exchange.url}")
    String exchangeUrl;
    @Value("${exchange.app-id}")
    String appId;
    @Value("${exchange.app-secret}")
    String appSecret;


    public Map<String, ExchangeRateDto> requestExchangeRate() {
        StringBuilder url = new StringBuilder();
        url.append(exchangeUrl);
        url.append("?");
        url.append("app_id=" + appId);
        url.append("&");
        url.append("app_secret=" + appSecret);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url.toString(), String.class);

        String body = forEntity.getBody();

        // in case the current exchange rate required, every invocation will request an exchange rate;
        JSONObject exchangeJsonObject = JSONObject.parseObject(body);
        Integer code = exchangeJsonObject.getInteger("code");
        // if the request fail, return null
        if (code != 1) {
            return null;
        }
        JSONArray data = exchangeJsonObject.getJSONArray("data");
        Map<String, ExchangeRateDto> exchangeMap = new HashMap<>();
        for (int i = 0; i < data.size(); i++) {
            JSONObject jsonObject = data.getJSONObject(i);
            String name = jsonObject.getString("name");
            String nameDesc = jsonObject.getString("nameDesc");
            String from = jsonObject.getString("from");
            String to = jsonObject.getString("to");
            Double price = jsonObject.getDouble("price");
            ExchangeRateDto dto = new ExchangeRateDto();
            dto.setName(name);
            dto.setNameDesc(nameDesc);
            dto.setFrom(from);
            dto.setTo(to);
            dto.setPrice(price);
            exchangeMap.put(name,dto);
        }
        return exchangeMap;
    }

}
