package com.zzx.model;

import java.util.List;

public class Page<T> {
    private List<T> modelList;
    private Integer pageTotal; //总页数
    private Integer showCount = 5; //一个页面显示数量，默认显示五条
    private Integer currentPage = 1; //当前页数，默认第一页

    public List<T> getModelList() {
        return modelList;
    }

    public void setModelList(List<T> modelList) {
        this.modelList = modelList;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getShowCount() {
        return showCount;
    }

    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }
}