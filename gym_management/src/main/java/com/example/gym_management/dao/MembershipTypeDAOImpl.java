package com.example.gym_management.dao;

import com.example.gym_management.model.MembershipType;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class MembershipTypeDAOImpl implements MembershipTypeDAO {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public MembershipTypeDAOImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<MembershipType> getAllMembershipTypes() {
    String sql = "SELECT * FROM membership_types";
    return jdbcTemplate.query(sql, new MembershipTypeRowMapper());
  }

  @Override
  public Optional<MembershipType> getMembershipTypeById(int membershipTypeId) {
    String sql = "SELECT * FROM membership_types WHERE membership_type_id = ?";
    return jdbcTemplate
      .query(sql, new MembershipTypeRowMapper(), membershipTypeId)
      .stream()
      .findFirst();
  }

  @Override
  public MembershipType addMembershipType(MembershipType membershipType) {
    String sql =
      "INSERT INTO membership_types (name, description, cost, number_of_months) VALUES (?, ?, ?, ?)";
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(
      connection -> {
        PreparedStatement statement = connection.prepareStatement(
          sql,
          Statement.RETURN_GENERATED_KEYS
        );
        statement.setString(1, membershipType.getName());
        statement.setString(2, membershipType.getDescription());
        statement.setBigDecimal(3, membershipType.getCost());
        statement.setInt(4, membershipType.getNumberOfMonths());
        return statement;
      },
      keyHolder
    );
    membershipType.setMembershipTypeId(keyHolder.getKey().intValue());
    return membershipType;
  }

  @Override
  public MembershipType updateMembershipType(
    int membershipTypeId,
    MembershipType membershipType
  ) {
    String sql =
      "UPDATE membership_types SET name = ?, description = ?, cost = ?, number_of_months = ? WHERE membership_type_id = ?";
    //KeyHolder keyHolder = new GeneratedKeyHolder();
    //SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(membershipType);
    jdbcTemplate.update(
      sql,
      membershipType.getName(),
      membershipType.getDescription(),
      membershipType.getCost(),
      membershipType.getNumberOfMonths(),
      membershipTypeId
    );

    return membershipType;
  }

  @Override
  public boolean deleteMembershipType(int membershipTypeId) {
    String sql = "DELETE FROM membership_types WHERE membership_type_id = ?";
    int rowsAffected = jdbcTemplate.update(sql, membershipTypeId);
    return rowsAffected > 0;
  }
}
