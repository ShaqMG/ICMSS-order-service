package org.icmss.icmssorderservice.controller;

import lombok.RequiredArgsConstructor;
import org.icmss.icmssorderservice.domain.request.TransactionRequest;
import org.icmss.icmssorderservice.domain.response.TransactionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orders/{orderId}/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER')")
    public ResponseEntity<TransactionResponse> initiateTransaction(@PathVariable Long orderId) {
        TransactionResponse transaction = transactionService.initiateTransaction(orderId);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<TransactionResponse> getTransaction(@PathVariable Long orderId) {
        TransactionResponse transaction = transactionService.getTransaction(orderId);
        return ResponseEntity.ok(transaction);
    }

    @PutMapping("/{transactionId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<TransactionResponse> updateTransaction(@PathVariable Long orderId, @PathVariable Long transactionId, @RequestBody TransactionRequest transactionRequest) {
        TransactionResponse updatedTransaction = transactionService.updateTransaction(orderId, transactionId, transactionRequest);
        return ResponseEntity.ok(updatedTransaction);
    }
}
