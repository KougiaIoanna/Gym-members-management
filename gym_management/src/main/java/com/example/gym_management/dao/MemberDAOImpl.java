package com.example.gym_management.dao;

import com.example.gym_management.model.Member;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public MemberDAOImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<Member> getAllMembers() {
    try {
      String sql =
        "SELECT  members.member_id, members.name, members.email, members.phone, members.address, members.membership_type_id, " +
        "membership_types.name AS membership_type_name, members.date_of_birth, " +
        "members.join_date, members.status " +
        "FROM members " +
        "JOIN membership_types " +
        "ON members.membership_type_id = membership_types.membership_type_id;";
      //String sql = "SELECT * FROM members";

      RowMapper<Member> rowMapper = new MemberRowMapperWithMembershipTypeName();
      return jdbcTemplate.query(sql, rowMapper);
    } catch (DataAccessException ex) {
      ex.printStackTrace();
      throw new RuntimeException("Failed to get members " + ex);
    }
  }

  @Override
  public Optional<Member> getMemberById(int memberId) {
    try {
      String sql = "SELECT * FROM members WHERE member_id = ?";
      return jdbcTemplate
        .query(sql, new MemberRowMapper(), memberId)
        .stream()
        .findFirst();
    } catch (DataAccessException ex) {
      ex.printStackTrace();
      throw new RuntimeException(
        "Failed to update member with id: " + memberId,
        ex
      );
    }
  }

  @Override
  public Member addMember(Member member) {
    try {
      String sql =
        "INSERT INTO members (name, email, phone, address, membership_type_id, date_of_birth, join_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
      KeyHolder keyHolder = new GeneratedKeyHolder();
      jdbcTemplate.update(
        connection -> {
          PreparedStatement ps = connection.prepareStatement(
            sql,
            Statement.RETURN_GENERATED_KEYS
          );
          ps.setString(1, member.getName());
          ps.setString(2, member.getEmail());
          ps.setString(3, member.getPhone());
          ps.setString(4, member.getAddress());
          ps.setInt(5, member.getMembershipTypeId());
          ps.setDate(6, Date.valueOf(member.getDateOfBirth()));
          ps.setDate(7, Date.valueOf(member.getJoinDate()));
          ps.setString(8, member.getStatus());
          return ps;
        },
        keyHolder
      );

      int memberId = Objects.requireNonNull(keyHolder.getKey()).intValue();
      member.setMemberId(memberId);
      return member;
    } catch (DataAccessException ex) {
      ex.printStackTrace();
      throw new RuntimeException("Failed to add member: " + ex);
    }
  }

  @Override
  public Member updateMember(int memberId, Member member) {
    try {
      String sql =
        "UPDATE members SET name=?, email=?, phone=?, address=?, membership_type_id=?, date_of_birth=?, join_date=?, status=? WHERE member_id=?";

      int updateCount = jdbcTemplate.update(
        sql,
        member.getName(),
        member.getEmail(),
        member.getPhone(),
        member.getAddress(),
        member.getMembershipTypeId(),
        member.getDateOfBirth(),
        member.getJoinDate(),
        member.getStatus(),
        memberId
      );
      if (updateCount == 1) {
        return getMemberById(member.getMemberId()).orElse(null);
      } else {
        return null;
      }
    } catch (DataAccessException ex) {
      ex.printStackTrace();
      throw new RuntimeException(
        "Failed to update member with id: " + memberId,
        ex
      );
    }
  }

  @Override
  public boolean deleteMember(int id) {
    try {
      String sql = "DELETE FROM members WHERE member_id=?";
      int rowsAffected = jdbcTemplate.update(sql, id);
      return rowsAffected > 0;
    } catch (DataAccessException ex) {
      ex.printStackTrace();
      throw new RuntimeException("Failed to delete member with id: " + id, ex);
    }
  }

  @Override
  public Member updateStatus(int id, String status) {
    if (status.startsWith("\"") && status.endsWith("\"")) {
      status = status.substring(1, status.length() - 1);
    }
    status = status.replace("\"", "");

    try {
      String sql = "UPDATE members SET status =? WHERE member_id=?";
      int updateCount = jdbcTemplate.update(sql, status, id);
      if (updateCount == 1) {
        return getMemberById(id).orElse(null);
      } else {
        return null;
      }
    } catch (DataAccessException ex) {
      ex.printStackTrace();
      throw new RuntimeException(
        "Failed to update status with memberid: " + id,
        ex
      );
    }
  }
}
