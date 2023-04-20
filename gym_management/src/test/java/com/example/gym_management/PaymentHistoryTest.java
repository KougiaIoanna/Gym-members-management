package com.example.gym_management;

import com.example.gym_management.model.PaymentHistory;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentHistoryTest {

  private PaymentHistory paymentHistory;

  @BeforeEach
  public void setUp() {
    // Create a new PaymentHistory object
    paymentHistory = new PaymentHistory();
    paymentHistory.setMemberId(1);
    paymentHistory.setMemberName("John Doe");
    paymentHistory.setPaymentDate("2023-02-02");
    paymentHistory.setMembershipTypeId(1);
    paymentHistory.setMembershipTypeName("Basic");
    paymentHistory.setAmount(new BigDecimal("50.00"));
  }

  @Test
  public void testGetMemberId() {
    int expected = 1;
    int actual = paymentHistory.getMemberId();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetMemberName() {
    String expected = "John Doe";
    String actual = paymentHistory.getMemberName();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetPaymentDate() {
    String expected = "2023-02-02";
    String actual = paymentHistory.getPaymentDate();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetMembershipTypeId() {
    int expected = 1;
    int actual = paymentHistory.getMembershipTypeId();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetMembershipTypeName() {
    String expected = "Basic";
    String actual = paymentHistory.getMembershipTypeName();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetAmount() {
    BigDecimal expected = new BigDecimal("50.00");
    BigDecimal actual = paymentHistory.getAmount();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSetMemberId() {
    int expected = 2;
    paymentHistory.setMemberId(expected);
    int actual = paymentHistory.getMemberId();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSetMemberName() {
    String expected = "Jane Smith";
    paymentHistory.setMemberName(expected);
    String actual = paymentHistory.getMemberName();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSetPaymentDate() {
    String expected = "2023-06-02";
    paymentHistory.setPaymentDate(expected);
    String actual = paymentHistory.getPaymentDate();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSetMembershipTypeId() {
    int expected = 2;
    paymentHistory.setMembershipTypeId(expected);
    int actual = paymentHistory.getMembershipTypeId();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSetMembershipTypeName() {
    String expected = "Premium";
    paymentHistory.setMembershipTypeName(expected);
    String actual = paymentHistory.getMembershipTypeName();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSetAmount() {
    BigDecimal expected = new BigDecimal("100.00");
    paymentHistory.setAmount(expected);
    BigDecimal actual = paymentHistory.getAmount();
    Assertions.assertEquals(expected, actual);
  }
}
