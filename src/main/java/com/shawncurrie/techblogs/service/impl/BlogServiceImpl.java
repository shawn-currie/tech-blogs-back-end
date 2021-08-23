package com.shawncurrie.techblogs.service.impl;

import com.shawncurrie.techblogs.io.entity.BlogEntity;
import com.shawncurrie.techblogs.io.entity.CompanyEntity;
import com.shawncurrie.techblogs.io.entity.FavoriteEntity;
import com.shawncurrie.techblogs.io.repository.BlogRepository;
import com.shawncurrie.techblogs.io.repository.CompanyRepository;
import com.shawncurrie.techblogs.io.repository.FavoriteRepository;
import com.shawncurrie.techblogs.service.BlogService;
import com.shawncurrie.techblogs.shared.dto.BlogDTO;
import com.shawncurrie.techblogs.shared.dto.CompanyDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    FavoriteRepository favoriteRepository;

    // TODO: figure out how to do OnToOne mappings instead of repeated DB calls. I hate this lol
    @Override
    public List<BlogDTO> getBlogs(int page, int limit) {

        // TODO: verify if sort is still needed
        Pageable pageableRequest = PageRequest.of(page, limit, Sort.by("id"));
        Page<BlogEntity> blogPages = blogRepository.findAllByOrderByDateDesc(pageableRequest);
        List<BlogEntity> blogs = blogPages.getContent();

        return mapBlogsAndCompanies(blogs);
    }

    @Override
    public List<BlogDTO> getBlogsByCompany(int companyId, int page, int limit) {

        Pageable pageableRequest = PageRequest.of(page, limit, Sort.by("id"));
        Page<BlogEntity> blogPages = blogRepository.findAllByCompanyId(companyId, pageableRequest);
        List<BlogEntity> blogs = blogPages.getContent();

        return mapBlogsAndCompanies(blogs);
    }

    // TODO: get joins to work so I don't have to make 2 calls
    // The ordering in this current system will get weird. When I have joins working I should sort by blog date
    @Override
    public List<BlogDTO> getFavoriteBlogs(int user, int page, int limit) {

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<FavoriteEntity> favoritesPage = favoriteRepository.findAllByUser(user, pageableRequest);
        List<FavoriteEntity> favorites = favoritesPage.getContent();

        List<Integer> blogIds = new ArrayList<>();
        for (FavoriteEntity favoriteEntity : favorites) {
            blogIds.add(favoriteEntity.getBlog());
        }

        List<BlogEntity> blogEntities = blogRepository.findByIdIn(blogIds);

        return mapBlogsAndCompanies(blogEntities);
    }

    private List<BlogDTO> mapBlogsAndCompanies(List<BlogEntity> blogEntities) {
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