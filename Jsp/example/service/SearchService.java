package com.example.service;

import com.common.Appservice;
import com.common.ServiceForward;
import com.example.ds.exampleDs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.regex.Pattern;

import static com.common.Constants.BASIC_VIEW_PATH;

public class SearchService implements Appservice {
    @Override
    public ServiceForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String input = request.getParameter("myNameId");

        if(input == null || input.equals("") || !Pattern.matches("^[0-9]{1,9}$", input)) {
            return null;
        }

        int id = Integer.parseInt(input);

        exampleDs ds = new exampleDs();
        String name = ds.selectNameById(id);
        if(name == null) {
            name = "검색 결과가 존재하지 않습니다.";
        }

        request.setAttribute("result", name);

        return ServiceForward.builder()
                .path(BASIC_VIEW_PATH + "search.jsp")
                .build();
    }
}
