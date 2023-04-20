package com.example.gym_management.dao;

import com.example.gym_management.model.PaymentHistory;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentHistoryDAOImpl implements PaymentHistoryDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public List<PaymentHistory> getAllPaymentHistory() {
    // String sql = "SELECT * FROM payment_history";
    String sql =
      "SELECT payment_history.payment_id, payment_history.member_id," +
      " members.name AS member_name, payment_history.membership_type_id," +
      " membership_types.name AS membership_type_name, payment_history.amount," +
      " payment_history.payment_date" +
      " FROM payment_history" +
      " JOIN members ON payment_history.member_id = members.member_id" +
      " JOIN membership_types ON payment_history.membership_type_id = membership_types.membership_type_id;";
    return jdbcTemplate.query(sql, new PaymentHistoryRowMapperWithNames());
  }

  @Override
  public List<PaymentHistory> getPaymentHistoryByMemberId(int memberId) {
    String sql = "SELECT * FROM payment_history WHERE member_id = ?";
    return jdbcTemplate.query(sql, new PaymentHistoryRowMapper(), memberId);
  }

  @Override
  public Optional<PaymentHistory> getPaymentHistoryById(int paymentId) {
    String sql = "SELECT * FROM payment_history WHERE payment_id=?";
    return jdbcTemplate
      .query(sql, new PaymentHistoryRowMapper(), paymentId)
      .stream()
      .findFirst();
  }

  @Override
  public PaymentHistory addPaymentHistory(PaymentHistory paymentHistory) {
    String sql =
      "INSERT INTO payment_history (member_id, payment_date, membership_type_id, amount) VALUES (?, ?, ?, ?)";
    int rows = jdbcTemplate.update(
      sql,
      paymentHistory.getMemberId(),
      paymentHistory.getPaymentDate(),
      paymentHistory.getMembershipTypeId(),
      paymentHistory.getAmount()
    );
    if (rows > 0) {
      return paymentHistory;
    }
    return null;
  }

  @Override
  public PaymentHistory updatePaymentHistory(PaymentHistory paymentHistory) {
    String sql =
      "UPDATE payment_history SET member_id = ?, payment_date = ?, amount = ? WHERE payment_id = ?";
    jdbcTemplate.update(
      sql,
      paymentHistory.getMemberId(),
      paymentHistory.getPaymentDate(),
      paymentHistory.getAmount(),
      paymentHistory.getPaymentId()
    );
    return getPaymentHistoryById(paymentHistory.getPaymentId()).orElse(null);
  }

  @Override
  public boolean deletePaymentHistory(int paymentId) {
    String sql = "DELETE FROM payment_history WHERE payment_id = ?";
    Object[] params = { paymentId };
    int numRowsAffected = jdbcTemplate.update(sql, params);
    return numRowsAffected == 1;
  }

  @Override
  public Optional<PaymentHistory> getLastPaymentByMemberId(int memberId) {
    String sql2 =
      "SELECT * FROM payment_history WHERE member_id = ? ORDER BY payment_date DESC LIMIT 1;";
    return jdbcTemplate
      .query(sql2, new PaymentHistoryRowMapper(), memberId)
      .stream()
      .findFirst();
  }
}
