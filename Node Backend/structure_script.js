// create-module-structure.mjs
import fs from "fs";
import path from "path";

// 👉 Change your service name here
const serviceName = "service_name";

const basePath = path.join(process.cwd(), serviceName);

// Modules you want to generate
const modules = ["user", "auth", "product"];

// Common folders
const commonFolders = [
  "middlewares",
  "utilities",
  "constants",
  "errors",
  "database",
];

// Helper to create file
const createFile = (filePath, content = "") => {
  if (!fs.existsSync(filePath)) {
    fs.writeFileSync(filePath, content);
    console.log(`Created file: ${filePath}`);
  }
};

// Helper to create folder
const createFolder = (folderPath) => {
  if (!fs.existsSync(folderPath)) {
    fs.mkdirSync(folderPath, { recursive: true });
    console.log(`Created folder: ${folderPath}`);
  }
};

// 👉 Create base folders
createFolder(basePath);
// createFolder(path.join(basePath, "));
createFolder(path.join(basePath, "modules"));
createFolder(path.join(basePath, "common"));
createFolder(path.join(basePath, "config"));
createFolder(path.join(basePath, "loaders"));
// createFolder(path.join(basePath, "tests"));

// 👉 Create common subfolders
commonFolders.forEach((folder) => {
  createFolder(path.join(basePath, "common", folder));
});

// 👉 Create modules
modules.forEach((module) => {
  const modulePath = path.join(basePath, "modules", module);
  createFolder(modulePath);

  // Files inside each module
  createFile(
    path.join(modulePath, `${module}.controller.js`),
    `export const get${capitalize(module)} = (req, res) => {
  res.send("${module} controller working");
};
`,
  );

  createFile(
    path.join(modulePath, `${module}.service.js`),
    `export const ${module}Service = {
  get${capitalize(module)}ById: (id) => {
    return { id, message: "${module} service working" };
  },
};
`,
  );

  createFile(
    path.join(modulePath, `${module}.repository.js`),
    `export const ${module}Repository = {
  findById: (id) => {
    return { id, message: "${module} repository working" };
  },
};
`,
  );

  createFile(
    path.join(modulePath, `${module}.routes.js`),
    `import express from "express";
import { get${capitalize(module)} } from "./${module}.controller.js";

const router = express.Router();

router.get("/:id", get${capitalize(module)});

export default router;
`,
  );

  createFile(
    path.join(modulePath, `${module}.validation.js`),
    `export const validate${capitalize(module)} = (data) => {
  return true;
};
`,
  );

  createFile(
    path.join(modulePath, `${module}.model.js`),
    `// Define ${module} schema/model here
export default {};
`,
  );

  // index.js (module entry point)
  createFile(
    path.join(modulePath, "index.js"),
    `import routes from "./${module}.routes.js";

export default {
  routes,
};
`,
  );
});

// 👉 Create app.js
createFile(
  path.join(basePath, "app.js"),
  `import express from "express";

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
`,
);

// 👉 Create server.js
createFile(
  path.join(basePath, "server.js"),
  `import app from "./app.js";

const PORT = 3000;

app.listen(PORT, () => {
  console.log(\`Server running on port \${PORT}\`);
});
`,
);

// 👉 Create package.json
createFile(
  path.join(basePath, "package.json"),
  `{
  "name": "${serviceName}",
  "version": "1.0.0",
  "type": "module",
  "main": "server.js",
  "scripts": {
    "start": "node server.js",
    "dev": "nodemon server.js"
  },
  "dependencies": {
    "express": "^4.18.2"
  }
}
`,
);

// 👉 Helper function
function capitalize(str) {
  return str.charAt(0).toUpperCase() + str.slice(1);
}

console.log("✅ Module-based backend structure created successfully!");
