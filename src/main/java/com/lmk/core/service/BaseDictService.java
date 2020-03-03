package com.lmk.core.service;

import com.lmk.core.po.BaseDict;

import java.util.List;

public interface BaseDictService {
    public List<BaseDict>findBaseDictByTypeCode(String typecode);
}
