package com.example.gym_management.dao;

import com.example.gym_management.model.PaymentHistory;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class PaymentHistoryRowMapper implements RowMapper<PaymentHistory> {

  @Override
  public PaymentHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
    PaymentHistory paymentHistory = new PaymentHistory();
    paymentHistory.setPaymentId(rs.getInt("payment_id"));
    paymentHistory.setMemberId(rs.getInt("member_id"));
    paymentHistory.setMembershipTypeId(rs.getInt("membership_type_id"));
    paymentHistory.setPaymentDate(rs.getString("payment_date"));
    paymentHistory.setAmount(rs.getBigDecimal("amount"));
    return paymentHistory;
  }
}
