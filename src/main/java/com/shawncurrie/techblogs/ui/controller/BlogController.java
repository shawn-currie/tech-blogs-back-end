package com.shawncurrie.techblogs.ui.controller;

import com.shawncurrie.techblogs.service.BlogService;
import com.shawncurrie.techblogs.shared.dto.BlogDTO;
import com.shawncurrie.techblogs.ui.model.response.BlogRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("blogs")
public class BlogController {

    @Autowired
    BlogService blogService;

    @CrossOrigin(origins = "*")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<BlogRest> getBlogs(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "15") int limit,
                                   @RequestParam(value = "companyId", defaultValue = "-1") long companyId) {

        List<BlogDTO> blogs;

        if (companyId == -1) {
            blogs = blogService.getBlogs(page, limit);
        } else {
            blogs = blogService.getBlogsByCompany(companyId, page, limit);
        }

        return mapBlogs(blogs);
    }

    private List<BlogRest> mapBlogs(List<BlogDTO> blogDTOS) {
        List<BlogRest> returnValues = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();

        for (BlogDTO blogDTO : blogDTOS) {
            returnValues.add(modelMapper.map(blogDTO, BlogRest.class));
        }

        return returnValues;
    }
}