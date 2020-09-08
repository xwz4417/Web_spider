package com.xwz.service.impl;

import com.xwz.bean.PageUtils;
import com.xwz.bean.Pic;
import com.xwz.mapper.LocalDataMapper;
import com.xwz.service.LocalDataService;
import com.xwz.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalDataServiceImpl implements LocalDataService {

    private LocalDataMapper localDataMapper;
    private HttpUtils httpUtils;

    public LocalDataServiceImpl(LocalDataMapper localDataMapper, HttpUtils httpUtils) {
        this.localDataMapper = localDataMapper;
        this.httpUtils = httpUtils;
    }

    @Override
    public PageUtils SelectDataByAll(PageUtils page) {
        int recordCount = localDataMapper.SelectCount(page.getKey());
        page.setRecordCount(recordCount);
        if (recordCount % page.getPageSize() == 0) {
            page.setPageCount(recordCount / page.getPageSize());
        }
        page.setPageCount(recordCount / page.getPageSize() + 1);
        if (page.getCurrentPage() > page.getPageCount()) {
            page.setCurrentPage(page.getPageCount());
        } else if (page.getCurrentPage() <= 0) {
            page.setCurrentPage(1);
        }
        List<Pic> pics = localDataMapper.SelectDataByAll(page);
        page.setList(pics);
        return page;
    }

    @Override
    public int Del_Pic(int id) {
        return localDataMapper.Del_Pic(id);
    }

    @Override
    public int SelectDataCount(String key) {
        return localDataMapper.SelectCount(key);
    }

    @Override
    public void Download_Pic(String key, String group, String path) {
        PageUtils pageUtils = new PageUtils();
        key = "%" + key + "%";
        pageUtils.setKey(key);
        if (!"all".equals(group)) {
            int current = Integer.parseInt(group.substring(group.lastIndexOf("_") + 1));
            pageUtils.setCurrentPage(current);
        } else {
            pageUtils.setPageSize(SelectDataCount(key));
            pageUtils.setCurrentPage(1);
        }
        List<Pic> list = SelectDataByAll(pageUtils).getList();
        for (Pic p : list) {
            httpUtils.Download_Pic(p, path);
        }
    }
}
