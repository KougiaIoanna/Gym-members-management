import client from "./client";

export const APIRequestPostMembershipType = async (
  name,
  description,
  cost,
  numberOfMonths
) => {
  const data = { name, description, cost, numberOfMonths };
  return client
    .post("/membership-type", data)
    .then((response) => {
      console.log("success api request", response.data);
    })
    .catch((error) => {
      console.log("failed api request", error);
    });
};

export const APIRequestUpdateMembershipType = async (
  membershipTypeId,
  name,
  description,
  cost,
  numberOfMonths
) => {
  const data = { name, description, cost, numberOfMonths };
  await client
    .put("/membership-type/" + parseInt(membershipTypeId), data)
    .catch((error) => {
      console.log("errorrr", error);
    });
};

export const APIRequestDeleteMembershipType = async (membershipTypeId) => {
  client
    .delete("/membership-type/" + parseInt(membershipTypeId))
    .catch((err) => {
      console.log("error while deleting:", membershipTypeId);
    });
};

export const APIRequestGetMembershipTypes = async () => {
  const res = await client.get("/membership-type");
  return res.data;
};

export const APIRequestGetMembershipTypeById = async (membershipTypeId) => {
  const res = await client
    .get("/membership-type/" + parseInt(membershipTypeId))
    .catch((err) => {
      console.log("error with edit:", err);
    });
  return res.data;
};
