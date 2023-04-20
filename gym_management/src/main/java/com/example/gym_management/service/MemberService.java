package com.example.gym_management.service;

import com.example.gym_management.dao.MemberDAO;
import com.example.gym_management.model.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

  private MemberDAO memberDAO;

  @Autowired
  public MemberService(MemberDAO memberDAO) {
    this.memberDAO = memberDAO;
  }

  public List<Member> getMembers() {
    List<Member> members = memberDAO.getAllMembers();
    for (Member member : members) {
      System.out.println(member.toStringForMemberTable());
    }
    return members;
  }

  public Optional<Member> getMemberById(int memberId) {
    return memberDAO.getMemberById(memberId);
  }

  public Member addMember(Member member) {
    return memberDAO.addMember(member);
  }

  public Member updateMember(int id, Member member) {
    return memberDAO.updateMember(id, member);
  }

  public Member updateStatus(int id, String status) {
    return memberDAO.updateStatus(id, status);
  }

  public boolean deleteMember(int id) {
    if (memberDAO.deleteMember(id)) {
      return true;
    }
    return false;
  }
}
