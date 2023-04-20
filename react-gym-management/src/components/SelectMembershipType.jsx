import React, { useState, useEffect } from "react";
import { APIRequestGetMembershipTypes } from "../services/membershipType";
import "../styles/styles.css";

function SelectMembershipType(props) {
  const [membershipTypes, setMembershipTypes] = useState([]);

  useEffect(() => {
    async function fetchData() {
      const types = await APIRequestGetMembershipTypes();
      setMembershipTypes(types);
    }
    fetchData();
  }, []);

  return (
    <select
      className="two-column-form-input"
      name="membershipTypeId"
      defaultValue={props.defaultValue ? props.defaultValue : null}
      onChange={props.onChange}
    >
      <option value={null}>Select a Membership Type</option>
      {Array.isArray(membershipTypes) &&
        membershipTypes.map((type) => (
          <option key={type.name} value={type.membershipTypeId}>
            {type.name}
          </option>
        ))}
    </select>
  );
}

export default SelectMembershipType;
