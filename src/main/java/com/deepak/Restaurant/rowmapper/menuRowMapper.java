package com.deepak.Restaurant.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.deepak.Restaurant.entity.foodmenu;

public class menuRowMapper  implements RowMapper<foodmenu>{

	@Override
	public foodmenu mapRow(ResultSet rs, int rowNum) throws SQLException {
		foodmenu menu =new foodmenu();
		menu.setName(rs.getString("food_name"));
		menu.setPrice(rs.getString("price"));
		menu.setDesc(rs.getString("description"));
		menu.setImageUrl(rs.getString("imageurl"));
		menu.setCategory(rs.getString("category"));
		return menu;
	}

}
