package com.shawncurrie.techblogs.service.impl;

import com.shawncurrie.techblogs.io.entity.*;
import com.shawncurrie.techblogs.io.repository.BlogRepository;
import com.shawncurrie.techblogs.io.repository.CompanyRepository;
import com.shawncurrie.techblogs.io.repository.FavoriteRepository;
import com.shawncurrie.techblogs.io.repository.UserRepository;
import com.shawncurrie.techblogs.service.UserService;
import com.shawncurrie.techblogs.shared.dto.BlogDTO;
import com.shawncurrie.techblogs.shared.dto.CompanyDTO;
import com.shawncurrie.techblogs.shared.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    FavoriteRepository favoriteRepository;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public UserDTO getUser(int id) {
        UserEntity userEntity = userRepository.findById(id);

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(userEntity, UserDTO.class);
    }

    @Override
    public List<BlogDTO> getFavoriteBlogs(int userId, int page, int limit) {

        Pageable pageable = PageRequest.of(page, limit);

        Page<FavoriteEntity> favoriteEntitiesPage = favoriteRepository.findAllByUser(userId, pageable);
        List<FavoriteEntity> favoriteEntitiesList = favoriteEntitiesPage.getContent();

        List<Integer> blogIds = new ArrayList<>();

        for (FavoriteEntity favoriteEntity : favoriteEntitiesList) {
            blogIds.add(favoriteEntity.getBlog());
        }

        List<BlogEntity> blogEntities = blogRepository.findByIdIn(blogIds);

        return mapBlogsAndCompanies(blogEntities);
    }

    @Override
    public void addFavoriteBlog(int user, int blog) {
        if (favoriteRepository.findByUserAndBlog(user, blog) != null) {
            System.out.println("blog already exists");
            return;
        }

        FavoriteEntity favoriteEntity = new FavoriteEntity(user, blog);

        favoriteRepository.save(favoriteEntity);
    }

    @Override
    public void removeFavoriteBlog(int user, int blog) {
        FavoriteEntity favoriteEntity = favoriteRepository.findByUserAndBlog(user, blog);

        if (favoriteEntity == null) {
            return;
        }

        favoriteRepository.delete(favoriteEntity);
    }

    // temporary until JPA is working
    public List<BlogDTO> mapBlogsAndCompanies(List<BlogEntity> blogEntities) {
        List<BlogDTO> returnValue = new ArrayList<>();
        Map<Integer, CompanyDTO> companyDetails = new HashMap<>();
        ModelMapper modelMapper = new ModelMapper();

        for(BlogEntity blogEntity: blogEntities) {
            BlogDTO blog = modelMapper.map(blogEntity, BlogDTO.class);

            if (!companyDetails.containsKey(blogEntity.getCompanyId())) {
                CompanyEntity companyEntity = companyRepository.findById(blogEntity.getCompanyId());
                CompanyDTO companyDTO = modelMapper.map(companyEntity, CompanyDTO.class);
                companyDetails.put(companyDTO.getId(), companyDTO);
            }
            blog.setCompanyDTO(companyDetails.get(blogEntity.getCompanyId()));
            returnValue.add(blog);
        }

        return returnValue;
    }
}
