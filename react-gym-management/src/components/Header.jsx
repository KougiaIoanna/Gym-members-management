import React, { useState } from "react";
import { Link } from "react-router-dom";
import "./Header.css";

const Header = ({ selectedScreen }) => {
  return (
    <nav className="Header">
      <ul>
        <li
          className={`header-screen-name ${
            selectedScreen === "member" ? "selected" : ""
          }`}
        >
          <Link to="/members">Members</Link>
        </li>
        <li
          className={`header-screen-name ${
            selectedScreen === "membership-type" ? "selected" : ""
          }`}
        >
          <Link to="/membership-types">Membership Types</Link>
        </li>
        <li
          className={`header-screen-name ${
            selectedScreen === "payment-history" ? "selected" : ""
          }`}
        >
          <Link to="/payment-history">Payment History</Link>
        </li>
      </ul>
    </nav>
  );
};

export default Header;
