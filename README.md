# 🚀 SkillUp – Plateforme de Gestion de Formations Intelligente

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-brightgreen)
![Python](https://img.shields.io/badge/Python-3.11-blue)
![FastAPI](https://img.shields.io/badge/FastAPI-0.100-teal)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Vite](https://img.shields.io/badge/Vite-4.0-purple)

**SkillUp** est une application web complète de gestion de formations enrichie de fonctions d'intelligence artificielle avancées.

---

## 📖 Table des matières

- [Aperçu](#-aperçu)
- [Fonctionnalités](#-fonctionnalités)
- [Architecture](#-architecture)

---

## 🎯 Aperçu

SkillUp combine une plateforme classique de gestion de formations avec des modules IA innovants pour offrir une expérience pédagogique intelligente et personnalisée.

### 🎯 Objectifs
- ✅ Gestion complète du cycle de formation
- 🧠 Intégration de services IA avancés
- 🚀 Architecture microservices modulaire
- 🔒 Sécurité et évolutivité

---

## 🧩 Fonctionnalités

### 📋 Modules CRUD
| Module | Description |
|--------|-------------|
| **Formations** | Gestion du catalogue de formations |
| **Formateurs** | Gestion des profils formateurs |
| **Apprenants** | Gestion des comptes apprenants |
| **Sessions** | Planification des sessions de formation |
| **Inscriptions** | Gestion des inscriptions aux sessions |
| **Présences** | Suivi de l'assiduité des apprenants |

### 🤖 Services IA
| Service | Technologie | Description |
|---------|-------------|-------------|
| **Recommandation** | Filtrage collaboratif | Suggestions personnalisées |
| **Résumé automatique** | TF-IDF / Transformers | Résumé de documents |
| **QA Documents** | Sentence-BERT | Questions/Réponses |
| **Détection anomalies** | Isolation Forest | Alertes assiduité |

---

## 🏗 Architecture

```mermaid
graph TB
    A[Frontend<br>Vite + JS] --> B[Backend<br>Spring Boot]
    B --> C[Base de données<br>MySQL]
    B --> D[Service IA<br>FastAPI]
    D --> E[ML Models<br>scikit-learn]
    
    style A fill:#ff6b6b
    style B fill:#4ecdc4
    style C fill:#45b7d1
    style D fill:#96ceb4
    style E fill:#feca57
