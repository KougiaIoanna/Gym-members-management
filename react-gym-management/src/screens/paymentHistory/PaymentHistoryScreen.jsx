import { useNavigate } from "react-router-dom";
import Header from "../../components/Header";
import { useEffect, useState } from "react";
import {
  APIRequestDeletePayment,
  APIRequestGetPaymentHistory,
} from "../../services/paymentHistory";

export default function PaymentHistoryScreen({}) {
  const [payments, setPayments] = useState([]);
  const navigate = useNavigate();
  const [selectedScreen, setSelectedScreen] = useState("payment-history");

  useEffect(() => {
    async function fetchData() {
      const data = await APIRequestGetPaymentHistory();
      setPayments(data);
    }
    fetchData();
  }, [payments]);

  const handleDelete = (paymentId, memberId) => {
    APIRequestDeletePayment(paymentId, memberId);
    navigate("/payment-history");
    window.location.reload();
  };

  return (
    <>
      <Header selectedScreen={selectedScreen} />
      <div className="table-container">
        <table className="table">
          <thead>
            <tr>
              <th>Payment ID</th>
              <th>Date</th>
              <th>Member name</th>
              <th>Membership Type</th>
              <th>Amount</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            {payments.map((payment) => (
              <tr key={payment.paymentId}>
                <td>{payment.paymentId}</td>
                <td>{payment.paymentDate}</td>
                <td>{payment.memberName}</td>
                <td>{payment.membershipTypeName}</td>
                <td>{payment.amount}€</td>

                <td>
                  <button
                    onClick={() => {
                      handleDelete(payment.paymentId, payment.memberId);
                    }}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </>
  );
}
