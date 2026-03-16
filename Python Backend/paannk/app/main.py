from fastapi import FastAPI

from app.db.database import connect_to_mongo, close_mongo_connection
from app.routes.routes import router

app = FastAPI(title="My FastAPI Project")


@app.on_event("startup")
async def on_startup() -> None:
    await connect_to_mongo()


@app.on_event("shutdown")
async def on_shutdown() -> None:
    await close_mongo_connection()


app.include_router(router)
