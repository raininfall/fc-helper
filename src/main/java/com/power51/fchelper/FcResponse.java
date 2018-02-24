package com.power51.fchelper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author RainInFall
 */
public class FcResponse {
    private int statusCode = 200;
    private byte[] body;
    private boolean isBase64Encoded = false;
    private Map<String, List<String>> headers = new LinkedHashMap<>();
    private final OutputStream outputStream;

    public FcResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }

    public String getBody() {
        if (isBase64Encoded) {
            new String(Base64.getEncoder().encode(body));
        }
        return new String(body);
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public boolean isBase64Encoded() {
        return isBase64Encoded;
    }

    public void setBase64Encoded(final boolean base64Encoded) {
        isBase64Encoded = base64Encoded;
    }

    public Map<String, String> getHeaders() {
        return headers.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> String.join("; ", entry.getValue())
                ));
    }

    public void setHeader(final String key, final String value) {
        headers.computeIfAbsent(key, keyReserved -> new ArrayList<String>())
                .add(value);
    }

    @JSONField(serialize = false)
    public List<String> getHeader(final String key) {
        return headers.get(key);
    }

    public void clearHeader(final String key) {
        headers.remove(key);
    }

    public void sendJson(final Object data) throws IOException {
        body = JSON.toJSONString(data).getBytes();
        outputStream.write(JSON.toJSONString(this).getBytes());
    }

    public void sendJson(int statusCode, final Object data) throws IOException {
        setStatusCode(statusCode);
        sendJson(data);
    }
}