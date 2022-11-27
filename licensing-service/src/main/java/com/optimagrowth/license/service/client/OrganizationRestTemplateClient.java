package com.optimagrowth.license.service.client;

import com.optimagrowth.license.model.Organization;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrganizationRestTemplateClient {
    private RestTemplate restTemplate;

    public OrganizationRestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Organization getOrganization(String organizationId) {
        return restTemplate.exchange(
                "http://organization-service/v1/oranization/{organizationId}", HttpMethod.GET,
                null, Organization.class, organizationId).getBody();
    }
}
