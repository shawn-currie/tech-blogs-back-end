package com.shawncurrie.techblogs.ui.controller;

import com.shawncurrie.techblogs.service.BlogService;
import com.shawncurrie.techblogs.shared.dto.BlogDTO;
import com.shawncurrie.techblogs.shared.dto.CompanyDTO;
import com.shawncurrie.techblogs.ui.model.response.BlogRest;
import com.shawncurrie.techblogs.ui.model.response.CompanyRest;
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
                                   @RequestParam(value = "companyId", defaultValue = "-1") int companyId) {

        List<BlogDTO> blogs;

        if (companyId == -1) {
            blogs = blogService.getBlogs(page, limit);
        } else {
            blogs = blogService.getBlogsByCompany(companyId, page, limit);
        }

        return mapBlogs(blogs);
    }
    
    @CrossOrigin(origins = "*")
    @GetMapping(path = "/favorites/{user}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<BlogRest> getFavoriteBlogs(@PathVariable int user,
                                   @RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "15") int limit) {

        List<BlogDTO> blogs = blogService.getFavoriteBlogs(user, page, limit);

        return mapBlogs(blogs);
    }

    private List<BlogRest> mapBlogs(List<BlogDTO> blogDTOS) {
        List<BlogRest> returnValues = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();

        for (BlogDTO blogDTO : blogDTOS) {
            CompanyDTO companyDTO = blogDTO.getCompanyDTO();
            BlogRest blogRest = modelMapper.map(blogDTO, BlogRest.class);
            CompanyRest companyRest = modelMapper.map(companyDTO, CompanyRest.class);
            blogRest.setCompany(companyRest);
            returnValues.add(blogRest);
        }

        return returnValues;
    }
}