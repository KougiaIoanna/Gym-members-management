package com.example.gym_management.dao;

import org.springframework.jdbc.core.RowMapper;

import com.example.gym_management.model.MembershipType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MembershipTypeRowMapper implements RowMapper<MembershipType> {

    @Override
    public MembershipType mapRow(ResultSet rs, int rowNum) throws SQLException {
        MembershipType membershipType = new MembershipType();
        membershipType.setMembershipTypeId(rs.getInt("membership_type_id"));
        membershipType.setName(rs.getString("name"));
        membershipType.setDescription(rs.getString("description"));
        membershipType.setCost(rs.getBigDecimal("cost"));
        membershipType.setNumberOfMonths(rs.getInt("number_of_months"));
        return membershipType;
    }
}
