package com.shawncurrie.techblogs.io.repository;

import com.shawncurrie.techblogs.io.entity.FavoriteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FavoriteRepository extends PagingAndSortingRepository<FavoriteEntity, Integer> {
    Page<FavoriteEntity> findAllByUser(int user, Pageable pageable);
    FavoriteEntity findByUserAndBlog(int user, int blog);
    List<FavoriteEntity> findByUserAndBlogIn(int user, List<Integer> blogs);
}
