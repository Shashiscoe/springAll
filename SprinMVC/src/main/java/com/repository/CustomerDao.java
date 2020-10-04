
package com.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDao {
	public List getJdbcTemplateData();

}
