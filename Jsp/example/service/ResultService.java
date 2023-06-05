package com.example.service;

import com.common.Appservice;
import com.common.ServiceForward;
import com.example.ds.exampleDs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.common.Constants.BASIC_VIEW_PATH;

public class ResultService implements Appservice {
    @Override
    public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("Myname");
//        String age = request.getParameter("Myage");
        request.setAttribute("name", name);
//        request.setAttribute("age", age);

        exampleDs ds = new exampleDs();
        ds.insertName(name);

        ServiceForward forward = ServiceForward.builder()
                .path(BASIC_VIEW_PATH + "result.jsp")
                .build();
        return forward;
    }
}
