import { Link } from "react-router-dom";
import Header from "../../components/Header";
import { APIRequestGetMembershipTypes } from "../../services/membershipType";
import { useEffect, useState } from "react";

export default function MembershipTypesScreen({}) {
  const [membershipTypes, setMembershipTypes] = useState([]);
  const [selectedScreen, setSelectedScreen] = useState("membership-type");

  useEffect(() => {
    async function fetchData() {
      const data = await APIRequestGetMembershipTypes();
      setMembershipTypes(data);
    }
    fetchData();
  }, []);

  const handleEditMembershipType = () => {};

  return (
    <>
      <div>
        <Header selectedScreen={selectedScreen} />
        <div className="button-container">
          <button className="button">
            <Link to="/add-membership-type">Add Membership Type</Link>
          </button>
        </div>
        <div className="table-container">
          <table className="table">
            <thead>
              <tr>
                <th>Membership Type ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Duration</th>
                <th>Cost</th>
                <th>Edit</th>
              </tr>
            </thead>
            <tbody>
              {membershipTypes.map((membershipType) => (
                <tr key={membershipType.membershipTypeId}>
                  <td>{membershipType.membershipTypeId}</td>
                  <td>{membershipType.name}</td>
                  <td>{membershipType.description}</td>
                  <td>{membershipType.numberOfMonths} months</td>
                  <td>{membershipType.cost}€</td>
                  <td>
                    <Link
                      to={`/edit-membership-type/${membershipType.membershipTypeId}`}
                    >
                      <button>Edit</button>
                    </Link>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
}
