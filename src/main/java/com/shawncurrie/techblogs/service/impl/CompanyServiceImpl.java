package com.shawncurrie.techblogs.service.impl;

import com.shawncurrie.techblogs.io.entity.BlogEntity;
import com.shawncurrie.techblogs.io.entity.CompanyEntity;
import com.shawncurrie.techblogs.io.repository.CompanyRepository;
import com.shawncurrie.techblogs.service.CompanyService;
import com.shawncurrie.techblogs.shared.dto.BlogDTO;
import com.shawncurrie.techblogs.shared.dto.CompanyDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Override
    public List<CompanyDTO> getCompanies(int page, int limit) {
        List<CompanyDTO> returnValue = new ArrayList<>();

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<CompanyEntity> companyPages = companyRepository.findAllByOrderByNameAsc(pageableRequest);
        List<CompanyEntity> companies = companyPages.getContent();

        ModelMapper modelMapper = new ModelMapper();
        for(CompanyEntity companyEntity: companies) {
            returnValue.add(modelMapper.map(companyEntity, CompanyDTO.class));
        }

        return returnValue;
    }

    @Override
    public CompanyDTO getCompany(int companyId) {
        CompanyEntity companyEntity = companyRepository.findById(companyId);

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(companyEntity, CompanyDTO.class);
    }
}
