from pydantic import BaseSettings

class Settings(BaseSettings):
    app_name: str = "My FastAPI Project"

settings = Settings()
