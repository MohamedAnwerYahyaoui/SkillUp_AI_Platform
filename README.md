🚀 SkillUp – Plateforme de Gestion de Formations Intelligente
SkillUp est une application web de gestion de formations enrichie de fonctions d'intelligence artificielle pour la recommandation, le résumé automatique, la recherche de réponses et la détection d'anomalies.

📌 Sommaire
Aperçu

Fonctionnalités

Stack Technique

Architecture

Installation et Démarrage

API Principales

Fonctions IA

Déploiement

Sécurité

Auteurs

🎯 Aperçu
SkillUp permet de gérer un catalogue de formations, des formateurs, des apprenants, des sessions, des inscriptions et des présences. Le projet intègre également des modules IA pour :

Recommander des formations personnalisées

Résumer automatiquement des comptes-rendus

Répondre aux questions sur les documents pédagogiques

Détecter les anomalies d’assiduité

🧩 Fonctionnalités
Fonctionnalités Métier (CRUD)
✅ Gestion des Formations

✅ Gestion des Formateurs

✅ Gestion des Apprenants

✅ Gestion des Sessions

✅ Gestion des Inscriptions

✅ Gestion des Présences

Fonctions IA
🧠 Recommandation de formations (filtrage collaboratif)

📄 Résumé automatique de CV/compte-rendu

❓ QA (Questions/Réponses) sur documents

⚠️ Détection d’anomalies d’assiduité (Isolation Forest)

🛠 Stack Technique
Composant	Technologie
Frontend	Vite + JavaScript
Backend	Java 17, Spring Boot, JPA/Hibernate
Base de données	MySQL
Services IA	Python 3.11, FastAPI, scikit-learn, transformers
Déploiement	Docker, Docker Compose (Kubernetes optionnel)
🏗 Architecture
text
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│   Frontend      │    │   Backend        │    │   Service IA    │
│   (Vite/JS)     │◄──►│   (Spring Boot)  │◄──►│   (FastAPI)     │
└─────────────────┘    └──────────────────┘    └─────────────────┘
                              │
                      ┌──────────────────┐
                      │   Base de données│
                      │   (MySQL)        │
                      └──────────────────┘
🚀 Installation et Démarrage
Prérequis
Java 17

MySQL

Python 3.11

Node.js (pour le frontend)

1. Base de données
sql
CREATE DATABASE skillup;
2. Backend (Spring Boot)
bash
cd backend
./mvnw clean package
java -jar target/skillup-backend.jar
3. Service IA (FastAPI)
bash
cd ia
python -m venv .venv
source .venv/bin/activate  # ou .venv\Scripts\activate sur Windows
pip install -r requirements.txt
uvicorn main:app --host 0.0.0.0 --port 8001
4. Frontend (Vite)
bash
cd frontend
npm install
npm run dev
📡 API Principales
Méthode	Endpoint	Description
GET	/api/formations	Liste des formations
POST	/api/formations	Créer une formation
GET	/api/ia/recommendations?apprenantId={id}	Recommandations
POST	/api/ia/summarize	Résumé de texte
POST	/api/ia/qa	Questions/Réponses
GET	/api/ia/anomalies?periode=30	Détection d’anomalies
🤖 Fonctions IA
Recommandation
Vecteur de profil apprenant + similarité cosinus

Évolutif vers LightFM ou embeddings

Résumé automatique
Approche extractive (TF-IDF) ou abstractive (BART/T5)

QA sur documents
Embeddings Sentence-BERT + recherche sémantique

Détection d’anomalies
Isolation Forest sur features temporelles

🐳 Déploiement
Avec Docker Compose
bash
docker-compose up --build
Production
Kubernetes recommandé

Ingress + TLS

Monitoring : Prometheus/Grafana

🔐 Sécurité
Authentification JWT

Validation des DTO

Rate limiting sur les endpoints IA

Contrôle d’accès par rôles

👥 Auteurs
Équipe SkillUp – Octobre 2025

📄 Licence
Ce projet est destiné à un usage éducatif et expérimental.

