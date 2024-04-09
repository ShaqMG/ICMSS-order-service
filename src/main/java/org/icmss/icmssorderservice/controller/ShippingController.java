package org.icmss.icmssorderservice.controller;

import lombok.RequiredArgsConstructor;
import org.icmss.icmssorderservice.domain.request.ShippingDetailsRequest;
import org.icmss.icmssorderservice.domain.response.ShippingDetailsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders/{orderId}/shipping")
@RequiredArgsConstructor
public class ShippingController {
    private final ShippingService shippingService;

    @PostMapping
    @PreAuthorize("hasAnyRole('DEALER', 'ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<ShippingDetailsResponse> createShippingDetails(@PathVariable Long orderId, @RequestBody ShippingDetailsRequest shippingDetailsRequest) {
        ShippingDetailsResponse shippingDetails = shippingService.createShippingDetails(orderId, shippingDetailsRequest);
        return ResponseEntity.ok(shippingDetails);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'DEALER', 'ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<ShippingDetailsResponse> getShippingDetails(@PathVariable Long orderId) {
        ShippingDetailsResponse shippingDetails = shippingService.getShippingDetails(orderId);
        return ResponseEntity.ok(shippingDetails);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('DEALER', 'ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<ShippingDetailsResponse> updateShippingDetails(@PathVariable Long orderId, @RequestBody ShippingDetailsRequest shippingDetailsRequest) {
        ShippingDetailsResponse updatedShippingDetails = shippingService.updateShippingDetails(orderId, shippingDetailsRequest);
        return ResponseEntity.ok(updatedShippingDetails);
    }
}
