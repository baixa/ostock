package com.optimagrowth.organizationservice.repository;

import com.optimagrowth.organizationservice.model.Organization;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrganizationRepository extends CrudRepository<Organization, String> {
}