/*
 * Copyright (c) 2017 <l_iupeiyu@qq.com> All rights reserved.
 */

package com.geekcattle.service.common;

import com.geekcattle.mapper.basic.BillnoMapper;
import com.geekcattle.model.basic.Billno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/**
 * author geekcattle
 * date 2016/10/21 0021 下午 15:43
 */
@Service
public class BillnoService {

    @Autowired
    private BillnoMapper billnoMapper;

    public List<Billno> getPageList(Billno billno) {
        //PageHelper.offsetPage(billno.getOffset(), billno.getLimit(), CamelCaseUtil.toUnderlineName(billno.getSort())+" "+billno.getOrder());
        return billnoMapper.selectAll();
    }

    public Integer getCount(Example example){
        return billnoMapper.selectCountByExample(example);
    }

    public Billno getById(String id) {
        return billnoMapper.selectByPrimaryKey(id);
    }

/*    public Receipt findByUsername(String username) {
        return receiptMapper.selectByUsername(username);
    }*/

    public void deleteById(String id) {
        billnoMapper.deleteByPrimaryKey(id);
    }

    public void insert(Billno billno){
        billnoMapper.insert(billno);
    }

    public void save(Billno billno) {
        if (billno.getUid() != null) {
            billnoMapper.updateByPrimaryKey(billno);
        } else {
            billnoMapper.insert(billno);
        }
    }

    public void updateExample(Billno billno, Example example){
        billnoMapper.updateByExampleSelective(billno, example);
    }

}
