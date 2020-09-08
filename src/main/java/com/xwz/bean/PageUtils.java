package com.xwz.bean;

import java.util.List;

public class PageUtils {
    private  int pageSize=40;
    private Integer currentPage;
    private Integer pageCount;
    private Integer dataBefore;
    private List<Pic> list;
    private Integer recordCount;
    private String key;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getPageSize() {
        return pageSize;
    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getDataBefore() {
        this.dataBefore=(currentPage-1)*getPageSize();
        return dataBefore;
    }

    public List<Pic> getList() {
        return list;
    }

    public void setList(List<Pic> list) {
        this.list = list;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
