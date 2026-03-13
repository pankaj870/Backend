// create-node-backend-structure.js
const fs = require("fs");
const path = require("path");

// Replace with your service name
const serviceName = "service_name";

// Base path for the service
const basePath = path.join(process.cwd(), serviceName);

// Define the folder structure relative to the service folder
const folders = [
  "controllers",
  "routes",
  "validations",
  "middlewares",
  "utilities",
  "services",
];

// Optional default files (can be empty to start)
const files = [
  {
    path: "routes/index.js",
    content: `const express = require('express');\nconst router = express.Router();\n\nmodule.exports = router;\n`,
  },
  {
    path: "controllers/sampleController.js",
    content: `exports.sampleFunction = (req, res) => {\n  res.send('Hello World');\n};\n`,
  },
  {
    path: "handler.js",
    content: `const express = require('express');\n
     const app = express();
     \n app.use(express.json()); 
     \n app.use(express.urlencoded({ extended: true }));
     \n const port = 3000;
     \n app.use("/", require("./routes/index"));
     \n app.listen(port ,()=> console.log("server are running on port 3000"));
    `,
  },
];

// Create service folder if it doesn't exist
if (!fs.existsSync(basePath)) {
  fs.mkdirSync(basePath);
  console.log(`Created service folder: ${serviceName}`);
}

// Create subfolders
folders.forEach((folder) => {
  const dirPath = path.join(basePath, folder);
  if (!fs.existsSync(dirPath)) {
    fs.mkdirSync(dirPath, { recursive: true });
    console.log(`Created folder: ${path.join(serviceName, folder)}`);
  }
});

// Create default files
files.forEach((file) => {
  const filePath = path.join(basePath, file.path);
  if (!fs.existsSync(filePath)) {
    fs.writeFileSync(filePath, file.content);
    console.log(`Created file: ${path.join(serviceName, file.path)}`);
  }
});

console.log(
  `Node backend structure created successfully inside "${serviceName}"!`,
);
