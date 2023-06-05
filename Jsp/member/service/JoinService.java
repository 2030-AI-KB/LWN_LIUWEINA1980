package com.member.service;

import com.common.*;
import com.dto.MemberDTO;
import com.member.ds.MemberDs;

import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.regex.Pattern;

import static com.common.Constants.BASIC_VIEW_PATH;
import static com.common.Validator.isValidated;

public class JoinService implements Appservice {
    @Override
    public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");
        String passwordCheck = request.getParameter("passwordCheck");
        String name = request.getParameter("name");
        String mobileNo = request.getParameter("mobileNo");
        String birthday = request.getParameter("birthday");
        String email = request.getParameter("email");
        String zipcode = request.getParameter("zipcode");
        String address = request.getParameter("address");
        String addressDetail = request.getParameter("addressDetail");

        if (!isValidated(loginId, "^[a-z0-9]{4,15}$", true)
                || !isValidated(password, "^[a-zA-Z0-9!@#$]{4,15}$", true)
                || !isValidated(name, "^[가-힣]{2,10}$", true)
                || !isValidated(mobileNo, "^[0-9]{10,12}$", true)
                || !isValidated(birthday, "^[0-9]{8,8}$", false)
                || !isValidated(email, "^.{1,50}$", true)
                || !isValidated(zipcode, "^[0-9]{5,5}$", false)
                || !isValidated(address, "^.{1,100}$", false)
                || !isValidated(addressDetail, "^.{1,100}$", false)) {
            return null;
        }

        if (!password.equals(passwordCheck)) {
            return null;
        }

//        AES256 aes256 = new AES256();
//        password = aes256.encrypt(password);

        password = BCrypt.hashpw(password, BCrypt.gensalt(12));

        MemberDTO memberDTO = MemberDTO.builder()
                .loginId(loginId)
                .password(password)
                .name(name)
                .mobileNo(mobileNo)
                .email(email)
                .zipcode(zipcode)
                .address(address)
                .addressDetail(addressDetail)
                .build();

        if(birthday != null && !birthday.equals("")) {
            birthday = birthday.substring(0,4)
                    +"-"+birthday.substring(4,6)
                    +"-"+birthday.substring(6);
            memberDTO.setBirthday(LocalDate.parse(birthday));
        }

        MemberDs ds = new MemberDs();
        boolean isSuccess = ds.insertMemberInfo(memberDTO);
        if(!isSuccess) {
            return null;
        }

        request.setAttribute("name", memberDTO.getName());

        ServiceForward forward = ServiceForward.builder()
                .path(BASIC_VIEW_PATH + "member/joinresult.jsp")
                .build();
        return forward;
    }

}
