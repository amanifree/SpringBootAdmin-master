package com.geekcattle.mapper.basic;

import com.geekcattle.model.basic.Employee;
import com.geekcattle.util.CustomerMapper;

import java.util.List;

public interface EmployeeMapper extends CustomerMapper<Employee> {
    List<Employee> selectByDepart(String depname);
}