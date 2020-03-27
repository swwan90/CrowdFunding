package com.atguigu.crowd.util;


import com.atguigu.crowd.constant.CrowdConstant;
import com.sun.org.glassfish.gmbal.Description;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author swwan
 * @create 2020-03-27 11:39
 */
public class CrowdUtil {

    public static boolean judgeRequestType(HttpServletRequest request) {
        // 1. 获取请求消息头信息
        String acceptHeader = request.getHeader("Accept");
        String xRequestHeader = request.getHeader("X-Requested-With");

        // 2. 判断
        return (acceptHeader != null && acceptHeader.length() > 0 && acceptHeader.contains("application/json"))
                ||
                (xRequestHeader != null && xRequestHeader.length() > 0 && xRequestHeader.equals("XMLHttpRequest"));
    }

    /**
     * @author swwan
     * @Description 对明文字符串进行MD5加密
     * @Date 2020/3/27
     * @param source 传入的明文字符串
     * @return java.lang.String 加密加过
     **/
    public static String md5(String source){
        // 1.判断 source 是否有效
        if(source == null || source.length() == 0){
            // 2.如果不是有效的字符串抛出异常
            throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
        }

        try {
            // 3.获取 MessageDigest 对象
            String algorithm = "md5";

            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);

            // 4.获取明文字符串对应的字节数组
            byte[] input = source.getBytes();

            // 5.执行加密
            byte[] output = messageDigest.digest(input);

            // 6.创建 BigInteger 对象
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, output);

            // 7.按照 16 进制将 bigInteger 的值转换为字符串
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();

            return encoded;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
}
