package com.example.gym_management.model;

import java.math.BigDecimal;
import java.sql.Date;

public class PaymentHistory {

  private int paymentId;
  private int memberId;
  private String paymentDate;
  private int membershipTypeId;
  private BigDecimal amount;
  private String memberName;
  private String membershipTypeName;

  public PaymentHistory(
    int paymentId,
    int memberId,
    String paymentDate,
    int membershipTypeId,
    BigDecimal amount
  ) {
    this.paymentId = paymentId;
    this.memberId = memberId;
    this.paymentDate = paymentDate;
    this.membershipTypeId = membershipTypeId;
    this.amount = amount;
  }

  public PaymentHistory() {}

  public PaymentHistory(
    int memberId,
    String paymentDate,
    int membershipTypeId,
    BigDecimal amount
  ) {
    this.memberId = memberId;
    this.paymentDate = paymentDate;
    this.membershipTypeId = membershipTypeId;
    this.amount = amount;
  }

  public PaymentHistory(
    int memberId,
    String memberName,
    String paymentDate,
    int membershipTypeId,
    String membershipTypeName,
    BigDecimal amount
  ) {
    this.memberId = memberId;
    this.memberName = memberName;
    this.paymentDate = paymentDate;
    this.membershipTypeId = membershipTypeId;
    this.membershipTypeName = membershipTypeName;
    this.amount = amount;
  }

  @Override
  public String toString() {
    return (
      "Payment History(memberId=" +
      memberId +
      ", paymentDate=" +
      paymentDate +
      ", membershipTypeId=" +
      membershipTypeId +
      ", amount=" +
      amount +
      ")"
    );
  }

  public String toStringWithNames() {
    return (
      "Payment History(memberId=" +
      memberId +
      ", memberName=" +
      memberName +
      ", paymentDate=" +
      paymentDate +
      ", membershipTypeId=" +
      membershipTypeId +
      ", membershipTypeName=" +
      membershipTypeName +
      ", amount=" +
      amount +
      ")"
    );
  }

  public int getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(int paymentId) {
    this.paymentId = paymentId;
  }

  public int getMemberId() {
    return memberId;
  }

  public void setMemberId(int memberId) {
    this.memberId = memberId;
  }

  public String getMemberName() {
    return memberName;
  }

  public void setMemberName(String memberName) {
    this.memberName = memberName;
  }

  public String getPaymentDate() {
    return paymentDate;
  }

  public void setPaymentDate(String paymentDate) {
    this.paymentDate = paymentDate;
  }

  public int getMembershipTypeId() {
    return membershipTypeId;
  }

  public void setMembershipTypeId(int membershipTypeId) {
    this.membershipTypeId = membershipTypeId;
  }

  public String getMembershipTypeName() {
    return membershipTypeName;
  }

  public void setMembershipTypeName(String membershipTypeName) {
    this.membershipTypeName = membershipTypeName;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }
}
