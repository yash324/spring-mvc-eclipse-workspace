package com.flp.ems.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.sql.DataSource;
import com.flp.ems.domain.*;
import com.flp.ems.util.ServiceLocator;
import com.flp.ems.util.ServiceLocatorException;
import com.flp.ems.dao.JDBCDaoException;

public class JDBCEmployeeDAO {
	DataSource datasource = null;
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public JDBCEmployeeDAO() {
		datasource = ServiceLocator.getDataSource("jdbc/EMSDataSource");
		if (datasource == null)
			throw new ServiceLocatorException("Container Service not available");
	}
	public void create(Employee emp) {
		Connection connection = null;
		String insertQuery = "INSERT INTO employee (name, email, phone, dob, doj, addr, deptid, projid, roleid) VALUES (? , ? , ? , ? , ? , ? , ? , ? , ?)";
		
		try {
			try {
				connection = datasource.getConnection();
				connection.setAutoCommit(false);
				PreparedStatement insertStat = connection.prepareStatement(insertQuery);
				insertStat.setString(1, emp.getName());
				insertStat.setString(2, emp.getEmail());
				insertStat.setLong(3, emp.getPhone());
				insertStat.setDate(4, new java.sql.Date(df.parse(emp.getDob()).getTime()));
				insertStat.setDate(5, new java.sql.Date(df.parse(emp.getDoj()).getTime()));
				insertStat.setString(6, emp.getAddress());
				insertStat.setInt(7, emp.getDeptid());
				insertStat.setInt(8, emp.getProjid());
				insertStat.setInt(9, emp.getRoleid());
				insertStat.execute();
				connection.commit();
			} catch (SQLException e) {
				if (connection != null)
					connection.rollback();
				throw e;
			} catch (ParseException e) {
				e.printStackTrace();
			} finally {
				if (connection != null)
					connection.close();
			}
		} catch (SQLException e) {
			throw new JDBCDaoException("SQL error while excecuting this query: " + insertQuery, e);
		}
	}

	public ArrayList<Employee> findAll() {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		Connection connection = null;
		String selectQuery = "select * from employee";
		ResultSet result = null;
		try {
			try {
				connection = datasource.getConnection();
				PreparedStatement selectStat = connection.prepareStatement(selectQuery);
				result = selectStat.executeQuery();
				while (result.next()) {
					Employee emp = new Employee();
					emp.setEmpid(result.getInt(1));
					emp.setName(result.getString(2));
					emp.setEmail(result.getString(3));
					emp.setPhone(result.getLong(4));
					emp.setDob(result.getDate(5));
					emp.setDoj(result.getDate(6));
					emp.setAddress(result.getString(7));
					emp.setDeptid(result.getInt(8));
					emp.setProjid(result.getInt(9));
					emp.setRoleid(result.getInt(10));
					empList.add(emp);
				}
			} finally {
				if (connection != null)
					connection.close();
			}
		} catch (SQLException e) {
			throw new JDBCDaoException("SQL error while excecuting query: " + selectQuery, e);
		}
		return empList;
	}

	public void update(Employee emp) {
		String updateQuery = "UPDATE employee SET name=?, email=?, phone=?, dob=?, doj=?, addr=?, deptid=?, projid=?, roleid=? WHERE empid = ?";
		Connection connection = null;
		try {
			try {
				connection = datasource.getConnection();
				connection.setAutoCommit(false);
				PreparedStatement updateStat = connection.prepareStatement(updateQuery);
				updateStat.setString(1, emp.getName());
				updateStat.setString(2, emp.getEmail());
				updateStat.setLong(3, emp.getPhone());
				updateStat.setDate(4, new java.sql.Date(df.parse(emp.getDob()).getTime()));
				updateStat.setDate(5, new java.sql.Date(df.parse(emp.getDoj()).getTime()));
				updateStat.setString(6, emp.getAddress());
				updateStat.setInt(7, emp.getDeptid());
				updateStat.setInt(8, emp.getProjid());
				updateStat.setInt(9, emp.getRoleid());
				updateStat.setInt(10, emp.getEmpid());
				updateStat.execute();
				connection.commit();
			} catch (SQLException e) {
				if (connection != null)
					connection.rollback();
				throw e;
			} catch (ParseException e) {
				e.printStackTrace();
			} finally {
				if (connection != null)
					connection.close();
			}
		} catch (SQLException e) {
			throw new JDBCDaoException("SQL error while excecuting query: " + updateQuery, e);
		}
	}

	public void delete(String[] ids) {
		Connection connection = null;
		String deleteQuery = "DELETE from employee WHERE empid = ?";
		try {
			try {
				connection = datasource.getConnection();
				connection.setAutoCommit(false);
				PreparedStatement deleteStat = connection.prepareStatement(deleteQuery);
				for (String id : ids) {
					deleteStat.setInt(1, Integer.parseInt(id));
					deleteStat.execute();
				}
				connection.commit();
			} catch (SQLException e) {
				if (connection != null)
					connection.rollback();

				throw e;
			} finally {
				if (connection != null)
					connection.close();
			}
		} catch (SQLException e) {
			throw new JDBCDaoException("SQL error while excecuting query: " + deleteQuery, e);
		}
	}

	public Employee findById(int id) {
		String selectQuery = "select * from employee where empid = ?";
		Employee emp = new Employee();
		Connection connection = null;
		try {
			try {
					connection = datasource.getConnection();
					connection.setAutoCommit(true);
					PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
					selectStatement.setInt(1, id);
					ResultSet result = selectStatement.executeQuery();
					if (result.next()) {
						emp.setEmpid(result.getInt(1));
						emp.setName(result.getString(2));
						emp.setEmail(result.getString(3));
						emp.setPhone(result.getLong(4));
						emp.setDob(result.getDate(5));
						emp.setDoj(result.getDate(6));
						emp.setAddress(result.getString(7));
						emp.setDeptid(result.getInt(8));
						emp.setProjid(result.getInt(9));
						emp.setRoleid(result.getInt(10));
					}
			} finally {
				if (connection != null)
					connection.close();
			}
		} catch (SQLException e) {
			throw new JDBCDaoException("SQL error while excecuting query: " + selectQuery, e);
		}
		return emp;
	}
}
