package com.example.lpc.demoWeather.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @Author: lipengcheng
 * @Date: 2018-12-07 11:04
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "c")
public class CityList {

    @XmlElement(name = "d")
    private List<City> cityList;

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    @Override
    public String toString() {
        return "CityList{" +
                "cityList=" + cityList +
                '}';
    }
}