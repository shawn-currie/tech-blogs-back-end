package com.shawncurrie.techblogs.io.repository;

import com.shawncurrie.techblogs.io.entity.BlogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BlogRepository extends PagingAndSortingRepository<BlogEntity, Integer> {
    BlogEntity findById(int id);
    Page<BlogEntity> findAllByCompanyId(int companyId, Pageable pageable);
    Page<BlogEntity> findAllByOrderByDateDesc(Pageable pageable);
    List<BlogEntity> findByIdIn(List<Integer> id);
}