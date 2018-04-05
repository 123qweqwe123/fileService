package com.lmbx.file.core.mybatis.domain;

import java.util.List;

public class TableResponse<T> {

    private long    total;
    private List<T> list;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public TableResponse setList(List<T> list) {
        this.list = list;
        return this;
    }
}
