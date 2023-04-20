package com.example.gym_management.dao;

import java.util.List;
import java.util.Optional;

import com.example.gym_management.model.MembershipType;

public interface MembershipTypeDAO {

    List<MembershipType> getAllMembershipTypes();

    Optional<MembershipType> getMembershipTypeById(int id);

    MembershipType addMembershipType(MembershipType membershipType);

    MembershipType updateMembershipType(int membershipTypeId, MembershipType membershipType);

    boolean deleteMembershipType(int id);
}
