import client from "./client";
import { APIRequestUpdateStatus } from "./member";

export const APIRequestPostPayment = async (
  memberId,
  paymentDate,
  membershipTypeId,
  amount
) => {
  const data = { memberId, paymentDate, membershipTypeId, amount };
  try {
    const response = await client.post(
      "/payment-history/" + parseInt(memberId),
      data
    );
    console.log("success api request", response.data);
    try {
      await APIRequestUpdateStatus(memberId, "Active");
      console.log("success api request", response.data);
    } catch (updateError) {
      console.log("failed to update status", updateError);
    }
  } catch (error) {
    console.log("failed api request post payment");
    console.log(error.response);
  }
};

export const APIRequestDeletePayment = async (paymentId, memberId) => {
  console.log("about to delete payment for member", memberId);
  client
    .delete("/payment-history/" + parseInt(paymentId))
    .then(async (res) => {
      await APIRequestUpdateStatus(memberId, "Inactive");
      console.log("successs:", paymentId);
    })
    .catch((err) => {
      console.log("error while deleting:", paymentId, err);
    });
};

export const APIRequestGetPaymentHistory = async () => {
  const res = await client.get("/payment-history");
  return res.data;
};

export const APIRequestGetPaymentHistoryForMember = async (memberId) => {
  const res = await client.get("/payment-history/" + parseInt(memberId));
  return res.data;
};

export const APIRequestGetLastPaymentByMemberId = async (memberId) => {
  const res = await client.get("/payment-history/member/" + parseInt(memberId));
  return res.data;
};

export const APIRequestGetPaymentHistoryById = async (paymentId) => {
  const res = await client.get("/payment-history/" + parseInt(paymentId));
  return res.data;
};

export const APIRequestUpdatePayment = async (
  paymentId,
  memberId,
  paymentDate,
  membershipTypeId,
  amount
) => {
  const data = { memberId, paymentDate, membershipTypeId, amount };
  await client
    .put("/payment-history/" + parseInt(paymentId), data)
    .catch((error) => {
      console.log("errorrr", error);
    });
};
