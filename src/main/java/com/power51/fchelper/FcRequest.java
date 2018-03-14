package com.power51.fchelper;

import com.auth0.jwk.JwkProvider;
import com.auth0.jwk.UrlJwkProvider;
import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

public class FcRequest {
    private final ApiGateInput input;
    private DecodedJWT jwt;

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

    public DecodedJWT getJWT() {
        if (null == jwt) {
            jwt = JWT.decode(getQuery("token"));
        }
        return jwt;
    }

    public Long getSub() {
        return Long.valueOf(getJWT().getSubject());
    }
}