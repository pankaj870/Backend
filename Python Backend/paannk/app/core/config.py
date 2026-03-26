from pydantic_settings import BaseSettings


class Settings(BaseSettings):
    app_name: str = "My FastAPI Project"

    # MongoDB settings (used by app/db/database.py)
    mongodb_uri: str = "mongodb://localhost:27017"
    mongodb_db: str = "mydb"


settings = Settings()
