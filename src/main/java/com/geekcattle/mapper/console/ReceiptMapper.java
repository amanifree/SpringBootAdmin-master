package com.geekcattle.mapper.console;

import com.geekcattle.model.console.Receipt;
import com.geekcattle.util.CustomerMapper;

import java.util.HashMap;
import java.util.List;

public interface ReceiptMapper extends CustomerMapper<Receipt> {
    List<Receipt> selectByRole(HashMap params);
}