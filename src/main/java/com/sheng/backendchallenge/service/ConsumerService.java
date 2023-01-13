package com.sheng.backendchallenge.service;


import java.util.List;

public interface ConsumerService {

    void consumerByTime(String groupId, long beginTime, long endTime, List<Long> accNums,Long cusId);

}
