package com.lmbx.file.core.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * Description:
 * 
 * <pre>
 * </pre>
 * 
 * @author javahuang
 * @since 17/9/19 下午6:59
 */
public class CryptoUtil {

    private static final String Algorithm  = "DES";   // 定义 加密算法,可用
    private static final String MD5_DIGEST = "MD5";

    // DES,DESede,Blowfish
    private static final String Chartset   = "UTF-8";
    public static String        Key        = "UIT001";

    // keybyte为加密密钥，长度为24字节
    // src为被加密的数据缓冲区（源）
    public static byte[] encryptMode(byte[] keybyte, byte[] src) {
        try {
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            // 加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    // keybyte为加密密钥，长度为24字节
    // src为加密后的缓冲区
    public static byte[] decryptMode(byte[] keybyte, byte[] src) {
        try {
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);

            // 解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    // 转换成十六进制字符串
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";

        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) hs = hs + "0" + stmp;
            else hs = hs + stmp;
            if (n < b.length - 1) hs = hs + ":";
        }
        return hs.toUpperCase();
    }

    public static String formatKey(String key) throws NoSuchAlgorithmException {
        return md5Encrypt(key).substring(0, 8);
    }

    public static String encryptString(String key, String txt) throws Exception {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        return Base64.encodeBase64String(encryptMode(formatKey(key).getBytes(Chartset), txt.getBytes(Chartset)));
    }

    public static byte[] encrypt(String key, String txt) throws Exception {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        return encryptMode(formatKey(key).getBytes(Chartset), txt.getBytes(Chartset));
    }

    public static byte[] encrypt(String key, byte[] bytes) throws Exception {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        return encryptMode(formatKey(key).getBytes(Chartset), bytes);
    }

    public static byte[] decrypt(String key, byte[] bytes) throws Exception {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        return decryptMode(formatKey(key).getBytes(Chartset), bytes);
    }

    public static String decryptString(String key, String txt) throws Exception {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        return new String(decryptMode(formatKey(key).getBytes(Chartset), Base64.decodeBase64(txt)));
    }

    public static byte[] decrypt(String key, String txt) throws Exception {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        return decryptMode(formatKey(key).getBytes(Chartset), Base64.decodeBase64(txt));
    }

    public static String md5Encrypt(String txt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(MD5_DIGEST);
        md.update(txt.getBytes());
        return Base64.encodeBase64String(md.digest());
    }

    private static String convertByteToHex(byte[] byteData) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

}
