package com.example.ds;

import com.AppDao;
import com.common.JdbcUtil;

import java.sql.Connection;

import static com.common.JdbcUtil.*;

public class exampleDs {
    private Connection conn;

    private AppDao setDao() {
        AppDao dao = AppDao.getInstance();
        this.conn = getConnection();
        dao.setConnection(this.conn);
        return dao;
    }

    public void insertName(String name) {
        AppDao dao = setDao();
        boolean isSuccess = dao.insertName(name);
        if(isSuccess) {
            commit(this.conn);
        }
        else {
            rollback(this.conn);
        }
        close(conn);
    }

    public String selectNameById(int id) {
        AppDao dao = setDao();
        String name = dao.selectNameById(id);
        close(conn);
        return name;
    }
}
