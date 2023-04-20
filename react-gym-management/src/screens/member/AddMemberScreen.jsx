import { useState } from "react";
import Header from "../../components/Header";
import "../../styles/styles.css";
import { APIRequestPostMember } from "../../services/member";
import { useNavigate } from "react-router-dom";
import SelectMembershipType from "../../components/SelectMembershipType";

export default function AddMemberScreen() {
  const [name, setName] = useState("");
  const [address, setAddress] = useState("");
  const [phone, setPhone] = useState("");
  const [email, setEmail] = useState("");
  const [status, setStatus] = useState("Inactive");
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [joinDate, setJoinDate] = useState("");
  const [membershipTypeId, setMembershipTypeId] = useState("");
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    if (
      !name ||
      !email ||
      !phone ||
      !address ||
      !membershipTypeId ||
      !dateOfBirth ||
      !joinDate ||
      !status
    ) {
      alert("Please fill in all fields");
      return;
    }
    APIRequestPostMember(
      name,
      email,
      phone,
      address,
      membershipTypeId,
      dateOfBirth,
      joinDate,
      status
    );

    navigate("/members");
    window.location.reload();
  };

  function handleMembershipChange(event) {
    setMembershipTypeId(event.target.value);
  }

  return (
    <div>
      <Header />
      <form onSubmit={handleSubmit} className="add-member-form">
        <div className="form-title-container">
          <div className="form-title">New Member</div>
        </div>
        <div className="form-column-container">
          <div className="form-column">
            <label className="two-column-form-label" htmlFor="name">
              Name:
            </label>
            <input
              className="two-column-form-input"
              id="name"
              type="text"
              value={name}
              onChange={(event) => setName(event.target.value)}
            />

            <label className="two-column-form-label" htmlFor="email">
              Email:
            </label>
            <input
              className="two-column-form-input"
              id="email"
              type="email"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
            />

            <label className="two-column-form-label" htmlFor="address">
              Address:
            </label>
            <input
              className="two-column-form-input"
              id="address"
              type="text"
              value={address}
              onChange={(event) => setAddress(event.target.value)}
            />

            <label className="two-column-form-label" htmlFor="phone">
              Phone:
            </label>
            <input
              className="two-column-form-input"
              id="phone"
              type="text"
              value={phone}
              onChange={(event) => setPhone(event.target.value)}
            />
          </div>
          <div className="form-column">
            <label className="two-column-form-label" htmlFor="dateOfBirth">
              Date of birth:
            </label>
            <input
              className="two-column-form-input"
              id="dateOfBirth"
              type="date"
              value={dateOfBirth}
              onChange={(event) => setDateOfBirth(event.target.value)}
            />

            <label className="two-column-form-label" htmlFor="joinDate">
              Join Date:
            </label>
            <input
              className="two-column-form-input"
              id="joinDate"
              type="date"
              value={joinDate}
              onChange={(event) => setJoinDate(event.target.value)}
            />

            <label className="two-column-form-label" htmlFor="membershipTypeId">
              Membership Type:
            </label>
            <SelectMembershipType onChange={handleMembershipChange} />

            <div className="checkbox-container">
              <label className="checkbox-label" htmlFor="status">
                Add payment:
              </label>
              <input
                className="checkboxx"
                id="status"
                type="checkbox"
                checked={status === "Active"}
                onChange={(event) => {
                  setStatus(event.target.checked ? "Active" : "Inactive");
                }}
              />
            </div>
          </div>
        </div>

        <button className="save-button" type="submit">
          Save
        </button>
      </form>
    </div>
  );
}
