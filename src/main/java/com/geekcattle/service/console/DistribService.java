/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.service.console;

import com.geekcattle.mapper.basic.EmployeeMapper;
import com.geekcattle.mapper.console.DistribMapper;
import com.geekcattle.mapper.basic.BillnoMapper;
import com.geekcattle.model.basic.Billno;
import com.geekcattle.model.basic.Employee;
import com.geekcattle.model.console.Distrib;
import com.geekcattle.util.CamelCaseUtil;
import com.geekcattle.util.DateUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;

/**
 * author geekcattle
 * date 2016/10/21 0021 下午 15:43
 */
@Service
public class DistribService {


    @Autowired
    private BillnoMapper billnoMapper;


    @Autowired
    private DistribMapper distribMapper;


/*    @Autowired
    private RoleService roleService;*/

    public List<Distrib> getPageList(Distrib distrib) {
        PageHelper.offsetPage(distrib.getOffset(), distrib.getLimit(), CamelCaseUtil.toUnderlineName(distrib.getSort())+" "+distrib.getOrder());
        return distribMapper.selectAll();
    }

    public List<Distrib> getPageListByRole(HashMap params) {
        //PageHelper.offsetPage(Distrib.getOffset(), Distrib.getLimit(), CamelCaseUtil.toUnderlineName(Distrib.getSort())+" "+Distrib.getOrder());
        return distribMapper.selectByRole(params);
    }

    public Integer getCount(Example example){
        return distribMapper.selectCountByExample(example);
    }

    public Distrib getById(String id) {
        return distribMapper.selectByPrimaryKey(id);
    }

/*    public Distrib findByUsername(String username) {
        return distribMapper.selectByUsername(username);
    }*/

    public void deleteById(String id) {
        distribMapper.deleteByPrimaryKey(id);
    }

    public void insert(Distrib distrib){
        //获取当前流水号
        String billtype = "LOOT";
        Billno billNo = billnoMapper.selectByBillType(billtype);
        String billData = billNo.getBilldate();
        String currData = DateUtil.getCurrentDate().replaceAll("-","");
        String currNo;
        String billid = billNo.getUid();
        if (currData.equals(billData)){
            currNo = billtype + currData + (billNo.getBillmaxno().toString()).substring(1);
            distrib.setDistribNum(currNo);
            distribMapper.insert(distrib);

            Integer newNo = billNo.getBillmaxno() + 1;
            billNo.setBillmaxno(newNo);
            billnoMapper.updateByPrimaryKey(billNo);
        }else{
            currNo = billtype + currData + "0001";
            distrib.setDistribNum(currNo);
            distribMapper.insert(distrib);

            Integer newNo = 10002;
            billNo.setBillmaxno(newNo);
            billNo.setBilldate(currData);
            billnoMapper.updateByPrimaryKey(billNo);
        }

    }

    public void save(Distrib distrib) {
        if (distrib.getUid() != null) {
            distribMapper.updateByPrimaryKey(distrib);
        } else {
            distribMapper.insert(distrib);
        }
    }

    public void updateExample(Distrib distrib, Example example){
        distribMapper.updateByExampleSelective(distrib, example);
    }



}
