package com.example.gym_management.controller;

import com.example.gym_management.model.PaymentHistory;
import com.example.gym_management.service.PaymentHistoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment-history")
@CrossOrigin(origins = "http://localhost:3000")
public class PaymentHistoryController {

  @Autowired
  private PaymentHistoryService paymentHistoryService;

  @GetMapping("/{memberId}")
  @CrossOrigin(origins = "http://localhost:3000")
  public ResponseEntity<List<PaymentHistory>> getPaymentHistoryByMemberId(
    @PathVariable("memberId") int memberId
  ) {
    List<PaymentHistory> paymentHistoryList = paymentHistoryService.getPaymentHistoryByMemberId(
      memberId
    );
    return ResponseEntity.ok().body(paymentHistoryList);
  }

  @PostMapping("/{memberId}")
  public ResponseEntity<PaymentHistory> addPaymentHistory(
    @RequestBody PaymentHistory paymentHistory
  ) {
    PaymentHistory addedPaymentHistory = paymentHistoryService.addPaymentHistory(
      paymentHistory
    );
    return ResponseEntity.status(HttpStatus.CREATED).body(addedPaymentHistory);
  }

  @GetMapping
  public ResponseEntity<List<PaymentHistory>> getAllPaymentHistory() {
    List<PaymentHistory> paymentHistories = paymentHistoryService.getAllPaymentHistory();
    return new ResponseEntity<>(paymentHistories, HttpStatus.OK);
  }

  @DeleteMapping("/{paymentId}")
  public ResponseEntity<?> deletePaymentHistory(@PathVariable int paymentId) {
    if (paymentHistoryService.deletePaymentHistory(paymentId)) {
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("/{paymentId}")
  public ResponseEntity<PaymentHistory> updatePaymentHistory(
    @PathVariable int paymentId,
    @RequestBody PaymentHistory paymentHistory
  ) {
    PaymentHistory updatedPaymentHistory = paymentHistoryService.updatePaymentHistory(
      paymentHistory
    );
    if (updatedPaymentHistory != null) {
      return ResponseEntity.ok(updatedPaymentHistory);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/member/{memberId}")
  @CrossOrigin(origins = "http://localhost:3000")
  public ResponseEntity<PaymentHistory> getLastPaymentByMemberId(
    @PathVariable("memberId") int memberId
  ) {
    PaymentHistory payment = paymentHistoryService.getLastPaymentByMemberId(
      memberId
    );
    return ResponseEntity.ok().body(payment);
  }
}
