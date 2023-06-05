package com.member.service;

import com.common.Appservice;
import com.common.BCrypt;
import com.common.ServiceForward;
import com.dto.MemberDTO;
import com.member.ds.MemberDs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.common.Constants.BASIC_VIEW_PATH;
import static com.common.Validator.isStringEmpty;

public class LoginProcessService implements Appservice {
    @Override
    public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");

        if(isStringEmpty(loginId) || isStringEmpty(password)) {
            return null;
        }

        MemberDs ds = new MemberDs();
        MemberDTO memberDTO = ds.selectMemberInfoByLoginId(loginId);
        if(memberDTO == null) {
            return null;
        }

        boolean isSame = BCrypt.checkpw(password, memberDTO.getPassword());
        if(!isSame) {
            return null;
        }

        HttpSession session = request.getSession();
        session.setAttribute("mi", memberDTO);

        ServiceForward forward = ServiceForward.builder()
                .path(BASIC_VIEW_PATH + "main.jsp")
                .build();
        return forward;
    }
}
