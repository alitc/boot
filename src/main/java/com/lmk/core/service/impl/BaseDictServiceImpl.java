package com.lmk.core.service.impl;

import com.lmk.core.dao.BaseDictDao;
import com.lmk.core.po.BaseDict;
import com.lmk.core.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("basaeDictService")
public class BaseDictServiceImpl implements BaseDictService {
    @Autowired
    private BaseDictDao baseDictDao;

    public List<BaseDict> findBaseDictByTypeCode(String typecode) {
        return baseDictDao.selectBaseDictByTypeCode(typecode);
    }
}
