package com.shawncurrie.techblogs.io.repository;

import com.shawncurrie.techblogs.io.entity.BlogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlogRepository extends PagingAndSortingRepository<BlogEntity, Long> {
    BlogEntity findById(long id);
    Page<BlogEntity> findAllByOrderByDateDesc(Pageable pageable);
}