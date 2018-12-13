package com.example.lpc.demoWeather.job;

import com.example.lpc.demoWeather.entity.City;
import com.example.lpc.demoWeather.service.CityDataService;
import com.example.lpc.demoWeather.service.WeatherDataService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

/**
 * @Author: lipengcheng
 * @Date: 2018-12-07 10:52
 */
public class WeatherDataSyncJob extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    private CityDataService cityDataService;

    @Autowired
    private WeatherDataService weatherDataService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        logger.info("数据同步Job,Start!");
        List<City> cityList = null;
        //获取城市ID列表
        try {
            cityList = cityDataService.getCityList();
        } catch (Exception e) {
            logger.error("Exception = " + e.toString());
        }

        //遍历城市ID获取天气
        for (City city : cityList) {

            String cityId = city.getCityId();
            logger.info("数据同步Job,cityId:" + cityId);
            weatherDataService.syncDataByCityId(cityId);
        }
    }
}