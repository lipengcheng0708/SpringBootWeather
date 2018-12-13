package com.example.lpc.demoWeather.service;

import com.example.lpc.demoWeather.entity.WeatherResponse;

/**
 * @Author: lipengcheng
 * @Date: 2018-12-05 15:54
 */
public interface WeatherDataService {

    //根据城市ID查询数据
    WeatherResponse getWeatherDataByCityId(String cityId);

    //根据城市名称查询数据
    WeatherResponse getWeatherDataByCityName(String cityName);

    /**
     * 根据城市Id来同步天气
     * @param cityId
     */
    void syncDataByCityId(String cityId);
}
