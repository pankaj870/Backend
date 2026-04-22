"use strict";

const { spawnSync } = require("node:child_process");
const fs = require("node:fs");
const path = require("node:path");

const serviceRoot = path.resolve(__dirname, "..");
const layerDir = path.join(serviceRoot, "layer", "nodejs");
const layerNodeModulesDir = path.join(layerDir, "node_modules");

fs.mkdirSync(layerDir, { recursive: true });

if (fs.existsSync(layerNodeModulesDir)) {
  fs.rmSync(layerNodeModulesDir, { recursive: true, force: true });
}

const npmCommand = process.platform === "win32" ? "npm.cmd" : "npm";
const result = spawnSync(npmCommand, ["install", "--omit=dev"], {
  cwd: layerDir,
  shell: process.platform === "win32",
  stdio: "inherit",
});

if (result.status !== 0) {
  process.exit(result.status ?? 1);
}
