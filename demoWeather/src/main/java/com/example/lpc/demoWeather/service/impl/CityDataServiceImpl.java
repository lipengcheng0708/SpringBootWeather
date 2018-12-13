package com.example.lpc.demoWeather.service.impl;

import com.example.lpc.demoWeather.entity.City;
import com.example.lpc.demoWeather.entity.CityList;
import com.example.lpc.demoWeather.service.CityDataService;
import com.example.lpc.demoWeather.util.XmlBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @Author: lipengcheng
 * @Date: 2018-12-07 11:11
 */
@Service
public class CityDataServiceImpl implements CityDataService {

    @Override
    public List<City> getCityList() throws Exception {

        //读取xml文件
        Resource resource = new ClassPathResource("cityList.xml");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
        StringBuffer sb = new StringBuffer();
        String dataLine;

        while ((dataLine = bufferedReader.readLine()) != null) {
            sb.append(dataLine);
        }
        bufferedReader.close();

        //xml字符串转换成CityList对象
        CityList cityList = (CityList) XmlBuilder.xmlStrToObject(CityList.class, sb.toString());
        return cityList.getCityList();
    }
}
