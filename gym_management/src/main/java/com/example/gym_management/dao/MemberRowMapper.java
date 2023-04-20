package com.example.gym_management.dao;

import com.example.gym_management.model.Member;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class MemberRowMapper implements RowMapper<Member> {

  @Override
  public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
    Member member = new Member();
    member.setMemberId(rs.getInt("member_id"));
    member.setName(rs.getString("name"));
    member.setEmail(rs.getString("email"));
    member.setPhone(rs.getString("phone"));
    member.setAddress(rs.getString("address"));
    member.setDateOfBirth(rs.getString("date_of_birth"));
    member.setMembershipTypeId(rs.getInt("membership_type_id"));
    member.setJoinDate(rs.getString("join_date"));
    member.setStatus(rs.getString("status"));

    return member;
  }
}
