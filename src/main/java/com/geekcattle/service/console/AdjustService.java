/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.service.console;

import com.geekcattle.mapper.basic.BillnoMapper;
import com.geekcattle.mapper.console.AdjustMapper;
import com.geekcattle.model.basic.Billno;
import com.geekcattle.model.console.Adjust;
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
public class AdjustService {


    @Autowired
    private BillnoMapper billnoMapper;

    @Autowired
    private AdjustMapper adjustMapper;


/*    @Autowired
    private RoleService roleService;*/

    public List<Adjust> getPageList(Adjust adjust) {
        PageHelper.offsetPage(adjust.getOffset(), adjust.getLimit(), CamelCaseUtil.toUnderlineName(adjust.getSort())+" "+adjust.getOrder());
        return adjustMapper.selectAll();
    }

    public List<Adjust> getPageListByRole(HashMap params) {
        Adjust adjust =(Adjust) params.get("adjust");
        if(null != adjust) {
            PageHelper.offsetPage(adjust.getOffset(), adjust.getLimit(), CamelCaseUtil.toUnderlineName(adjust.getSort()) + " " + adjust.getOrder());
        }
        return adjustMapper.selectByRole(params);
    }

    public Integer getCount(Example example){
        return adjustMapper.selectCountByExample(example);
    }

    public Adjust getById(String id) {
        return adjustMapper.selectByPrimaryKey(id);
    }

/*    public Adjust findByUsername(String username) {
        return adjustMapper.selectByUsername(username);
    }*/

    public void deleteById(String id) {
        adjustMapper.deleteByPrimaryKey(id);
    }

    public void insert(Adjust adjust){
        //获取当前流水号
        String billtype = "LOAD";
        Billno billNo = billnoMapper.selectByBillType(billtype);
        String billData = billNo.getBilldate();
        String currData = DateUtil.getCurrentDate().replaceAll("-","");
        String currNo;
        String billid = billNo.getUid();
        if (currData.equals(billData)){
            currNo = billtype + currData + (billNo.getBillmaxno().toString()).substring(1);
            adjust.setAdjustNum(currNo);
            adjustMapper.insert(adjust);

            Integer newNo = billNo.getBillmaxno() + 1;
            billNo.setBillmaxno(newNo);
            billnoMapper.updateByPrimaryKey(billNo);
        }else{
            currNo = billtype + currData + "0001";
            adjust.setAdjustNum(currNo);
            adjustMapper.insert(adjust);

            Integer newNo = 10002;
            billNo.setBillmaxno(newNo);
            billNo.setBilldate(currData);
            billnoMapper.updateByPrimaryKey(billNo);
        }

    }

    public void save(Adjust adjust) {
        if (adjust.getUid() != null) {
            adjustMapper.updateByPrimaryKey(adjust);
        } else {
            adjustMapper.insert(adjust);
        }
    }

    public void updateExample(Adjust adjust, Example example){
        adjustMapper.updateByExampleSelective(adjust, example);
    }



}
