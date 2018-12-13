package com.example.lpc.demoWeather.config;

import com.example.lpc.demoWeather.constant.WeatherConstant;
import com.example.lpc.demoWeather.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lipengcheng
 * @Date: 2018-12-12 9:07
 */
@Configuration
public class QuartzConfiguration {


    //JobDetail
    @Bean
    public JobDetail weatherDataSyncJobDetail() {
        return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJob").storeDurably().build();
    }

    //Trigger触发器
    //还可以使用CronScheduleBuilder来自定义cron表达式，更加灵活
    @Bean
    public Trigger weatherDataSyncTrigger() {

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(WeatherConstant.TIME).repeatForever();
        return TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail()).withIdentity("weatherDataSyncTrigger").withSchedule(scheduleBuilder).build();
    }
}