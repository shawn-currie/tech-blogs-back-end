package com.shawncurrie.techblogs.service;

import com.shawncurrie.techblogs.shared.dto.BlogDTO;
import com.shawncurrie.techblogs.shared.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUser(int id);
    List<BlogDTO> getFavoriteBlogs(int userId, int page, int limit);
}
