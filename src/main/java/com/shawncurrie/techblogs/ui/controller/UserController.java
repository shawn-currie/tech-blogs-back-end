package com.shawncurrie.techblogs.ui.controller;

import com.shawncurrie.techblogs.service.UserService;
import com.shawncurrie.techblogs.shared.dto.BlogDTO;
import com.shawncurrie.techblogs.shared.dto.UserDTO;
import com.shawncurrie.techblogs.ui.model.response.BlogRest;
import com.shawncurrie.techblogs.ui.model.response.UserRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin("*")
    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserRest getUser(@PathVariable int userId) {
        UserDTO userDTO = userService.getUser(userId);

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(userDTO, UserRest.class);
    }


    @CrossOrigin("*")
    @GetMapping(path = "/{userId}/favorites", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<BlogRest> getUserFavorites(@PathVariable int userId,
                                           @RequestParam(value = "page", defaultValue = "0") int page,
                                           @RequestParam(value = "limit", defaultValue = "15") int limit) {

        List<BlogDTO> blogDTOS = userService.getFavoriteBlogs(userId, page, limit);
        List<BlogRest> results = new ArrayList<>();

        ModelMapper modelMapper = new ModelMapper();

        for (BlogDTO blogDTO : blogDTOS) {
            results.add(modelMapper.map(blogDTO, BlogRest.class));
        }

        return results;
    }
}
