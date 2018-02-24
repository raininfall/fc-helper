package com.power51.fchelper;

import com.alibaba.fastjson.JSON;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FcHelper {
    final private FcRequest request;
    final private FcResponse response;


    public FcHelper(final InputStream inputStream, final OutputStream outputStream) throws IOException {
        final byte[] raw = IOUtils.readFully(inputStream, -1, true);
        final ApiGateInput input = JSON.parseObject(raw, ApiGateInput.class);
        request = new FcRequest(input);
        response = new FcResponse(outputStream);
    }

    public FcRequest getRequest() {
        return request;
    }

    public FcResponse getResponse() {
        return response;
    }
}