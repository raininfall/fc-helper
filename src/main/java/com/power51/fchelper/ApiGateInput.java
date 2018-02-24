package com.power51.fchelper;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

/**
 * @author RainInFall
 */
public class ApiGateInput {
    private String path;
    private String httpMethod;
    private Map<String, String> headers;
    private Map<String, String> queryParameters;
    private Map<String, String> pathParameters;
    private String body;
    private boolean isBase64Encoded;

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(final String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(final Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getQueryParameters() {
        return queryParameters;
    }

    public void setQueryParameters(final Map<String, String> queryParameters) {
        this.queryParameters = queryParameters;
    }

    public Map<String, String> getPathParameters() {
        return pathParameters;
    }

    public void setPathParameters(final Map<String, String> pathParameters) {
        this.pathParameters = pathParameters;
    }

    public String getBody() throws UnsupportedEncodingException{
        if (isBase64Encoded) {

            body = new String(Base64.getDecoder().decode(body), "UTF-8");
            isBase64Encoded = false;
        }
        return body;
    }

    public void setBody(final String body) {
        this.body = body;
    }

    public boolean isBase64Encoded() {
        return isBase64Encoded;
    }

    public void setBase64Encoded(final boolean base64Encoded) {
        isBase64Encoded = base64Encoded;
    }
}