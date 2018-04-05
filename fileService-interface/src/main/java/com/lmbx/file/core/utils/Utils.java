package com.lmbx.file.core.utils;

import java.util.UUID;

/**
 * Description:
 * 
 * <pre>
 * 系统常用的一些工具类
 * </pre>
 * 
 * @author javahuang
 * @since 17/9/19 下午6:59
 */
public class Utils {

    /**
     * @return 返回UUID作为主键
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
