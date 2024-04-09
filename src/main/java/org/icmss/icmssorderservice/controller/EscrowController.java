package org.icmss.icmssorderservice.controller;

import lombok.RequiredArgsConstructor;
import org.icmss.icmssorderservice.domain.response.EscrowAccountResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orders/{orderId}/escrow")
@RequiredArgsConstructor
public class EscrowController {
    private final EscrowService escrowService;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<EscrowAccountResponse> createEscrowAccount(@PathVariable Long orderId) {
        EscrowAccountResponse escrowAccount = escrowService.createEscrowAccount(orderId);
        return ResponseEntity.ok(escrowAccount);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'DEALER', 'ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<EscrowAccountResponse> getEscrowAccount(@PathVariable Long orderId) {
        EscrowAccountResponse escrowAccount = escrowService.getEscrowAccount(orderId);
        return ResponseEntity.ok(escrowAccount);
    }

    @PutMapping("/release")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<Void> releaseEscrowFunds(@PathVariable Long orderId) {
        escrowService.releaseEscrowFunds(orderId);
        return ResponseEntity.ok().build();
    }
}
