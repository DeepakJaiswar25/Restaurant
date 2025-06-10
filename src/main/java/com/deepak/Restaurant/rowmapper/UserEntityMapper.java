package com.deepak.Restaurant.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.deepak.Restaurant.entity.UserEntity;


public class UserEntityMapper implements RowMapper<UserEntity> {

	@Override
	public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserEntity user= new UserEntity();
		user.setEmployeeid(rs.getString("employeeid"));
		user.setName(rs.getString("name"));
		user.setRole(rs.getString("role"));
		user.setEmail(rs.getString("email"));
		return user;
	}

}
