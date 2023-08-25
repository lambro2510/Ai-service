package com.lambro2510.service.response;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
public class PageResponse<T> {

  public static <T> PageResponse<T> createFrom(Page<T> pageData) {
    PageResponse<T> pageResponse = new PageResponse(pageData.getContent(), Metadata.createFrom(pageData));
    return pageResponse;
  }

  public static <T> PageResponse<T> createFrom(List<T> listData, Pageable pageable) {
    int totalPages = (listData.size() + pageable.getPageSize() - 1) / pageable.getPageSize();
    int totalElement = listData.size();
    if (pageable.getPageNumber() + 1 > totalPages) {
      listData.clear();
    }
    Page<T> pageData = new PageImpl<>(listData, pageable, totalElement);
    return (PageResponse<T>) new PageResponse(pageData.getContent(), Metadata.createFrom(pageData));
  }

  public final List<T> data;
  public final Metadata metadata;
}
