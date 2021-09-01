package com.shawncurrie.techblogs.service.impl;

import com.shawncurrie.techblogs.exceptions.UserServiceException;
import com.shawncurrie.techblogs.io.entity.*;
import com.shawncurrie.techblogs.io.repository.BlogRepository;
import com.shawncurrie.techblogs.io.repository.CompanyRepository;
import com.shawncurrie.techblogs.io.repository.FavoriteRepository;
import com.shawncurrie.techblogs.io.repository.UserRepository;
import com.shawncurrie.techblogs.service.UserService;
import com.shawncurrie.techblogs.shared.dto.BlogDTO;
import com.shawncurrie.techblogs.shared.dto.CompanyDTO;
import com.shawncurrie.techblogs.shared.dto.UserDTO;
import com.shawncurrie.techblogs.ui.model.response.ErrorMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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

        Pageable pageable = PageRequest.of(page, limit, Sort.by("blog"));

        Page<FavoriteEntity> favoriteEntitiesPage = favoriteRepository.findAllByUserOrderByDateDesc(userId, pageable);
        List<FavoriteEntity> favoriteEntitiesList = favoriteEntitiesPage.getContent();

        List<Integer> favortieBlogIds = new ArrayList<>();

        for (FavoriteEntity favoriteEntity : favoriteEntitiesList) {
            favortieBlogIds.add(favoriteEntity.getBlog());
        }

        List<BlogEntity> blogEntities = blogRepository.findByIdIn(favortieBlogIds);

        Map<Integer, BlogEntity> blogIdMapping = new HashMap<>();

        for (BlogEntity blogEntity : blogEntities) {
            blogIdMapping.put(blogEntity.getId(), blogEntity);
        }

        List<BlogEntity> orderedBlogs = new ArrayList<>();

        for (Integer blogId : favortieBlogIds) {
            orderedBlogs.add(blogIdMapping.get(blogId));
        }

        return mapBlogsAndCompanies(orderedBlogs);
    }

    @Override
    public void addFavoriteBlog(int user, int blog) {
        if (favoriteRepository.findByUserAndBlog(user, blog) != null) {
            throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
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

    @Override
    public void updateFavoriteStatus(int userId, List<BlogDTO> blogs) {

        List<Integer> blogIds = new ArrayList<>();

        for (BlogDTO blogDTO : blogs) {
            blogIds.add(blogDTO.getId());
        }

        List<FavoriteEntity> favoriteEntities = favoriteRepository.findByUserAndBlogIn(userId, blogIds);

        Set<Integer> favoriteBlogIds = new HashSet<>();

        for (FavoriteEntity favoriteEntity : favoriteEntities) {
            favoriteBlogIds.add(favoriteEntity.getBlog());
        }

        for (BlogDTO blogDTO : blogs) {
            if (favoriteBlogIds.contains(blogDTO.getId())) {
                blogDTO.setFavorite(true);
            }
        }
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
