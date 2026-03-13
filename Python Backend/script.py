import os

project_name = "myfastapi"

structure = {
    "app": {
        "__init__.py": "",
        "main.py": """from fastapi import FastAPI
from app.api.routes import router

app = FastAPI(title="My FastAPI Project")

app.include_router(router)
""",
        "api": {
            "__init__.py": "",
            "routes.py": """from fastapi import APIRouter

router = APIRouter()

@router.get("/")
def home():
    return {"message": "FastAPI folder structure working!"}
"""
        },
        "core": {
            "__init__.py": "",
            "config.py": """from pydantic import BaseSettings

class Settings(BaseSettings):
    app_name: str = "My FastAPI Project"

settings = Settings()
"""
        },
        "models": {
            "__init__.py": ""
        },
        # "schemas": {
        #     "__init__.py": ""
        # },
        "services": {
            "__init__.py": ""
        },
        "utils": {
            "__init__.py": ""
        },
        "db": {
            "__init__.py": "",
            "database.py": """from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

SQLALCHEMY_DATABASE_URL = "sqlite:///./test.db"

engine = create_engine(SQLALCHEMY_DATABASE_URL, connect_args={"check_same_thread": False})
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
"""
        }
    },
    "tests": {
        "test_main.py": """from fastapi.testclient import TestClient
from app.main import app

client = TestClient(app)

def test_home():
    response = client.get("/")
    assert response.status_code == 200
"""
    },
    ".env": "APP_NAME=My FastAPI Project\n",
    "requirements.txt": """fastapi
uvicorn[standard]
python-dotenv
pydantic
sqlalchemy
psycopg2-binary
"""
}

def create_structure(base_path, structure_dict):
    for name, content in structure_dict.items():
        path = os.path.join(base_path, name)
        if isinstance(content, dict):
            os.makedirs(path, exist_ok=True)
            create_structure(path, content)
        else:
            with open(path, "w", encoding="utf-8") as f:
                f.write(content)

if __name__ == "__main__":
    os.makedirs(project_name, exist_ok=True)
    create_structure(project_name, structure)
    print(f"✅ FastAPI project '{project_name}' created successfully!")
    print("👉 Next steps:")
    print(f"cd {project_name}")
    print("python -m venv venv")
    print("pip install -r requirements.txt")
    print("uvicorn app.main:app --reload")