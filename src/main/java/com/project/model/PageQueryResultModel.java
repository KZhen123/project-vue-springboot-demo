package com.project.model;

import java.util.List;

/**
 * 分页查询结果对象
 *
 * @param <R>
 */
public class PageQueryResultModel<R> {

    /**
     * 结果集总数
     */
    private Long total;

    /**
     * 当前返回的列表
     */
    private List<R> data;

    public PageQueryResultModel(Long total, List<R> data) {
        this.total = total;
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public PageQueryResultModel<R> setTotal(Long total) {
        this.total = total;
        return this;
    }

    public List<R> getData() {
        return data;
    }

    public PageQueryResultModel<R> setData(List<R> data) {
        this.data = data;
        return this;
    }
}
