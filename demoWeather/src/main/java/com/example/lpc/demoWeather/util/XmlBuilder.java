package com.example.lpc.demoWeather.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;

/**
 * @Author: lipengcheng
 * @Date: 2018-12-07 13:57
 */
public class XmlBuilder {

    /**
     * @return java.lang.Object
     * @Author lipengcheng
     * @Description 将xml文件转成JavaBean
     * @Date 2018-12-07 14:26
     * @Param [clazz, xmlStr]
     **/
    public static Object xmlStrToObject(Class<?> clazz, String xmlStr) throws Exception {
        Object xmlObject;
        Reader reader;
        JAXBContext context = JAXBContext.newInstance(clazz);

        //XML转为对象接口
        Unmarshaller unmarshaller = context.createUnmarshaller();
        reader = new StringReader(xmlStr);
        xmlObject = unmarshaller.unmarshal(reader);

        if (null != reader) {
            reader.close();
        }
        return xmlObject;
    }
}