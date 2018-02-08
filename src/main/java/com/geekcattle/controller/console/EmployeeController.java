/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.controller.console;

import com.geekcattle.model.basic.Employee;
import com.geekcattle.model.member.Member;
import com.geekcattle.service.common.EmployeeService;
import com.geekcattle.util.ReturnUtil;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * author geekcattle
 * date 2016/10/21 0021 下午 15:58
 */
@Controller
@RequestMapping("/console/employee")
public class EmployeeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/index", method = {RequestMethod.GET})
    public String index(Model model) {
        return "console/member/index";
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap list(Employee employee) {
        ModelMap map = new ModelMap();
        List<Employee> Lists = employeeService.getPageList(employee);
        /*map.put("pageInfo", new PageInfo<Employee>(Lists));
        map.put("queryParam", employee);*/
        map.put("employee",employee);
        return ReturnUtil.Success("加载成功", map, null);
    }

    @RequestMapping(value = "/combobox", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public ModelMap combobox(String depart) {
        ModelMap map = new ModelMap();
        depart="仓储部";
        List<Employee> emplist = employeeService.selectByDepart(depart);
        map.put("emplist",emplist);
        return ReturnUtil.Success("加载成功", map, null);
    }
}
