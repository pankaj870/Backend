const checkoutNodeJssdk = require("@paypal/checkout-server-sdk");

function environment() {
  const clientId = process.env.PAYPAL_CLIENT_ID;
  const clientSecret = process.env.PAYPAL_CLIENT_SECRET;

  if (!clientId || !clientSecret) {
    throw new Error(
      "PayPal client ID or secret is missing. Check your .env or environment variables."
    );
  }

  return new checkoutNodeJssdk.core.SandboxEnvironment(clientId, clientSecret);
  // For production:
  // return new checkoutNodeJssdk.core.LiveEnvironment(clientId, clientSecret);
}

function client() {
  return new checkoutNodeJssdk.core.PayPalHttpClient(environment());
}

module.exports = { client };
