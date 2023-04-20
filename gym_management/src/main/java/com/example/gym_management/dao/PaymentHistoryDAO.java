package com.example.gym_management.dao;

import java.util.List;
import java.util.Optional;

import com.example.gym_management.model.PaymentHistory;

public interface PaymentHistoryDAO {
    List<PaymentHistory> getAllPaymentHistory();
    Optional<PaymentHistory> getPaymentHistoryById(int paymentId);
    List<PaymentHistory> getPaymentHistoryByMemberId(int memberId);
    PaymentHistory addPaymentHistory(PaymentHistory paymentHistory);
    PaymentHistory updatePaymentHistory(PaymentHistory paymentHistory);
    boolean deletePaymentHistory(int paymentId);
    Optional<PaymentHistory> getLastPaymentByMemberId(int memberId);
}
