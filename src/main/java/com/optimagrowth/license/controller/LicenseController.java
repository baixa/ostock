package com.optimagrowth.license.controller;

import com.optimagrowth.license.model.License;
import com.optimagrowth.license.service.LicenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "v1/organization/{organizationId}/license")
public class LicenseController {
    private final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId) {
        License license = licenseService.getLicense(licenseId, organizationId);

        license.add(
                linkTo(methodOn(LicenseController.class).getLicense(organizationId, license.getLicenseId())).withSelfRel(),
                linkTo(methodOn(LicenseController.class).postLicense(organizationId, license, Locale.ROOT)).withRel("createLicense"),
                linkTo(methodOn(LicenseController.class).putLicense(organizationId, license, Locale.ROOT)).withRel("updateLicense"),
                linkTo(methodOn(LicenseController.class).deleteLicense(organizationId, license.getLicenseId(), Locale.ROOT)).withRel("deleteLicense"));

        return ResponseEntity.ok(license);
    }

    @PostMapping
    public ResponseEntity<String> postLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License license,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        String response = licenseService.createLicense(license, organizationId, locale);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<String> putLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License license,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        String response = licenseService.updateLicense(license, organizationId, locale);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(value = "/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        String response = licenseService.deleteLicense(licenseId, organizationId, locale);
        return ResponseEntity.ok(response);
    }
}
