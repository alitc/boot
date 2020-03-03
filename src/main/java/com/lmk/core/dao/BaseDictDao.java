package com.lmk.core.dao;

import com.lmk.core.po.BaseDict;

import java.util.List;

public interface BaseDictDao {
    public List<BaseDict> selectBaseDictByTypeCode(String typecode);
}
