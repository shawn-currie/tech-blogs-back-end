package com.shawncurrie.techblogs.service;

import com.shawncurrie.techblogs.shared.dto.BlogDTO;

import java.util.List;

public interface BlogService {
    List<BlogDTO> getBlogs(int page, int limit);
    List<BlogDTO> getBlogsByCompany(long companyId, int page, int limit);
}
