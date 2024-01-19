package com.social.ConnectSphere.Payload;

import lombok.Data;

import java.util.List;

@Data
public class PostResponse {
    private List<PostDTO> content;
    private int pageNumber;
    private int pageSize;
    private int totalElements;
    private int totalPages;
    private boolean LastPage;
}
