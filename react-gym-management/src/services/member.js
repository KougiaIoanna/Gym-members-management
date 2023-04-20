import client from "./client";
import { APIRequestGetMembershipTypeById } from "./membershipType";
import { APIRequestPostPayment } from "./paymentHistory";

export const APIRequestPostMember = (
  name,
  email,
  phone,
  address,
  membershipTypeId,
  dateOfBirth,
  joinDate,
  status
) => {
  membershipTypeId = parseInt(membershipTypeId);
  const data = {
    name,
    email,
    phone,
    address,
    membershipTypeId,
    dateOfBirth,
    joinDate,
    status,
  };
  const date = new Date().toISOString().slice(0, 10);
  return client
    .post("/member", data)
    .then(async (response) => {
      if (status === "Active") {
        const membership = await APIRequestGetMembershipTypeById(
          membershipTypeId
        );
        console.log("------------------", membership.cost);
        await APIRequestPostPayment(
          response.data.memberId,
          date,
          membershipTypeId,
          membership.cost
        );
      }
    })
    .catch((error) => {
      console.log("error again post member", error);
    });
};

export const APIRequestUpdateMember = async (
  memberId,
  name,
  email,
  phone,
  address,
  membershipTypeId,
  dateOfBirth,
  joinDate,
  status
) => {
  membershipTypeId = parseInt(membershipTypeId);

  const data = {
    memberId,
    name,
    email,
    phone,
    address,
    membershipTypeId,
    dateOfBirth,
    joinDate,
    status,
  };
  await client.put("/member/" + parseInt(memberId), data);
};

export const APIRequestDeleteMember = async (memberId) => {
  client.delete("/member/" + parseInt(memberId)).catch((err) => {
    console.log("error while deleting:", memberId);
  });
};

export const APIRequestGetMembers = async () => {
  const res = await client.get("/member");
  console.log("HEY HO! these are the members:", res.data);
  return res.data;
};

export const APIRequestGetMemberById = async (id) => {
  const res = await client.get("/member/" + parseInt(id)).catch((err) => {
    console.log("error with edit:", err);
  });
  return res.data;
};

export const APIRequestUpdateStatus = (memberId, status) => {
  console.log("WHYYYYYYYYYYYYYYYYYY", status, typeof status, memberId);
  client.patch("/member/" + parseInt(memberId) + "/status", status);
};
