package com.heavytiger.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class WebUtils {
    /**
     * 使用BeanUtils对JAVABean进行赋值，抽取该代码
     * @param value
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T copyParamToBean(Map<String, ?> value, T bean){
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String strInt, int defaultValue){
        if(strInt == null || "null".equalsIgnoreCase(strInt)) {
            return defaultValue;
        } else {
            return Integer.parseInt(strInt);
        }
    }
}
