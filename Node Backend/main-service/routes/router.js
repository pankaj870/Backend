const express = require("express");
const router = express.Router();
const { client } = require("../utilities/paypal.client");
const checkoutNodeJssdk = require("@paypal/checkout-server-sdk");


// Create PayPal Order
// router.post("/create-order", async (req, res) => {
//   const request = new checkoutNodeJssdk.orders.OrdersCreateRequest();
//   request.prefer("return=representation");
//   request.requestBody({
//     intent: "CAPTURE",
//     purchase_units: [
//       {
//         amount: {
//           currency_code: "USD",
//           value: req.body.amount || "20.00",
//         },
//       },
//     ],
//   });

//   try {
//     const order = await client().execute(request);
//     return res.json({ id: order.result.id });
//   } catch (err) {
//     console.error("PayPal Create Order Error:", err.message);
//     res.status(500).send("Something went wrong with PayPal Create Order");
//   }
// });

// // Capture order
// router.post("/capture-order/:orderId", async (req, res) => {
//   const orderId = req.params.orderId;
//   const request = new checkoutNodeJssdk.orders.OrdersCaptureRequest(orderId);
//   request.requestBody({});

//   try {
//     const capture = await client().execute(request);
//     res.json(capture.result);
//   } catch (err) {
//     console.error("PayPal Capture Order Error:", err.message);
//     res.status(500).send("Something went wrong with PayPal Capture Order");
//   }
// });

module.exports = router;
