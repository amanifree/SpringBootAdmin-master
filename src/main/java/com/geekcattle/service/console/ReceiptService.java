/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.service.console;

import com.geekcattle.mapper.basic.BillnoMapper;
import com.geekcattle.mapper.console.ReceiptMapper;
import com.geekcattle.model.basic.Billno;
import com.geekcattle.model.console.Receipt;
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
public class ReceiptService {

    @Autowired
    private ReceiptMapper receiptMapper;

    @Autowired
    private BillnoMapper billnoMapper;

/*    @Autowired
    private RoleService roleService;*/

    public List<Receipt> getPageList(Receipt receipt) {
        PageHelper.offsetPage(receipt.getOffset(), receipt.getLimit(), CamelCaseUtil.toUnderlineName(receipt.getSort())+" "+receipt.getOrder());
        return receiptMapper.selectAll();
    }

    public List<Receipt> getPageListByRole(HashMap params) {
        //PageHelper.offsetPage(receipt.getOffset(), receipt.getLimit(), CamelCaseUtil.toUnderlineName(receipt.getSort())+" "+receipt.getOrder());
        return receiptMapper.selectByRole(params);
    }

    public Integer getCount(Example example){
        return receiptMapper.selectCountByExample(example);
    }

    public Receipt getById(String id) {
        return receiptMapper.selectByPrimaryKey(id);
    }

/*    public Receipt findByUsername(String username) {
        return receiptMapper.selectByUsername(username);
    }*/

    public void deleteById(String id) {
        receiptMapper.deleteByPrimaryKey(id);
    }

    public void insert(Receipt receipt){
        //获取当前流水号
        String billtype = "LOIN";
        Billno billNo = billnoMapper.selectByBillType(billtype);
        String billData = billNo.getBilldate();
        String currData = DateUtil.getCurrentDate().replaceAll("-","");
        String currNo;
        String billid = billNo.getUid();
        if (currData.equals(billData)){
            currNo = billtype + currData + (billNo.getBillmaxno().toString()).substring(1);
            receipt.setReceiptNum(currNo);
            receiptMapper.insert(receipt);

            Integer newNo = billNo.getBillmaxno() + 1;
            billNo.setBillmaxno(newNo);
            billnoMapper.updateByPrimaryKey(billNo);
        }else{
            currNo = billtype + currData + "0001";
            receipt.setReceiptNum(currNo);
            receiptMapper.insert(receipt);

            Integer newNo = 10002;
            billNo.setBillmaxno(newNo);
            billNo.setBilldate(currData);
            billnoMapper.updateByPrimaryKey(billNo);
        }

    }

    public void save(Receipt receipt) {
        if (receipt.getUid() != null) {
            receiptMapper.updateByPrimaryKey(receipt);
        } else {
            receiptMapper.insert(receipt);
        }
    }

    public void updateExample(Receipt receipt, Example example){
        receiptMapper.updateByExampleSelective(receipt, example);
    }



}
