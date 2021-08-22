package com.shawncurrie.techblogs.ui.controller;

import com.shawncurrie.techblogs.service.CompanyService;
import com.shawncurrie.techblogs.shared.dto.CompanyDTO;
import com.shawncurrie.techblogs.ui.model.response.CompanyRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @CrossOrigin("*")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<CompanyRest> getCompanies(@RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "limit", defaultValue = "20") int limit) {

        List<CompanyRest> returnValues = new ArrayList<>();

        List<CompanyDTO> companies = companyService.getCompanies(page, limit);

        ModelMapper modelMapper = new ModelMapper();

        for (CompanyDTO companyDTO : companies) {
            returnValues.add(modelMapper.map(companyDTO, CompanyRest.class));
        }

        return returnValues;
    }

    @CrossOrigin("*")
    @GetMapping(path="/{companyId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompanyRest getCompany(@PathVariable Long companyId) {
        CompanyDTO companyDTO = companyService.getCompany(companyId);

        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(companyDTO, CompanyRest.class);
    }
}
