package com.zhang.blog.util;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import com.zhang.blog.vo.request.LoginDto;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 类描述：
 * 创建时间：2020/6/1 8:44 下午
 * 创建人：zhang
 */
public class RSAUtil {

    public static final String KEY_ALGORITHM = "RSA";
    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static final int MAX_DECRYPT_BLOCK = 256;

    public RSAUtil() {
    }

    public static byte[] encrypt(byte[] data, String publicKey) throws Exception {
        byte[] keyBytes = Base64.decode(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(1, publicK);
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        for(int i = 0; inputLen - offSet > 0; offSet = i * 117) {
            byte[] cache;
            if (inputLen - offSet > 117) {
                cache = cipher.doFinal(data, offSet, 117);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }

            out.write(cache, 0, cache.length);
            ++i;
        }

        byte[] encryptedData = out.toByteArray();
        out.close();
        return encryptedData;
    }

    public static byte[] decrypt(byte[] text, String privateKey) throws Exception {
        byte[] keyBytes = Base64.decode(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(2, privateK);
        int inputLen = text.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;

        for(int i = 0; inputLen - offSet > 0; offSet = i * 256) {
            byte[] cache;
            if (inputLen - offSet > 256) {
                cache = cipher.doFinal(text, offSet, 256);
            } else {
                cache = cipher.doFinal(text, offSet, inputLen - offSet);
            }

            out.write(cache, 0, cache.length);
            ++i;
        }

        byte[] decryptedData = out.toByteArray();
        out.close();
        return decryptedData;
    }

    public static void main(String[] args) throws Exception {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDBeKFwUbfHTYEP95lxPSUqtsbW24vH2rCFG4f1SgXXUPE7gJp9XnlfGfl6BEME+t0ecUTtGgGn97KNXvCPA9AaSMTId5WnNjYi8mDWq4zojflZOTI5q8ikpV9OR3DLZwPkuCHDqOjEWbV/2CI3W55IzGrl5GNZe289IqfsJ0Rg8QIDAQAB";
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("zhang");
        loginDto.setPassword("zhang");
        String json = JSONUtil.toJsonStr(loginDto);

        byte[] encrypt = encrypt(json.getBytes("utf-8"), publicKey);
        String result = Base64.encode(encrypt);
        System.out.println(result);
    }
}
