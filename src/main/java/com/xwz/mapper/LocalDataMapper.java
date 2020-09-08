package com.xwz.mapper;

import com.xwz.bean.PageUtils;
import com.xwz.bean.Pic;

import java.util.List;

public interface LocalDataMapper {
    List<Pic> SelectDataByAll(PageUtils pageUtils);

    int SelectCount(String key);

    int Del_Pic(int id);
}
