package org.icmss.icmssorderservice.controller;

import lombok.RequiredArgsConstructor;
import org.icmss.icmssorderservice.domain.response.TaxResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/orders/{orderId}/taxes")
@RequiredArgsConstructor
public class TaxController {
    private final TaxService taxService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<List<TaxResponse>> calculateAndApplyTaxes(@PathVariable Long orderId) {
        List<TaxResponse> taxes = taxService.calculateAndApplyTaxes(orderId);
        return ResponseEntity.ok(taxes);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'DEALER', 'ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<List<TaxResponse>> getTaxes(@PathVariable Long orderId) {
        List<TaxResponse> taxes = taxService.getTaxes(orderId);
        return ResponseEntity.ok(taxes);
    }
}
