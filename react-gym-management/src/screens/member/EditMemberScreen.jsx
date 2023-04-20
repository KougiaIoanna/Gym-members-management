import { useEffect, useState } from "react";
import Header from "../../components/Header";
import "../../styles/styles.css";
import {
  APIRequestDeleteMember,
  APIRequestGetMemberById,
  APIRequestUpdateMember,
} from "../../services/member";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";
import SelectMembershipType from "../../components/SelectMembershipType";

export default function EditMemberScreen() {
  const [member, setMember] = useState(null);
  const navigate = useNavigate();
  const { memberId } = useParams();
  const [name, setName] = useState("");
  const [address, setAddress] = useState("");
  const [phone, setPhone] = useState("");
  const [email, setEmail] = useState("");
  const [status, setStatus] = useState("");
  const [dateOfBirth, setDateOfBirth] = useState("");
  const [joinDate, setJoinDate] = useState("");
  const [membershipTypeId, setMembershipTypeId] = useState("");

  useEffect(() => {
    async function fetchData() {
      const ret = await APIRequestGetMemberById(memberId);
      setMember(ret);
      setName(ret.name);
      setAddress(ret.address);
      setEmail(ret.email);
      setPhone(ret.phone);
      setStatus(ret.status);
      setDateOfBirth(ret.dateOfBirth);
      setJoinDate(ret.joinDate);
      setMembershipTypeId(ret.membershipTypeId);
    }
    fetchData();
  }, [memberId]);

  const handleSubmit = async (event) => {
    event.preventDefault();
    APIRequestUpdateMember(
      memberId,
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

  const handleDelete = async (memberId) => {
    APIRequestDeleteMember(memberId);
  };

  if (!member) {
    return <div>Loading...</div>;
  }

  function handleMembershipChange(event) {
    setMembershipTypeId(event.target.value);
  }

  return (
    <div>
      <Header />
      <form onSubmit={handleSubmit} className="add-member-form">
        <div className="form-title-container">
          <div className="form-title">Member Information</div>
        </div>
        <div className="form-column-container">
          <div className="form-column">
            <label className="two-column-form-label" htmlFor="name">
              Name:
            </label>
            <input
              id="name"
              className="two-column-form-input"
              type="text"
              value={name}
              onChange={(event) => {
                setName(event.target.value);
              }}
            />

            <label className="two-column-form-label" htmlFor="email">
              Email:
            </label>
            <input
              id="email"
              className="two-column-form-input"
              type="email"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
            />

            <label className="two-column-form-label" htmlFor="address">
              Address:
            </label>
            <input
              id="address"
              className="two-column-form-input"
              type="text"
              value={address}
              onChange={(event) => setAddress(event.target.value)}
            />

            <label className="two-column-form-label" htmlFor="phone">
              Phone:
            </label>
            <input
              id="phone"
              className="two-column-form-input"
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
            <SelectMembershipType
              defaultValue={membershipTypeId}
              value={membershipTypeId}
              onChange={handleMembershipChange}
            />
            <label className="two-column-form-label" htmlFor="status">
              Status:
            </label>
            <input
              id="status"
              className="two-column-form-input"
              type="text"
              value={status}
              onChange={(event) => setStatus(event.target.value)}
            />
          </div>
        </div>
        <div className="button-container">
          <button
            onClick={() => handleDelete(memberId)}
            className="delete-button"
          >
            Delete Member
          </button>
          <button className="save-button" type="submit">
            Save changes
          </button>
        </div>
      </form>
    </div>
  );
}
