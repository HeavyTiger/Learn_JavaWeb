package com.heavytiger.utils;

/**
 * 单例模式
 * 加锁保证了线程安全
 * 进制转换，实现了将byte[]转换为16进制字符串。
 */
public class HexKit {

    private static final String HEX = "0123456789abcdef";

    private static volatile HexKit instance;

    private HexKit() {}

    public static HexKit getInstance(){
        if(instance == null) {
            synchronized (HexKit.class) {
                if(instance == null) {
                    instance = new HexKit();
                }
            }
        }
        return instance;
    }

    /**
     * 将字节数组转换成16进制字符串
     * @param bytes 需要转换成的字节数组
     * @return String 转换成字节的16进制字符串
     */
    public String byte2hex(byte[] bytes) {
        // 因为将4位转换为1个char型，即1字节替换为0.5字节，因此容量较bytes翻倍
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for(byte b : bytes) {
            // 取出字节的高四位，与0x0f相与，得到0-15转换为HEX中相应位置的字符，并添加到StringBuilder中
            sb.append(HEX.charAt((b >> 4) & 0x0f));
            // 取出低四位，相与后存入
            sb.append(HEX.charAt(b & 0x0f));
        }
        return sb.toString();
    }

    /**
     * 将16进制字符串转换成字节数组
     * @param hex 需要转换成字节的16进制字符串
     * @return byte[] 转换成的字节数组
     */
    public byte[] hex2byte(String hex) {
        int len = (hex.length() / 2);
        byte[] bytes = new byte[len];
        char[] hexChars = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            bytes[i] = (byte) (HEX.indexOf(hexChars[pos]) << 4
                    | HEX.indexOf(hexChars[pos + 1]));
        }
        return bytes;
    }
}
