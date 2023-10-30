package com.bellasend.bellasendbackend.web.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
@Getter
@Setter
public class PagedListProductDto {
    private Integer totalPages;
    private Long totalElements;
    private Integer currentPageNumber;
    private Integer currentNumOfElements;
    private Integer pageSize;
    private List<ProductDto> content;
    private boolean hasContent;
    private boolean hasNext;
    private boolean hasPrevious;
}
