package com.zhang.blog.config;

import cn.hutool.core.codec.Base64;
import com.zhang.blog.util.RSAUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * 类描述：
 * 创建时间：2020/6/1 8:35 下午
 * 创建人：zhang
 */
@Slf4j
public class DecryptHttpInputMessage implements HttpInputMessage {
    private HttpHeaders headers;
    private InputStream body;

    public DecryptHttpInputMessage(HttpInputMessage inputMessage, String privateKey, String charset) throws Exception {
        if (StringUtils.isEmpty(privateKey)) {
            throw new IllegalArgumentException("私钥为空");
        } else {
            this.headers = inputMessage.getHeaders();
            String content = (String) (new BufferedReader(new InputStreamReader(inputMessage.getBody()))).lines().collect(Collectors.joining(System.lineSeparator()));
            String decryptBody;
            if (content.startsWith("{")) {
                log.info("Unencrypted without decryption:{}", content);
                decryptBody = content;
            } else {
                StringBuilder json = new StringBuilder();
                content = content.replaceAll(" ", "+");
                if (!StringUtils.isEmpty(content)) {
                    String[] contents = content.split("\\|");
                    String[] var8 = contents;
                    int var9 = contents.length;

                    for (int var10 = 0; var10 < var9; ++var10) {
                        String value = var8[var10];
                        value = new String(RSAUtil.decrypt(Base64.decode(value), privateKey), charset);
                        json.append(value);
                    }
                }

                decryptBody = json.toString();
                log.info("收到的加密报文：{},解密后报文：{}", content, decryptBody);
            }

            this.body = new ByteArrayInputStream(decryptBody.getBytes());
        }
    }

    @Override
    public InputStream getBody() {
        return this.body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return this.headers;
    }
}
