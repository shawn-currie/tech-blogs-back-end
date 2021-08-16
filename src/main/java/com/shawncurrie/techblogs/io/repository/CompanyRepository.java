package com.shawncurrie.techblogs.io.repository;

import com.shawncurrie.techblogs.io.entity.CompanyEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CompanyRepository extends PagingAndSortingRepository<CompanyEntity, Long> {
    CompanyEntity findById(long id);
}
