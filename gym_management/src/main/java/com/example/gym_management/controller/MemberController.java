package com.example.gym_management.controller;

import com.example.gym_management.model.Member;
import com.example.gym_management.service.MemberService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/member")
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {

  private final MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping
  public List<Member> getMembers() {
    return memberService.getMembers();
  }

  @GetMapping("/{memberId}")
  public ResponseEntity<Member> getMemberById(@PathVariable int memberId) {
    Optional<Member> member = memberService.getMemberById(memberId);
    System.out.println("This is member by id:" + member);
    if (member.isPresent()) {
      return ResponseEntity.ok(member.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Member> addMember(@RequestBody Member member) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(memberService.addMember(member));
  }

  @PutMapping("/{memberId}")
  public ResponseEntity<Member> updateMember(
    @PathVariable int memberId,
    @RequestBody Member member
  ) {
    member.setMemberId(memberId);
    Member updatedMember = memberService.updateMember(memberId, member);
    if (updatedMember != null) {
      return ResponseEntity.ok(updatedMember);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{memberId}")
  public ResponseEntity<Void> deleteMember(@PathVariable int memberId) {
    boolean deleted = memberService.deleteMember(memberId);
    if (deleted) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PatchMapping("/{memberId}/status")
  public ResponseEntity<Member> updateStatus(
    @PathVariable int memberId,
    @RequestBody String status
  ) {
    Member updatedMember = memberService.updateStatus(memberId, status);
    if (updatedMember != null) {
      return ResponseEntity.ok(updatedMember);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
