package com.shawncurrie.techblogs.service;

import com.shawncurrie.techblogs.shared.dto.CompanyDTO;

import java.util.List;

public interface CompanyService {
    List<CompanyDTO> getCompanies(int page, int limit);
    CompanyDTO getCompany(int companyId);
}
