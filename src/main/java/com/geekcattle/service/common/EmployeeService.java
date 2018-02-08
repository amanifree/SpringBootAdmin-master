/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.service.common;

import com.geekcattle.mapper.basic.EmployeeMapper;
import com.geekcattle.model.basic.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * author geekcattle
 * date 2016/10/21 0021 下午 15:43
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper  employeeMapper;

    public List<Employee> getPageList(Employee billno) {
        //PageHelper.offsetPage(billno.getOffset(), billno.getLimit(), CamelCaseUtil.toUnderlineName(billno.getSort())+" "+billno.getOrder());
        return employeeMapper.selectAll();
    }

    public Integer getCount(Example example){
        return employeeMapper.selectCountByExample(example);
    }

    public Employee getById(String id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    public List<Employee> selectByDepart(String depname) {
        return employeeMapper.selectByDepart(depname);
    }

    public void deleteById(String id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    public void insert(Employee billno){
        employeeMapper.insert(billno);
    }

    public void save(Employee billno) {
        if (billno.getUid() != null) {
            employeeMapper.updateByPrimaryKey(billno);
        } else {
            employeeMapper.insert(billno);
        }
    }

    public void updateExample(Employee billno, Example example){
        employeeMapper.updateByExampleSelective(billno, example);
    }

}
