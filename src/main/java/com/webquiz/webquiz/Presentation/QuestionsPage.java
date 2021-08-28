package com.webquiz.webquiz.Presentation;

import org.springframework.data.domain.Sort;

public class QuestionsPage {
    private final int pageNumber;
    private final int pageSize;
    private final Sort.Direction sortDirection = Sort.Direction.DESC;
    private final String sortBy = "completedAt";

    public QuestionsPage(int pageNumber, int pageSize) {
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    public Sort.Direction getSortDirection() {
        return sortDirection;
    }

    public String getSortBy() {
        return sortBy;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
