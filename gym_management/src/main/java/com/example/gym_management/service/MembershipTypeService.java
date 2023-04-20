package com.example.gym_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gym_management.dao.MembershipTypeDAO;
import com.example.gym_management.model.MembershipType;

@Service
public class MembershipTypeService {
	
	private MembershipTypeDAO membershipTypeDAO;

	@Autowired
    public MembershipTypeService(MembershipTypeDAO membershipTypeDAO) {
        this.membershipTypeDAO = membershipTypeDAO;
    }


    public List<MembershipType> getMembershipTypes(){
		List<MembershipType> membershipTypes = membershipTypeDAO.getAllMembershipTypes();
		return membershipTypes;
	}

	public Optional<MembershipType> getMembershipTypeById(int membershipTypeId) {
        return membershipTypeDAO.getMembershipTypeById(membershipTypeId);
    }

    public MembershipType addMembershipType(MembershipType membershipType) {
        return membershipTypeDAO.addMembershipType(membershipType);
    }

    public MembershipType updateMembershipType(int id, MembershipType membershipType) {
        return membershipTypeDAO.updateMembershipType(id, membershipType);
    }

    public boolean deleteMembershipType(int id) {
        if (membershipTypeDAO.deleteMembershipType(id)){
            return true;
        }
        return false;
       
    }
}