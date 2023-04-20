import { useEffect, useState } from "react";
import Header from "../../components/Header";
import "../../styles/styles.css";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import {
  APIRequestGetPaymentHistoryById,
  APIRequestUpdatePayment,
} from "../../services/paymentHistory";
import { APIRequestGetMembershipTypeById } from "../../services/membershipType";

export default function EditMemberScreen() {
  const navigate = useNavigate();
  const { paymentId } = useParams();
  const [memberName, setMemberName] = useState("");
  const [memberId, setMemberId] = useState("");

  const [amount, setAmount] = useState("");
  const [paymentDate, setPaymentDate] = useState(
    new paymentDate().toISOString().slice(0, 10)
  );
  const [membershipTypeId, setMembershipTypeId] = useState("");
  const [membershipTypeName, setMembershipTypeName] = useState("");

  useEffect(() => {
    async function fetchData() {
      const ret = await APIRequestGetPaymentHistoryById(paymentId);
      setMemberName(ret.memberName);
      setMemberId(ret.memberId);
      setAmount(ret.amount);
      setPaymentDate(ret.paymentDate);
      setMembershipTypeId(ret.membershipTypeId);
      const res = await APIRequestGetMembershipTypeById(ret.membershipTypeId);
      setMembershipTypeName(res.name);
    }
    fetchData();
  }, [paymentId]);

  const handleSubmit = async (event) => {
    event.preventDefault();
    APIRequestUpdatePayment(
      paymentId,
      memberId,
      paymentDate,
      membershipTypeId,
      amount
    );
    navigate("/payment-history");
    window.location.reload();
  };

  return (
    <>
      <div>
        <Header />
        <form onSubmit={handleSubmit} className="add-payment-form">
          <div className="form-title-container">
            <div className="form-title">Edit Payment for {memberName}</div>
          </div>
          <label htmlFor="membershipTypeId">
            Membership Type: {membershipTypeName}
          </label>
          <label htmlFor="amount">Amount: {amount}€</label>
          <input
            id="paymentDate"
            type="paymentDate"
            value={new paymentDate().toISOString().slice(0, 10)}
            onChange={(event) => setPaymentDate(event.target.value)}
          />
          <button className="save-button" type="submit">
            Add Payment
          </button>
        </form>
      </div>
    </>
  );
}
