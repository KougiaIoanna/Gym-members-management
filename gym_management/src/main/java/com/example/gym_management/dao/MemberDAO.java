package com.example.gym_management.dao;

import com.example.gym_management.model.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDAO {
  List<Member> getAllMembers();
  Optional<Member> getMemberById(int memberId);
  Member addMember(Member member);
  Member updateMember(int memberId, Member member);
  boolean deleteMember(int memberId);
  Member updateStatus(int memberId, String status);
}
