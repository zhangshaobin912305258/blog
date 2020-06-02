package com.zhang.blog.util;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import java.util.ArrayList;


import javax.crypto.Cipher;

/**
 * 类描述：
 * 创建时间：2020/6/2 2:18 下午
 * 创建人：zhang
 */
@Slf4j
public class RSA2Utils {

    private static final String KEY_ALGORITHM = "RSA";
    private static final int KEY_SIZE = 2048;//设置长度
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    public static final String RSA_TYPE = "RSA/ECB/PKCS1Padding";

    private final static String PUBLIC_KEY_NAME = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqOIEsDftWE06tjLIq+HhBLArNu/kayalM44+yFWRRIptKTGCeQ0ieKvvufUT+F+4FVefkXC/vzlPjJnGeLIzcQbgnp/SpLi4AVE6fqvy1zTLpb9zEqVkk1JtZ9aS92XV2mhlWERhgbOIXZNP7xZXZuhteveDpN0nbfdLqED0qL0EBExi3fzIizgDrr3nv8WtmLvLz5wXW/VKnE9B1MhQVVkOhisynyM83ahLoalMVsAO2yJ9/ld7R+Qk6w5WLAD8T5HgdSQgKywRmOB94raKPSFX/B0CmtZF6OXidLMi9No6k29mFuwcjeRdWewqOoQrn5Gj1yupfjY1A8k+ru7/0QIDAQAB";
    private final static String PRIVATE_KEY_NAME = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCo4gSwN+1YTTq2Msir4eEEsCs27+RrJqUzjj7IVZFEim0pMYJ5DSJ4q++59RP4X7gVV5+RcL+/OU+MmcZ4sjNxBuCen9KkuLgBUTp+q/LXNMulv3MSpWSTUm1n1pL3ZdXaaGVYRGGBs4hdk0/vFldm6G1694Ok3Sdt90uoQPSovQQETGLd/MiLOAOuvee/xa2Yu8vPnBdb9UqcT0HUyFBVWQ6GKzKfIzzdqEuhqUxWwA7bIn3+V3tH5CTrDlYsAPxPkeB1JCArLBGY4H3itoo9IVf8HQKa1kXo5eJ0syL02jqTb2YW7ByN5F1Z7Co6hCufkaPXK6l+NjUDyT6u7v/RAgMBAAECggEBAKhBDeS2w4s+3iiZrd242+M81MEV/Z9XUC1uIVScE2+S7jClN9T8ZgSNX/gHmE7Spmb09nADQbX1FjEBH4AuIhwQewbuOgGMWx5M3BdJnlYULnRCqhXAm9Q+wes7Fw7N8WJ4scdf0TlUMZ7w3ilYO9m15SUmpYxCHT9sqn7FwDq+/4YoeTMAyrhvTFmdY0sUt0DXLlnn2kotbVXG7vyA1Rj7NxzfamO/mo9X9ZpYxFpo8yZU7zuXEIdb5regx7KSr4CZUKyK+8R8A/WVgwhtTm6gDnrbcF7GnBk1UIRIL7EfUmj9SExcauvSL1BkJ1Xp2x8y/mAbWqpjHv4NAJrdJiUCgYEA0bxaZUmH37t9d1Twqd5GHnNK2AjeJCWGDxl8gEio2ng8GEpO+p41s24BJ+Z84APcQolSi0zhxa7KsD8/DY7ntJanSLyB1+k+uta0qT97zFYaxP6dDb/RKz9wQ3kxV5JrcJDUDrEZzvcts3DlSbCCUeCUuj64FIcJNRMDU7EWD3MCgYEAziK6qrVRHBsxKSe6+aI/gwRfIKpDtv09OYe9J0dARGdZ3fO5N4RNPvoG978dy71IfFWbd0/RutOjl7eL7VoeUW3YhEpU7IRgocDCBJTMtJhCUZiDLa/ADT3nHPyEDIz7Kbkl66bnXUxjAV127y3WKz9LlrOqr3+euoOzY7+bGqsCgYAJ/2R/t4WWkKVmbtIaBtJo2S4UJjgQmQlO5vQcWVpTL7ANqF0jbS86ImBDuqTxOL3jHhClO0D332EiDaG/z7QT06qduP6ngCLRXTK3bqNEFkaoZUqvQRmDaj67uKc1eXFNqFmzaZXFW9r91r/XR2au6HmudxbnQ5iYwxNBhgt7KQKBgQCyr7Q0Slx3CrNAw6n2v0hi4CU+AZoVQ8wQ1LOJGuCA6z4hgiYqE8uKLd5gYBEKDZiJ/wN3F3/sfY0v9PGc3COv3pJziqO3KsFBNgB5TCtu2RWT+aqsrc7DK8ftfo9Q+dy2vEI81j90xsSD6Gsyxf8DGikCB9iLrQsBDy0v6GYhZQKBgEHZOUyD8nn7b15Ef+qvisqBjJOmxI5Ti6r7Unjyfk62VfSYDaLpmXd0l4etzoBx7byOt8E2c6zX/AyfIp0plVu8bYD197KA7XrlO2tuqFz8adLS280OfzbRDP4edawUwHE1/WOp9qfkPb8O1q7J96JbQlnmqHfCoIeyFm5022aP";
    private final static String SERVICE_PUBLIC_KEY_NAME = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqOIEsDftWE06tjLIq+HhBLArNu/kayalM44+yFWRRIptKTGCeQ0ieKvvufUT+F+4FVefkXC/vzlPjJnGeLIzcQbgnp/SpLi4AVE6fqvy1zTLpb9zEqVkk1JtZ9aS92XV2mhlWERhgbOIXZNP7xZXZuhteveDpN0nbfdLqED0qL0EBExi3fzIizgDrr3nv8WtmLvLz5wXW/VKnE9B1MhQVVkOhisynyM83ahLoalMVsAO2yJ9/ld7R+Qk6w5WLAD8T5HgdSQgKywRmOB94raKPSFX/B0CmtZF6OXidLMi9No6k29mFuwcjeRdWewqOoQrn5Gj1yupfjY1A8k+ru7/0QIDAQAB";

    /**
     * 生成公、私钥
     * 根据需要返回String或byte[]类型
     * @return
     */
    public static ArrayList<String> createRSAKeys(){
        ArrayList<String> array = new ArrayList<>();
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGenerator.initialize(KEY_SIZE, new SecureRandom());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            //获取公、私钥值
            String publicKeyValue = Base64.encode(publicKey.getEncoded());
            String privateKeyValue = Base64.encode(privateKey.getEncoded());
            //存入
            array.add(publicKeyValue);
            array.add(privateKeyValue);
            log.info("公钥:\n{}",publicKeyValue);
            log.info("私钥:\n{}",privateKeyValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }


    //获取本地RSA公钥
    public static PublicKey getPublicKey() {
        try {
            return  getPublicKey(PUBLIC_KEY_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取本地RSA公钥
    public static String getPublicKeyString() {
        try {
            return  PUBLIC_KEY_NAME;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取服务器RSA公钥
    public static PublicKey getServicePublicKey() {
        try {
            return  getPublicKey(SERVICE_PUBLIC_KEY_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //获取RSA公钥 根据钥匙字段
    public static PublicKey getPublicKey(String key) {
        try {
            byte[] byteKey = Base64.decode(key);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            return keyFactory.generatePublic(x509EncodedKeySpec);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //获取RSA私钥   根据钥匙字段
    private static PrivateKey getPrivateKey(String key) {
        try {
            byte[] byteKey = Base64.decode(key);
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(byteKey);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

            return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }




    //本地RSA私钥 签名
    public static String sign(String requestData){
        String signature = null;
        byte[] signed = null;
        try {
//            Log.e("=0== 签名前 >>>",requestData);
            PrivateKey privateKey = getPrivateKey(PRIVATE_KEY_NAME);
            Signature Sign = Signature.getInstance(SIGNATURE_ALGORITHM);
            Sign.initSign(privateKey);
            Sign.update(requestData.getBytes());
            signed = Sign.sign();
            signature = Base64.encode(signed);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signature;
    }


    //公钥验证签名   base64签名 signature   签名内容requestData
    public static boolean verifySign(String requestData, String signature){
        boolean verifySignSuccess = false;
        try {
            PublicKey publicKey = getServicePublicKey();
            Signature verifySign = Signature.getInstance(SIGNATURE_ALGORITHM);
            verifySign.initVerify(publicKey);
            verifySign.update(requestData.getBytes());

            verifySignSuccess = verifySign.verify(Base64.decode(signature));
            System.out.println(" >>> "+verifySignSuccess);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return verifySignSuccess;
    }


    public static String encrypt(String clearText) {
        String encryptedBase64 = "";
        try {
            Key key = getServicePublicKey();
            final Cipher cipher = Cipher.getInstance(RSA_TYPE);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            //
            byte[] encryptedBytes = cipher.doFinal(clearText.getBytes("UTF-8"));
            encryptedBase64 = Base64.encode(encryptedBytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedBase64;
    }

    public static String decrypt(String encryptedBase64) {
        String decryptedString = "";
        try {
            Key key =  getPrivateKey(PRIVATE_KEY_NAME);
            final Cipher cipher = Cipher.getInstance(RSA_TYPE);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptedBytes = Base64.decode(encryptedBase64);
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
            decryptedString = new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedString;
    }

    /*public static void main(String[] args) {
        String content = encrypt("{123fgfblkdfsdnfnvkcbvbcmnb jsbd召开的基本功解放碑}");
        String decrypt = decrypt(content);
        System.out.println(content);
        System.out.println(decrypt);
        //createRSAKeys();
    }*/
}
