import express from "express";
import { getAuth } from "./auth.controller.js";

const router = express.Router();

router.get("/:id", getAuth);

export default router;
