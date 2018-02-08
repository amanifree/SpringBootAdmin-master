package com.geekcattle.mapper.console;

import com.geekcattle.model.console.Distrib;
import com.geekcattle.util.CustomerMapper;

import java.util.HashMap;
import java.util.List;

public interface DistribMapper extends CustomerMapper<Distrib> {
    List<Distrib> selectByRole(HashMap params);
}