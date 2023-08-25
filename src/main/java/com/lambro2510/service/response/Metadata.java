package com.lambro2510.service.response;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class Metadata {

  private final int page;
  private final long pageSize;
  private final long count;
  private final long totalElements;
  private final long totalPages;

  public static Metadata createFrom(Page page) {
    return new Metadata(
        page.getNumber(),
        page.getSize(),
        page.getNumberOfElements(),
        page.getTotalElements(),
        page.getTotalPages());
  }

  public static Metadata createFrom(Page page, int totalRecord) {
    int totalPage;
    if (totalRecord % page.getSize() != 0) {
      totalPage = (totalRecord / page.getSize()) + 1;
    } else {
      totalPage = (totalRecord / page.getSize());
    }
    return new Metadata(
        page.getNumber(), page.getSize(), page.getTotalElements(), totalRecord, totalPage);
  }
}
