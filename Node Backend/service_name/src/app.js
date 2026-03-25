import express from "express";

const app = express();

app.use(express.json());

// Load modules
import userModule from "./modules/user/index.js";
import authModule from "./modules/auth/index.js";
import productModule from "./modules/product/index.js";

app.use("/users", userModule.routes);
app.use("/auth", authModule.routes);
app.use("/products", productModule.routes);

export default app;
