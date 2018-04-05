/**
 * PasswordUtil.java Create on 2012-11-19
 * Copyright(c) Gener-Tech Inc.
 * ALL Rights Reserved.
 */
package com.lmbx.file.core.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 * 
 * @author javahuang
 * @since 17/9/19 下午6:59
 */
public class PasswordUtils {

    public static byte[] getBytes(String str) {
        return str.getBytes();
    }

    public static String getEncodePassWord(String passWord, byte[] salt) {
        byte[] hashPassword = Digests.sha1(getBytes(passWord), salt, 1024);
        return encodeHex(hashPassword);
    }

    public static String encodeHex(byte[] bytes) {
        return Hex.encodeHexString(bytes);
    }

    /**
     * Hex解密算法
     */
    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw Exceptions.unchecked(e);
        }
    }
}
