package com.example.gym_management;

import com.example.gym_management.model.MembershipType;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MembershipTypeTest {

  private MembershipType membershipType;

  @BeforeEach
  public void setUp() {
    membershipType =
      new MembershipType(
        1,
        "Basic",
        "Basic Membership",
        new BigDecimal("50.00"),
        1
      );
  }

  /////////////////////////////// GET TESTS ////////////////////////////////

  @Test
  public void testGetMembershipTypeId() {
    int expected = 1;
    membershipType.setMembershipTypeId(expected);
    int actual = membershipType.getMembershipTypeId();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetName() {
    String expected = "Basic";
    membershipType.setName(expected);
    String actual = membershipType.getName();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetDescription() {
    String expected = "Basic membership";
    membershipType.setDescription(expected);
    String actual = membershipType.getDescription();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetCost() {
    BigDecimal expected = new BigDecimal("50.00");
    membershipType.setCost(expected);
    BigDecimal actual = membershipType.getCost();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testGetNumberOfMonths() {
    int expected = 1;
    membershipType.setNumberOfMonths(expected);
    int actual = membershipType.getNumberOfMonths();
    Assertions.assertEquals(expected, actual);
  }

  ////////////////////////// SET TESTS ////////////////////////////

  @Test
  public void testSetName() {
    String expected = "Premium";
    membershipType.setName(expected);
    String actual = membershipType.getName();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSetDescription() {
    String expected = "Premium membership";
    membershipType.setDescription(expected);
    String actual = membershipType.getDescription();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSetCost() {
    BigDecimal expected = new BigDecimal("100.00");
    membershipType.setCost(expected);
    BigDecimal actual = membershipType.getCost();
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testSetNumberOfMonths() {
    int expected = 3;
    membershipType.setNumberOfMonths(expected);
    int actual = membershipType.getNumberOfMonths();
    Assertions.assertEquals(expected, actual);
  }
}
