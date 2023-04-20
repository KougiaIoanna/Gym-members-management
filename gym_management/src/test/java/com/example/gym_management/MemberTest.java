package com.example.gym_management;

import com.example.gym_management.model.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberTest {

  private Member member;

  @BeforeEach
  public void setUp() {
    // Set up member object before each test
    member =
      new Member(
        "John Doe",
        "john@doe.com",
        "6942563665",
        "Mpoumpoulinas 25, Ioannina",
        3,
        "2000-10-01",
        "2023-02-03",
        "active"
      );
  }

  //////////////////// GET TESTS //////////////////////////////////////

  @Test
  public void testGetName() {
    String expected = "John Doe";
    member.setName(expected);
    String actual = member.getName();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetEmail() {
    String expected = "john@doe.com";
    member.setEmail(expected);
    String actual = member.getEmail();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetPhone() {
    String expected = "1234567890";
    member.setPhone(expected);
    String actual = member.getPhone();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetAddress() {
    String expected = "Ethnikis Antistaseos 5, Ioannina";
    member.setAddress(expected);
    String actual = member.getAddress();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetMembershipTypeId() {
    int expected = 1;
    member.setMembershipTypeId(expected);
    int actual = member.getMembershipTypeId();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetDateOfBirth() {
    String expected = "1990-02-02";
    member.setDateOfBirth(expected);
    String actual = member.getDateOfBirth();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetJoinDate() {
    String expected = "2022-25-11";
    member.setJoinDate(expected);
    String actual = member.getJoinDate();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetStatus() {
    String expected = "Active";
    member.setStatus(expected);
    String actual = member.getStatus();
    Assertions.assertEquals(expected, actual);
  }

  /////////////////////// SET TESTS ////////////////////////////////////
  @Test
  public void testSetEmail() {
    member.setEmail("jane@doe.com");
    String expected = "jane@doe.com";
    String actual = member.getEmail();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSetPhone() {
    String expected = "1234567890";
    member.setPhone(expected);
    String actual = member.getPhone();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSetAddress() {
    String expected = "Ethnikis Antistaseos 5, Ioannina";
    member.setAddress(expected);
    String actual = member.getAddress();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSetMembershipTypeId() {
    int expected = 1;
    member.setMembershipTypeId(expected);
    int actual = member.getMembershipTypeId();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSetDateOfBirth() {
    String expected = "1990-01-01";
    member.setDateOfBirth(expected);
    String actual = member.getDateOfBirth();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSetJoinDate() {
    String expected = "2023-04-18";
    member.setJoinDate(expected);
    String actual = member.getJoinDate();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSetStatus() {
    String expected = "ACTIVE";
    member.setStatus(expected);
    String actual = member.getStatus();
    Assertions.assertEquals(expected, actual);
  }
}
