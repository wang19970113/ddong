package com.ddong.common.pojo;

import lombok.Data;

import java.util.List;
@Data
public class PageResult<T> {
    private Long  pageIndex;
    private Long pageSize;
    private Long totalRows;
    private Long totalPages;
    private List<T> list;

}
