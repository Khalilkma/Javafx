package com.primeiro.javafx.model.services;

import com.primeiro.javafx.model.dao.DaoFactory;
import com.primeiro.javafx.model.dao.DepartmentDao;
import com.primeiro.javafx.model.entities.Department;

import java.util.List;

public class DepartmentService {

    private DepartmentDao dao = DaoFactory.createDepartmentDao();

    public List<Department> findAll() {
        return dao.findAll();
    }
}


