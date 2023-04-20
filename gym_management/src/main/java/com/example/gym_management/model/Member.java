package com.example.gym_management.model;

public class Member {

  private int memberId;
  private String name;
  private String email;
  private String phone;
  private String address;
  private int membershipTypeId;
  private String membershipTypeName;
  private String dateOfBirth;
  private String joinDate;
  private String status;

  public Member(int memberId, String name) {
    this.memberId = memberId;
    this.name = name;
  }

  public Member() {}

  public Member(
    int memberId,
    String name,
    String email,
    String phone,
    String address,
    String dateOfBirth,
    int membershipTypeId,
    String membershipTypeName,
    String joinDate,
    String status
  ) {
    this.memberId = memberId;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.membershipTypeId = membershipTypeId;
    this.membershipTypeName = membershipTypeName;
    this.dateOfBirth = dateOfBirth;
    this.joinDate = joinDate;
    this.status = status;
  }

  public Member(
    String name,
    String email,
    String phone,
    String address,
    int membershipTypeId,
    //String membershipTypeName,
    String dateOfBirth,
    String joinDate,
    String status
  ) {
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.membershipTypeId = membershipTypeId;
    //this.membershipTypeName = membershipTypeName;
    this.dateOfBirth = dateOfBirth;
    this.joinDate = joinDate;
    this.status = status;
  }

  public Member(
    int memberId,
    String name,
    String email,
    String phone,
    String address,
    int membershipTypeId,
    String dateOfBirth,
    String joinDate,
    String status
  ) {
    this.memberId = memberId;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.membershipTypeId = membershipTypeId;
    this.dateOfBirth = dateOfBirth;
    this.joinDate = joinDate;
    this.status = status;
  }

  public String toStringForMemberTable() {
    return (
      "Member(id=" +
      memberId +
      ", name=" +
      name +
      ", email=" +
      email +
      ", phone=" +
      phone +
      ", address=" +
      address +
      ", membershipTypeId=" +
      membershipTypeId +
      ", membershipTypeName=" +
      membershipTypeName +
      ", dateOfBirth=" +
      dateOfBirth +
      ", joinDate=" +
      joinDate +
      ", status=" +
      status +
      ")"
    );
  }

  @Override
  public String toString() {
    return (
      "Member(id=" +
      memberId +
      ", name=" +
      name +
      ", email=" +
      email +
      ", phone=" +
      phone +
      ", address=" +
      address +
      ", membershipTypeId=" +
      membershipTypeId +
      ", dateOfBirth=" +
      dateOfBirth +
      ", joinDate=" +
      joinDate +
      ", status=" +
      status +
      ")"
    );
  }

  public int getMemberId() {
    return memberId;
  }

  public void setMemberId(int memberId) {
    this.memberId = memberId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getMembershipTypeId() {
    return membershipTypeId;
  }

  public void setMembershipTypeId(int membershipTypeId) {
    this.membershipTypeId = membershipTypeId;
  }

  public String getMembershipTypeName() {
    return membershipTypeName;
  }

  public void setMembershipTypeName(String membershipTypeName) {
    this.membershipTypeName = membershipTypeName;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getJoinDate() {
    return joinDate;
  }

  public void setJoinDate(String joinDate) {
    this.joinDate = joinDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
