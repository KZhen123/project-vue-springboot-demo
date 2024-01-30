package com.project.model.dto.request;

/**
 * 单表，或者复杂查询使用 作为请求入参条件
 * 分页
 */
public abstract class BaseQuery {

    private int page = 1;

    private int size = 10;

    private int row;

    public int getPage() {
        return page;
    }

    public BaseQuery setPage(int page) {
        this.page = page;
        return this;
    }

    public int getSize() {
        return size;
    }

    public BaseQuery setSize(int size) {
        this.size = size;
        return this;
    }

    public int getRow() {
        return (this.page - 1) * this.size;
    }
}
