package com.geekcattle.mapper.basic;

import com.geekcattle.model.basic.Billno;
import com.geekcattle.util.CustomerMapper;

public interface BillnoMapper extends CustomerMapper<Billno> {
    Billno selectByBillType(String billtype);
}