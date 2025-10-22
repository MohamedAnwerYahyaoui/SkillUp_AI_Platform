from fastapi import FastAPI
from pydantic import BaseModel
from typing import List
import numpy as np, pandas as pd, requests
from sklearn.preprocessing import RobustScaler
from sklearn.ensemble import IsolationForest
from fastapi.middleware.cors import CORSMiddleware
from sentence_transformers import SentenceTransformer, util
from transformers import pipeline

# --------------------- FastAPI Config ---------------------
app = FastAPI(title="SkillUp AI")

origins = ["*"]
app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

BACKEND_BASE_URL = "http://localhost:8088/api"

# --------------------- Load AI Models ---------------------
# Semantic similarity model
embedder = SentenceTransformer("paraphrase-MiniLM-L6-v2")

# Text summarization model
summarizer = pipeline("summarization", model="facebook/bart-large-cnn")

# QA model
qa_pipeline = pipeline("question-answering", model="distilbert-base-uncased-distilled-squad")


# ----------------------------------------------------------
# 1️⃣ Recommend Formations (AI-based)
# ----------------------------------------------------------
@app.get("/ai/recommend/{apprenant_id}")
def recommend(apprenant_id: int):
    try:
        apprenant = requests.get(f"{BACKEND_BASE_URL}/apprenants/{apprenant_id}").json()
        formations = requests.get(f"{BACKEND_BASE_URL}/formations").json()

        apprenant_text = apprenant.get("objectifs", "") + " " + apprenant.get("competences", "")
        appr_vec = embedder.encode(apprenant_text, convert_to_tensor=True)

        items = []
        for f in formations:
            f_text = f"{f.get('titre', '')} {f.get('objectifs', '')} {f.get('syllabus', '')} {f.get('tags', '')}"
            f_vec = embedder.encode(f_text, convert_to_tensor=True)
            score = util.pytorch_cos_sim(appr_vec, f_vec).item()
            items.append({
                "formationId": f["id"],
                "score": float(score),
                "why": "AI semantic similarity between learner profile and formation"
            })

        items = sorted(items, key=lambda x: x["score"], reverse=True)
        return {"apprenantId": apprenant_id, "items": items[:5]}

    except Exception as e:
        return {"error": str(e)}


# ----------------------------------------------------------
# 2️⃣ Summarize CV (AI-based)
# ----------------------------------------------------------
@app.get("/ai/summarize/{apprenant_id}")
def summarize(apprenant_id: int):
    try:
        apprenant = requests.get(f"{BACKEND_BASE_URL}/apprenants/{apprenant_id}").json()
        text = apprenant.get("cvText", "")
        if not text:
            return {"resume": "No CV text found."}

        summary = summarizer(text, max_length=120, min_length=40, do_sample=False)
        return {"resume": f"Résumé automatique du CV de {apprenant['nom']}: {summary[0]['summary_text']}"}

    except Exception as e:
        return {"error": str(e)}


# ----------------------------------------------------------
# 3️⃣ Smart Question Answering (AI-based)
# ----------------------------------------------------------
class QAIn(BaseModel):
    question: str

@app.post("/ai/qa/{apprenant_id}")
def qa(apprenant_id: int, inp: QAIn):
    try:
        apprenant = requests.get(f"{BACKEND_BASE_URL}/apprenants/{apprenant_id}").json()

        # Combine relevant info
        context = (
            f"Nom: {apprenant.get('nom', '')}, "
            f"Objectifs: {apprenant.get('objectifs', '')}, "
            f"Compétences: {apprenant.get('competences', '')}, "
            f"CV: {apprenant.get('cvText', '')}"
        )

        answer = qa_pipeline(question=inp.question, context=context)
        return {"answer": answer["answer"], "score": float(answer["score"]), "apprenant": apprenant_id}

    except Exception as e:
        return {"error": str(e)}


# ----------------------------------------------------------
# 4️⃣ Attendance Anomaly Detection (ML-based)
# ----------------------------------------------------------
@app.get("/ai/attendance/analyze")
def analyze_from_backend():
    try:
        data = requests.get(f"{BACKEND_BASE_URL}/apprenants").json()
        if not data:
            return {"count": 0, "items": []}

        df = pd.DataFrame(data)
        if "id" not in df.columns:
            return {"error": "Missing id in data"}

        ids = df["id"].values
        X = df.select_dtypes(include=[float, int]).fillna(0).values

        scaler = RobustScaler().fit(X)
        Xn = scaler.transform(X)
        iso = IsolationForest(n_estimators=300, random_state=42, contamination="auto").fit(Xn)
        scores = -iso.score_samples(Xn)
        top = np.argsort(scores)[::-1][:max(1, int(len(scores) * 0.1))]

        anomalies = [{"apprenantId": int(ids[i]), "anomaly_score": float(scores[i]), "rank": r+1} for r, i in enumerate(top)]
        return {"count": len(anomalies), "items": anomalies}

    except Exception as e:
        return {"error": str(e)}
