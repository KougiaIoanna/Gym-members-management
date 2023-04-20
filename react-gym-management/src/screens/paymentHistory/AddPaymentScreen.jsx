import { useEffect, useState } from "react";
import Header from "../../components/Header";
import { APIRequestPostPayment } from "../../services/paymentHistory";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import { APIRequestGetMemberById } from "../../services/member";
import { APIRequestGetMembershipTypeById } from "../../services/membershipType";

export default function AddPaymentScreen() {
  const [memberName, setMemberName] = useState("");
  const [membershipTypeId, setMembershipTypeId] = useState("");
  const [membershipTypeName, setMembershipTypeName] = useState("");

  const [amount, setAmount] = useState("");
  const [date, setDate] = useState(new Date().toISOString().slice(0, 10));
  const navigate = useNavigate();
  const { memberId } = useParams();
  //const memberId = useParams().memberId;
  useEffect(() => {
    async function fetchData() {
      const m = memberId;
      const data = await APIRequestGetMemberById(m);
      setMemberName(data.name);
      setMembershipTypeId(data.membershipTypeId);
      const res = await APIRequestGetMembershipTypeById(data.membershipTypeId);
      setMembershipTypeName(res.name);
      setAmount(res.cost);
    }

    fetchData();
  }, [memberId]);

  const handleSubmit = (event) => {
    event.preventDefault();
    APIRequestPostPayment(memberId, date, membershipTypeId, amount);
    navigate("/payment-history");
    window.location.reload();
  };
  return (
    <>
      <div>
        <Header />

        <form onSubmit={handleSubmit} className="add-payment-form">
          <div className="form-title-container">
            <div className="form-title">Add Payment for {memberName}</div>
          </div>
          <label htmlFor="membershipTypeId">
            Membership Type: {membershipTypeName}
          </label>
          <label htmlFor="amount">Amount: {amount}€</label>

          <input
            id="date"
            type="date"
            value={date}
            onChange={(event) => setDate(event.target.value)}
          />

          <button className="save-button" type="submit">
            Add Payment
          </button>
        </form>
      </div>
    </>
  );
}
