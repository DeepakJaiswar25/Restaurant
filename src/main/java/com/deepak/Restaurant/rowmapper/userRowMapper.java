package com.deepak.Restaurant.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.deepak.Restaurant.entity.userLogin;

public class userRowMapper implements RowMapper<userLogin> {

	@Override
	public userLogin mapRow(ResultSet rs, int rowNum) throws SQLException {
		userLogin user =new userLogin();
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setRole(rs.getString("role"));
		user.setEmployeeID(rs.getString("employeeid"));
		return user;
	}

}
