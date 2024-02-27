package com.primeiro.javafx.model.dao;

import com.primeiro.javafx.db.DB;
import com.primeiro.javafx.model.dao.impl.DepartmentDaoJDBC;
import com.primeiro.javafx.model.dao.impl.SellerDaoJDBC;



public class DaoFactory {

	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(DB.getConnection());
	}
	
	public static DepartmentDao createDepartmentDao() {
		return new DepartmentDaoJDBC(DB.getConnection());
	}
}
