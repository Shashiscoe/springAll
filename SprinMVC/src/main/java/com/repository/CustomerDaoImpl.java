
package com.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.entity.CustomerErrorResponse;
import com.exception.CustomerNotFoundException;

@Configuration
@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List getJdbcTemplateData() {
		List result = null;
		try {
			result = jdbcTemplate.queryForList("select *from customer");
			
			//System.out.println("Got Result" + result);
		} catch (Exception exc) {
			throw new CustomerNotFoundException(exc.getMessage());
		}
		return result;

	}

}
