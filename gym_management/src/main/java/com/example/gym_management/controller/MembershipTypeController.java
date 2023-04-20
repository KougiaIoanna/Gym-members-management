package com.example.gym_management.controller;

import com.example.gym_management.model.MembershipType;
import com.example.gym_management.service.MembershipTypeService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/membership-type")
@CrossOrigin(origins = "http://localhost:3000")
public class MembershipTypeController {

  @Autowired
  private MembershipTypeService membershipTypeService;

  @GetMapping
  public List<MembershipType> getAllMembershipTypes() {
    return membershipTypeService.getMembershipTypes();
  }

  @GetMapping("/{id}")
  @CrossOrigin(origins = "http://localhost:3000")
  public ResponseEntity<MembershipType> getMembershipTypeById(
    @PathVariable int id
  ) {
    Optional<MembershipType> membershipType = membershipTypeService.getMembershipTypeById(
      id
    );
    if (membershipType.isPresent()) {
      return ResponseEntity.ok(membershipType.get());
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<MembershipType> addMembershipType(
    @RequestBody MembershipType membershipType
  ) {
    return ResponseEntity
      .status(HttpStatus.CREATED)
      .body(membershipTypeService.addMembershipType(membershipType));
  }

  @PutMapping("/{membershipTypeId}")
  public ResponseEntity<MembershipType> updateMembershipType(
    @PathVariable int membershipTypeId,
    @RequestBody MembershipType membershipType
  ) {
    Optional<MembershipType> existingMembershipType = membershipTypeService.getMembershipTypeById(
      membershipTypeId
    );

    if (existingMembershipType.isPresent()) {
      membershipType.setMembershipTypeId(membershipTypeId);
      membershipTypeService.updateMembershipType(
        membershipTypeId,
        membershipType
      );
      return ResponseEntity.ok(membershipType);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteMembershipType(@PathVariable int id) {
    Optional<MembershipType> existingMembershipType = membershipTypeService.getMembershipTypeById(
      id
    );
    if (existingMembershipType.isPresent()) {
      membershipTypeService.deleteMembershipType(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
