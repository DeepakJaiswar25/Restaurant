package com.deepak.Restaurant.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.deepak.Restaurant.entity.orders;

public class orderRowMapper implements RowMapper<orders> {

	@Override
	public orders mapRow(ResultSet rs, int rowNum) throws SQLException {
		orders order =new orders();
		order.setId(rs.getInt("ordernumber"));
		order.setName(rs.getString("items"));
		order.setComment(rs.getString("foodcomments"));
		order.setQuantity(rs.getInt("quantity"));
		order.setStatus(rs.getString("status"));
		order.setPriority(rs.getInt("priority"));
		order.setTableNumber(rs.getString("tableNumber"));
		return order;
	}

}
