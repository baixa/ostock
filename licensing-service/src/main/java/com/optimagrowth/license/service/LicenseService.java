package com.optimagrowth.license.service;

import com.optimagrowth.license.config.ServiceConfig;
import com.optimagrowth.license.model.License;
import com.optimagrowth.license.model.Organization;
import com.optimagrowth.license.repository.LicenseRepository;
import com.optimagrowth.license.service.client.OrganizationDiscoveryClient;
import com.optimagrowth.license.service.client.OrganizationFeignClient;
import com.optimagrowth.license.service.client.OrganizationRestTemplateClient;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LicenseService {

    private final MessageSource messages;
    private final LicenseRepository licenseRepository;
    private final ServiceConfig config;
    private final OrganizationFeignClient organizationFeignClient;
    private final OrganizationRestTemplateClient organizationRestClient;
    private final OrganizationDiscoveryClient organizationDiscoveryClient;

    public LicenseService(MessageSource messageSource, LicenseRepository licenseRepository, ServiceConfig config, OrganizationFeignClient organizationFeignClient, OrganizationRestTemplateClient organizationRestClient, OrganizationDiscoveryClient organizationDiscoveryClient) {
        this.messages = messageSource;
        this.licenseRepository = licenseRepository;
        this.config = config;
        this.organizationFeignClient = organizationFeignClient;
        this.organizationRestClient = organizationRestClient;
        this.organizationDiscoveryClient = organizationDiscoveryClient;
    }

    public License getLicense(String licenseId, String organizationId, String clientType) {
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        if(license == null) {
            throw new IllegalArgumentException(String.format(messages.getMessage("license.search.error.message", null, null), licenseId, organizationId));
        }

        Organization organization = retrieveOrganizationInfo(organizationId, clientType);
        if (organization != null) {
            license.setOrganizationName(organization.getName());
            license.setContactName(organization.getContactName());
            license.setContactEmail(organization.getContactEmail());
            license.setContactPhone(organization.getContactPhone());
        }

        return license.withComment(config.getProperty());
    }

    private Organization retrieveOrganizationInfo(String organizationId, String clientType) {
        Organization organization;

        switch (clientType) {
            case "feign" -> {
                System.out.println("I am using the feign client");
                organization = organizationFeignClient.getOrganization(organizationId);
            }
            case "rest" -> {
                System.out.println("I am using the rest client");
                organization = organizationDiscoveryClient.getOrganization(organizationId);
            }
            case "discovery" -> {
                System.out.println("I am using the discovery client");
                organization = organizationDiscoveryClient.getOrganization(organizationId);
            }
            default -> organization = organizationRestClient.getOrganization(organizationId);
        }

        return organization;
    }

    public License createLicense(License license) {
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);

        return license.withComment(config.getProperty());
    }

    public License updateLicense(License license) {
        licenseRepository.save(license);

        return license.withComment(config.getProperty());
    }

    public String deleteLicense(String licenseId) {
        String responseMessage;
        License license = new License();
        license.setLicenseId(licenseId);
        licenseRepository.delete(license);
        responseMessage = String.format(messages.getMessage("license.delete.message", null, null), licenseId);
        return responseMessage;
    }

    public List<License> getLicensesByOrganizationId(String organizationId) {
        return licenseRepository.findByOrganizationId(organizationId);
    }

}
