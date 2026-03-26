from fastapi import APIRouter
from pydantic import BaseModel

from app.db.database import get_mongo_db
from google import genai


class GeminiRequest(BaseModel):
    prompt: str


router = APIRouter()


@router.post("/get_gemini/")
async def get_gemini(body: GeminiRequest):
    """Generate content with Google Gemini."""
    client = genai.Client()

    try:
        result = client.models.generate_content(
            model="gemini-3-flash-preview",
            contents=body.prompt,
            examples=[
                genai.Example(
                    input="What is the capital of France?",
                    output="The capital of France is Paris.",
                ),
                genai.Example(
                    input="What is the largest planet in our solar system?",
                    output="Jupiter is the largest planet in our solar system.",
                ),
            ],
        )

        return {"result": result}
    except Exception as e:
        return {"error": "Failed to generate content from Gemini.", "message": str(e)}


@router.post("/mongo-test/")
async def mongo_test(item: dict):
    """A small endpoint to verify MongoDB connectivity."""
    db = get_mongo_db()
    result = await db["test_collection"].insert_one(item)
    return {"inserted_id": str(result.inserted_id)}

