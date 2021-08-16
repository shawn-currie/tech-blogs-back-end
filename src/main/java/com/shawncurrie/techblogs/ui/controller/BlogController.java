package com.shawncurrie.techblogs.ui.controller;

import com.shawncurrie.techblogs.service.BlogService;
import com.shawncurrie.techblogs.service.CompanyService;
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
                                   @RequestParam(value = "limit", defaultValue = "20") int limit) {
        List<BlogRest> returnValues = new ArrayList<>();

        List<BlogDTO> blogs = blogService.getBlogs(page, limit);

        ModelMapper modelMapper = new ModelMapper();

        for (BlogDTO blogDTO : blogs) {
            returnValues.add(modelMapper.map(blogDTO, BlogRest.class));
        }

        return returnValues;
    }
}