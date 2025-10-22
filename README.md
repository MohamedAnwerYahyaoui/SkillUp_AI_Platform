# SkillUp — Starter (Sans Docker)
Composants:
- Backend: Spring Boot (Java 17), JPA MySQL, Swagger
- IA: FastAPI (Python 3.11) — stubs (Reco, Résumé, QA) + Isolation Forest (anomalies)
- Frontend: Vite (HTML/JS minimal) pour tester CRUD + IA

## Prérequis
- Java 17 + Maven
- Python 3.11 + pip
- Node 20 + npm
- MySQL 8 (localhost:3306)

## Base MySQL
Créez la base et l'utilisateur :
CREATE DATABASE skillup;
CREATE USER 'skillup'@'localhost' IDENTIFIED BY 'skilluppw';
GRANT ALL PRIVILEGES ON skillup.* TO 'skillup'@'localhost';
FLUSH PRIVILEGES;

## Démarrage
### 1) Backend
cd backend
mvn spring-boot:run
# http://localhost:8080/api/health
# http://localhost:8080/swagger-ui/index.html

### 2) IA
cd ai
python -m venv .venv
# Windows: .\.venv\Scripts\activate
# Linux/Mac: source .venv/bin/activate
pip install -r requirements.txt
uvicorn app:app --reload --port 8000
# http://localhost:8000/docs

### 3) Frontend
cd frontend
npm i
npm run dev
# http://localhost:5173

Dans la page, mettez Backend URL = http://localhost:8080, AI URL = http://localhost:8000.
