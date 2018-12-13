package com.example.lpc.demoWeather.controller;

import com.example.lpc.demoWeather.service.CityDataService;
import com.example.lpc.demoWeather.service.WeatherDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: lipengcheng
 * @Date: 2018-12-12 11:29
 * @Description 后台请求控制器
 */
@RestController
@RequestMapping("/report")
public class WeatherReportController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherReportController.class);

    @Autowired
    private WeatherDataService weatherDataService;

    @Autowired
    private CityDataService cityDataService;

    @GetMapping("/cityId/{cityId}")
    public ModelAndView getReportByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {

        model.addAttribute("title", "test");
        model.addAttribute("cityId", cityId);
        model.addAttribute("cityList", cityDataService.getCityList());
        model.addAttribute("report", weatherDataService.getWeatherDataByCityId(cityId).getData());

        logger.info("WeatherReportController cityId = " + cityId);
        return new ModelAndView("weather/report", "reportModel", model);
    }
}