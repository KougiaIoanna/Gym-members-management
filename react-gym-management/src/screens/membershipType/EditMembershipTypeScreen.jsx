import { useEffect, useState } from "react";
import Header from "../../components/Header";
import "../../styles/styles.css";
import {
  APIRequestDeleteMembershipType,
  APIRequestGetMembershipTypeById,
  APIRequestUpdateMembershipType,
} from "../../services/membershipType";
import { useNavigate } from "react-router-dom";
import { useParams } from "react-router-dom";

export default function EditMembershipTypeScreen() {
  const [name, setName] = useState("");
  const [cost, setCost] = useState("");
  const [description, setDescription] = useState("");
  const [membershipType, setMembershipType] = useState(null);
  const navigate = useNavigate();
  const { membershipTypeId } = useParams();
  const [numberOfMonths, setNumberOfMonths] = useState();

  useEffect(() => {
    async function fetchData() {
      const ret = await APIRequestGetMembershipTypeById(membershipTypeId);
      console.log("DIEEEEEEEEEEEEE BIATCH", ret);

      setMembershipType(ret);
      setName(ret.name);
      setDescription(ret.description);
      setCost(ret.cost);
      setNumberOfMonths(ret.numberOfMonths);
    }
    fetchData();
  }, [membershipTypeId]);

  const handleSubmit = (event) => {
    event.preventDefault();

    APIRequestUpdateMembershipType(
      membershipTypeId,
      name,
      description,
      cost,
      numberOfMonths
    );
    navigate("/membership-types");
    window.location.reload();
  };
  if (!membershipType) {
    return <div>Loading...</div>;
  }

  const handleDelete = async (membershipTypeId) => {
    APIRequestDeleteMembershipType(membershipTypeId);
  };

  return (
    <>
      <div>
        <Header />
        <form onSubmit={handleSubmit} className="add-member-form">
          <div className="form-title-container">
            <div className="form-title">Edit Membership Type</div>
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

          <div className="button-container">
            <button
              onClick={() => handleDelete(membershipTypeId)}
              className="delete-button"
            >
              Delete Membership Type
            </button>
            <button className="save-button" type="submit">
              Save changes
            </button>
          </div>
        </form>
      </div>
    </>
  );
}
