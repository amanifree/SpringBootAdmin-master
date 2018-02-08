package com.geekcattle.mapper.console;

import com.geekcattle.model.console.Adjust;
import com.geekcattle.util.CustomerMapper;
import java.util.HashMap;
import java.util.List;

public interface AdjustMapper extends CustomerMapper<Adjust> {
    List<Adjust> selectByRole(HashMap params);
}