package com.lmbx.file.core.mybatis.domain;

public class TableResponseContext {

    protected static ThreadLocal<TableResponse> localResponse = new ThreadLocal<>();

    private TableResponseContext(){

    }

    public static <T> TableResponse<T> getTableResponse() {
        TableResponse<T> tableResponse = localResponse.get();
        if (tableResponse == null) {
            tableResponse = new TableResponse<>();
        }
        localResponse.remove();
        return tableResponse;
    }

}
