import { useState } from "react";
import Header from "../../components/Header";
import "../../styles/styles.css";
import { APIRequestPostMembershipType } from "../../services/membershipType";
import { useNavigate } from "react-router-dom";

export default function AddMembershipTypeScreen() {
  const [name, setName] = useState("");
  const [cost, setCost] = useState("");
  const [description, setDescription] = useState("");
  const [numberOfMonths, setNumberOfMonths] = useState();
  const navigate = useNavigate();

  const handleSubmit = (event) => {
    event.preventDefault();
    APIRequestPostMembershipType(name, description, cost, numberOfMonths);
    navigate("/membership-types");
    window.location.reload();
  };

  return (
    <>
      <div>
        <Header />
        <form onSubmit={handleSubmit} className="add-member-form">
          <div className="form-title-container">
            <div className="form-title">New Membership Type</div>
          </div>

          <label className="one-column-form-label" htmlFor="name">
            Name:
          </label>
          <input
            className="one-column-form-input"
            id="name"
            type="text"
            value={name}
            onChange={(event) => setName(event.target.value)}
          />

          <label className="one-column-form-label" htmlFor="email">
            Description:
          </label>
          <input
            className="one-column-form-input"
            id="description"
            type="text"
            value={description}
            onChange={(event) => setDescription(event.target.value)}
          />

          <label className="one-column-form-label" htmlFor="email">
            Duration (in months):
          </label>
          <input
            className="one-column-form-input"
            id="numberOfMonths"
            type="int"
            value={numberOfMonths}
            onChange={(event) => setNumberOfMonths(event.target.value)}
          />

          <label className="one-column-form-label" htmlFor="address">
            Cost:
          </label>
          <input
            className="one-column-form-input"
            id="cost"
            type="number"
            value={cost}
            onChange={(event) => setCost(event.target.value)}
          />

          <button className="save-button" type="submit">
            Save
          </button>
        </form>
      </div>
    </>
  );
}
