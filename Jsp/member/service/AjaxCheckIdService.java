package com.member.service;

import com.common.Appservice;
import com.common.ServiceForward;
import com.member.ds.MemberDs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.regex.Pattern;

import static com.common.Constants.BASIC_VIEW_PATH;

public class AjaxCheckIdService implements Appservice {
    @Override
    public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String loginId = request.getParameter("loginId");

        if(loginId == null || loginId.equals("") || !Pattern.matches("^[a-z0-9]{4,15}$", loginId)) {
            return null;
        }

        MemberDs ds = new MemberDs();
        int count = ds.selectAccountCountByLoginId(loginId);

        request.setAttribute("result", count == 0 ? true : false);

        ServiceForward forward = ServiceForward.builder()
                .path(BASIC_VIEW_PATH + "ajax/result.jsp")
                .build();
        return forward;
    }
}
