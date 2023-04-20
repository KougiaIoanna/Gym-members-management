package com.example.gym_management.model;

import java.math.BigDecimal;

public class MembershipType {
    private int membershipTypeId;
    private String name;
    private String description;
    private BigDecimal cost;
    private int numberOfMonths;

    public MembershipType(int membershipTypeId, String name, String description, BigDecimal cost, int numberOfMonths){
        this.membershipTypeId = membershipTypeId;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.numberOfMonths=numberOfMonths;
    }

    public MembershipType(){
    }

    public MembershipType( String name, String description, BigDecimal cost, int numberOfMonths){
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.numberOfMonths = numberOfMonths;
    }


    @Override
    public String toString() {
        return "MembershipType(id=" + membershipTypeId + ", name=" + name + ", description=" + description + ", cost="+cost+ ", numberOfMonths="+numberOfMonths+ ")";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMembershipTypeId() {
        return membershipTypeId;
    }

    public void setMembershipTypeId(int membershipTypeId) {
        this.membershipTypeId = membershipTypeId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getNumberOfMonths() {
        return numberOfMonths;
    }

    public void setNumberOfMonths(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }


}
