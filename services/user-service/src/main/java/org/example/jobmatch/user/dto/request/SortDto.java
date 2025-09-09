package org.example.jobmatch.user.dto.request;

import lombok.Data;

@Data
public class SortDto {
    private String property;
    private String direction; // "ASC" or "DESC"
}