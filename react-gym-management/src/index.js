import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import MembersScreen from "./screens/member/MembersScreen";
import AddMemberScreen from "./screens/member/AddMemberScreen";
import MembershipTypesScreen from "./screens/membershipType/MembershipTypesScreen";
import PaymentHistoryScreen from "./screens/paymentHistory/PaymentHistoryScreen";
import AddMembershipTypeScreen from "./screens/membershipType/AddMembershipTypeScreen";
import AddPayment from "./screens/paymentHistory/AddPaymentScreen";
import EditPayment from "./screens/paymentHistory/EditPaymentScreen";

import EditMemberScreen from "./screens/member/EditMemberScreen";
import EditMembershipTypeScreen from "./screens/membershipType/EditMembershipTypeScreen";
import Header from "./components/Header";

const router = createBrowserRouter([
  {
    path: "/",
    element: <Header />,
  },
  {
    path: "/members",
    element: <MembersScreen />,
  },
  {
    path: "/membership-types",
    element: <MembershipTypesScreen />,
  },
  {
    path: "/payment-history",
    element: <PaymentHistoryScreen />,
  },
  {
    path: "/add-member",
    element: <AddMemberScreen />,
  },
  {
    path: "/add-membership-type",
    element: <AddMembershipTypeScreen />,
  },
  {
    path: "/add-payment/:memberId",
    element: <AddPayment />,
  },
  { path: "/edit-member/:memberId", element: <EditMemberScreen /> },
  {
    path: "/edit-membership-type/:membershipTypeId",
    element: <EditMembershipTypeScreen />,
  },
  {
    path: "/edit-payment/:paymentId",
    element: <EditPayment />,
  },
]);

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
