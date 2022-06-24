package com.instahyre.backend.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Value("${white.listed.client.id}")
    private String whiteListedClientId;
    private final String AUTENTICATION_HEADER = "X-Client-Id";

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (whiteListedClientId.equals(request.getHeader(AUTENTICATION_HEADER)))
            return true;
        else {
            log.error("Required authentication header is not present , please pass the value of " + AUTENTICATION_HEADER);
            response.addHeader("error-msg","Mandatory authentication error is not present, send the valid value of "+AUTENTICATION_HEADER+"  in header");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return false;
        }
    }
}