package com.husama.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by husama on 16-9-20.
 */
public class MD5Utils {

    private static final String SAULT = "苟利国家生死以";

    public static String toMD5(String s, String sault){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
        byte[] inputByteArray = (s+SAULT+sault).getBytes();
        md5.update(inputByteArray);
        byte[] resultByteArray = md5.digest();
        return ByteArrayToHex(resultByteArray);
    }

    private static String ByteArrayToHex(byte[] byteArray){
        // 首先初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };
        // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
        char[] resultCharArray =new char[byteArray.length * 2];
        // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b& 0xf];
        }
        // 字符数组组合成字符串返回
        return new String(resultCharArray);
    }
}
