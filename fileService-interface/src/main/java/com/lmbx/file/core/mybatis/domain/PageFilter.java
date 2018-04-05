package com.lmbx.file.core.mybatis.domain;

public class PageFilter {

    // 当前页码
    private int page     = 1;
    // 分页数量
    private int pageSize = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
