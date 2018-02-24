package com.power51.fchelper;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class FcRequest {
    final ApiGateInput input;

    public FcRequest(final ApiGateInput input) {
        this.input = input;
    }

    public String getMethod() {
        return input.getHttpMethod();
    }

    public List<String> getHeader(final String key) {
        return Arrays.stream(input.getHeaders().get(key).split(";"))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    public String getQuery(final String key) {
        return input.getQueryParameters().get(key);
    }

    public String getPathParameter(final String key) {
        return input.getPathParameters().get(key);
    }

    public String getPath() {
        return input.getPath();
    }

    public byte[] getBody() throws UnsupportedEncodingException{
        if (input.isBase64Encoded()) {
            return Base64.getDecoder().decode(input.getBody().getBytes("UTF-8"));
        }
        return input.getBody().getBytes("UTF-8");
    }
}