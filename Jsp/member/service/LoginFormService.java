package com.member.service;

import com.common.Appservice;
import com.common.ServiceForward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.common.Constants.BASIC_VIEW_PATH;

public class LoginFormService implements Appservice {
    @Override
    public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {



        ServiceForward forward = ServiceForward.builder()
                .path(BASIC_VIEW_PATH + "member/login.jsp")
                .build();
        return forward;
    }
}
