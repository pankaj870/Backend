from typing import Optional

from motor.motor_asyncio import AsyncIOMotorClient
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

from app.core.config import settings

# SQLAlchemy (SQLite) - left in place for legacy / future use
SQLALCHEMY_DATABASE_URL = "sqlite:///./test.db"
engine = create_engine(SQLALCHEMY_DATABASE_URL, connect_args={"check_same_thread": False})
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

# MongoDB (Motor)
mongo_client: Optional[AsyncIOMotorClient] = None


async def connect_to_mongo() -> None:
    """Connect to MongoDB on app startup."""
    global mongo_client
    mongo_client = AsyncIOMotorClient(settings.mongodb_uri)
    # Ensure connection is working by sending a ping.
    await mongo_client.admin.command("ping")


async def close_mongo_connection() -> None:
    """Close the MongoDB connection on shutdown."""
    if mongo_client:
        mongo_client.close()


def get_mongo_db():
    """Get the default MongoDB database instance."""
    if not mongo_client:
        raise RuntimeError("MongoDB client is not connected")
    return mongo_client[settings.mongodb_db]
