package com.example.lpc.demoWeather.service;

import com.example.lpc.demoWeather.entity.City;

import java.util.List;

/**
 * @Author: lipengcheng
 * @Date: 2018-12-07 11:12
 */
public interface CityDataService {

    /**
     * @return java.util.List<com.example.lpc.demoWeather.entity.City>
     * @Author lipengcheng
     * @Description 获取City列表
     * @Date 2018-12-07 11:14
     * @Param []
     **/
    List<City> getCityList() throws Exception;
}
