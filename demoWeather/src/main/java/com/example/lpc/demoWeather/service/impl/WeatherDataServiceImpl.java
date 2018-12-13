package com.example.lpc.demoWeather.service.impl;

import com.example.lpc.demoWeather.constant.WeatherConstant;
import com.example.lpc.demoWeather.entity.WeatherResponse;
import com.example.lpc.demoWeather.service.WeatherDataService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lipengcheng
 * @Date: 2018-12-05 16:00
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(WeatherDataServiceImpl.class);


    @Override
    public WeatherResponse getWeatherDataByCityId(String cityId) {

        String uri = WeatherConstant.WEATHER_URI + "citykey=" + cityId;
        System.out.println("uri = " + uri);
        return this.doGetWeather(uri);
    }

    @Override
    public WeatherResponse getWeatherDataByCityName(String cityName) {

        String uri = WeatherConstant.WEATHER_URI + "city=" + cityName;
        System.out.println("uri = " + uri);
        return this.doGetWeather(uri);
    }

    @Override
    public void syncDataByCityId(String cityId) {

        String uri = WeatherConstant.WEATHER_URI + "citykey=" + cityId;
        this.saveWeatherData(uri);
    }

    private WeatherResponse doGetWeather(String uri) {

        //返回响应体
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();

        //将接口返回的Json字符串转换成对象
        ObjectMapper mapper = new ObjectMapper();
        WeatherResponse resp = null;

        //返回的结构体
        String strBody = null;

        if (stringRedisTemplate.hasKey(uri)) {
            //先查缓存，如果缓存中有信息就在缓存中取
            logger.info("Redis has data");
            strBody = valueOperations.get(uri);

        } else {
            //如果缓存没有，再去调用服务接口来获取
            logger.info("Redis doesn't has data");

            if (respString.getStatusCodeValue() == 200) {
                strBody = respString.getBody();
            }
            //数据写入缓存,
//          valueOperations.set(uri, strBody, WeatherConstant.TIME_OUT, TimeUnit.SECONDS);
        }

        try {
            resp = mapper.readValue(strBody, WeatherResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("doGetWeather IOException e.toString = " + e.toString());
            logger.error("doGetWeather IOException e.getMessage = " + e.getMessage());
        }
        return resp;
    }

    /**
     * @return void
     * @Author lipengcheng
     * @Description 把天气数据放入缓存，相当于更新缓存
     * @Date 2018-12-07 14:54
     * @Param [uri]
     **/
    private void saveWeatherData(String uri) {

        String strBody = null;
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        //调用服务接口来获取
        ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);

        //将接口返回的Json字符串转换成对象
        if (respString.getStatusCodeValue() == 200) {
            strBody = respString.getBody();
        }
        //数据写入缓存，设置过期的时间为30分钟
        ops.set(uri, strBody, WeatherConstant.TIME_OUT, TimeUnit.SECONDS);
        logger.info("数据写入缓存");
    }
}