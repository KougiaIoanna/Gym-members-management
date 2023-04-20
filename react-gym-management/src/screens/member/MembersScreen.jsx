import { Link } from "react-router-dom";
import Header from "../../components/Header";
import "../../styles/styles.css";
import React, { useEffect, useState } from "react";
import {
  APIRequestGetMembers,
  APIRequestUpdateStatus,
} from "../../services/member";
import { APIRequestGetMembershipTypeById } from "../../services/membershipType";
import {
  APIRequestGetLastPaymentByMemberId,
  APIRequestGetPaymentHistoryForMember,
} from "../../services/paymentHistory";

const MembersScreen = ({}) => {
  const [members, setMembers] = useState([]);
  const [expirationDates, setExpirationDates] = useState([]);
  const [selectedScreen, setSelectedScreen] = useState("member");

  useEffect(() => {
    async function fetchData() {
      const data = await APIRequestGetMembers();
      setMembers(data);
    }
    fetchData();
  }, []);

  useEffect(() => {
    async function fetchExpirationDates() {
      const dates = await Promise.all(
        members.map(async (member) => {
          const paymentHistory = await APIRequestGetPaymentHistoryForMember(
            member.memberId
          );
          if (paymentHistory.length > 0) {
            const membershipType = await APIRequestGetMembershipTypeById(
              member.membershipTypeId
            );
            const duration = membershipType.numberOfMonths;
            const lastPayment = await APIRequestGetLastPaymentByMemberId(
              member.memberId
            );
            const latestPaymentdate = lastPayment.paymentDate;
            const expirationDate = new Date(latestPaymentdate);
            expirationDate.setMonth(expirationDate.getMonth() + duration);
            console.log("GIA NA DWWW", expirationDate, new Date());
            if (expirationDate < new Date()) {
              //exei lhjei
              APIRequestUpdateStatus(member.memberId, "Inactive");
            }
            return {
              memberId: member.memberId,
              expirationDate: expirationDate,
            };
          } else {
            APIRequestUpdateStatus(member.memberId, "Inactive");
            return { memberId: member.memberId, expirationDate: "-" };
          }
        })
      );
      const mappedDates = dates.reduce((acc, date) => {
        acc[date.memberId] = date.expirationDate;
        return acc;
      }, {});
      console.log("MAPPED", mappedDates);
      setExpirationDates(mappedDates);
    }
    fetchExpirationDates();
  }, [members]);

  return (
    <div>
      <Header selectedScreen={selectedScreen} />
      <div className="button-container">
        <button className="button">
          <Link to="/add-member">New Member</Link>
        </button>
      </div>
      <div className="table-container">
        <table className="table">
          <thead>
            <tr>
              <th>Member ID</th>
              <th>Name</th>
              <th>Membership</th>
              <th>Email</th>
              <th>Birth Date</th>
              <th>Address</th>
              <th>Phone</th>
              <th>Join Date</th>
              <th>Status</th>
              <th>Until</th>
              <th>Pay</th>
              <th>Edit</th>
            </tr>
          </thead>
          <tbody>
            {members.map((member, index) => {
              return (
                <tr key={member.memberId}>
                  <td>{member.memberId}</td>
                  <td>{member.name}</td>
                  <td>{member.membershipTypeName}</td>
                  <td>{member.email}</td>
                  <td>{member.dateOfBirth}</td>
                  <td>{member.address}</td>
                  <td>{member.phone}</td>
                  <td>{member.joinDate}</td>
                  <td>{member.status}</td>
                  <td>
                    {expirationDates[member.memberId]
                      ? new Date(
                          expirationDates[member.memberId]
                        ).toLocaleDateString() === "Invalid Date"
                        ? "-"
                        : new Date(
                            expirationDates[member.memberId]
                          ).toLocaleDateString()
                      : "N/A"}
                  </td>
                  <td>
                    <Link to={`/add-payment/${member.memberId}`}>
                      <button>Pay</button>
                    </Link>
                  </td>
                  <td>
                    <Link to={`/edit-member/${member.memberId}`}>
                      <button>Edit</button>
                    </Link>
                  </td>
                </tr>
              );
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default MembersScreen;
