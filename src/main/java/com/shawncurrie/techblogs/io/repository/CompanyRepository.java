package com.shawncurrie.techblogs.io.repository;

import com.shawncurrie.techblogs.io.entity.CompanyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends PagingAndSortingRepository<CompanyEntity, Integer> {
    CompanyEntity findById(int id);
    Page<CompanyEntity> findAllByOrderByNameAsc(Pageable pageable);
}
