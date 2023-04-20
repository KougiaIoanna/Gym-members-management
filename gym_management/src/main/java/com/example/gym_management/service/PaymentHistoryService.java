package com.example.gym_management.service;

import com.example.gym_management.dao.PaymentHistoryDAO;
import com.example.gym_management.model.PaymentHistory;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentHistoryService {

  private PaymentHistoryDAO paymentHistoryDAO;

  @Autowired
  public PaymentHistoryService(PaymentHistoryDAO paymentHistoryDAO) {
    this.paymentHistoryDAO = paymentHistoryDAO;
  }

  public List<PaymentHistory> getAllPaymentHistory() {
    List<PaymentHistory> payments = paymentHistoryDAO.getAllPaymentHistory();

    for (PaymentHistory payment : payments) {
      System.out.println(payment.toStringWithNames());
    }
    return payments;
  }

  public List<PaymentHistory> getPaymentHistoryByMemberId(int memberId) {
    return paymentHistoryDAO.getPaymentHistoryByMemberId(memberId);
  }

  public Optional<PaymentHistory> getPaymentHistoryById(int paymentId) {
    return paymentHistoryDAO.getPaymentHistoryById(paymentId);
  }

  public PaymentHistory getLastPaymentByMemberId(int memberId) {
    Optional<PaymentHistory> result = paymentHistoryDAO.getLastPaymentByMemberId(
      memberId
    );
    if (result.isPresent()) {
      return result.get();
    } else {
      throw new RuntimeException(
        "Payment history not found for member id: " + memberId
      );
    }
  }

  public PaymentHistory addPaymentHistory(PaymentHistory paymentHistory) {
    return paymentHistoryDAO.addPaymentHistory(paymentHistory);
  }

  public PaymentHistory updatePaymentHistory(PaymentHistory paymentHistory) {
    return paymentHistoryDAO.updatePaymentHistory(paymentHistory);
  }

  public boolean deletePaymentHistory(int paymentId) {
    return paymentHistoryDAO.deletePaymentHistory(paymentId);
  }
}
