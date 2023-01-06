package com.zhoumin.utils;


import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils() {

    }

    public static <V> V copyBean(Object sourse, Class<V> clazz) {
        //创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            //进行拷贝
            BeanUtils.copyProperties(sourse, result);
            //返回结果
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <k,V> List<V> copyBeanList(List<k> list, Class<V> clazz) {
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }

}
