package com.lmbx.file.core.utils;

/**
 * Description:
 * 
 * <pre>
 * 异常转化
 * </pre>
 * 
 * @author javahuang
 * @since 17/9/19 下午6:59
 */
public class Exceptions {

    /**
     * 将 CheckedException 转换为 UncheckedException
     *
     * @param e CheckedException
     * @return UncheckedException
     */
    public static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else {
            return new RuntimeException(e);
        }
    }
}
