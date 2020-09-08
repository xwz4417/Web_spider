package com.xwz.service;

import com.xwz.bean.PageUtils;
import com.xwz.bean.Pic;

import java.util.List;

public interface LocalDataService {
    PageUtils SelectDataByAll(PageUtils page);
    int Del_Pic(int id);
    int SelectDataCount(String key);
    void Download_Pic(String key,String group,String path);
}
