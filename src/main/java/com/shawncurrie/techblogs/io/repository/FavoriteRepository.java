package com.shawncurrie.techblogs.io.repository;

import com.shawncurrie.techblogs.io.entity.FavoriteEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FavoriteRepository extends PagingAndSortingRepository<FavoriteEntity, Integer> {
    List<FavoriteEntity> findAllByUser(int user);
}
