# LankaGuide — Smart Tourism Guide & Travel Planner

LankaGuide is a dynamic, production-grade native Android mobile application designed to assist tourists in exploring Sri Lanka, planning itineraries, reserving hotels, and receiving personalized travel recommendations. The application uses a sleek, glassmorphic Jetpack Compose UI, a robust offline caching architecture, and dynamic cloud synchronization.

---

## 🛠️ Implemented Core Architecture

LankaGuide is built with the following modern Android development stack:

*   **UI Framework:** Jetpack Compose (Kotlin-native declarative layouts with harmonized HSL dark/light palettes).
*   **Database & Cloud:** Firebase Firestore (live user/reviews/places sync) & Firebase Storage (image uploads).
*   **Offline Support:** SQLite via Jetpack Room Database (full destination and itinerary caching).
*   **Image Loading:** Coil Compose (dynamic download, asynchronous thread handling, and local caching).
*   **Machine Learning:** Google ML Kit Barcode Scanning & CameraX viewfinder (real-time Admin ticket QR verification).
*   **REST API Weather Client:** Asynchronous weather fetcher querying Open-Meteo coordinates globally with zero-config setup.
*   **Authentication:** Firebase Auth + Input Trimming + Custom dynamic offline cache fallback.

---

## 🚀 Future Roadmap & Enhancements

To transform LankaGuide into a complete, state-of-the-art national travel platform, the following **12 Future Enhancements** are planned for upcoming release cycles:

### 1. 🤖 AI Chatbot Travel Assistant
*   **Description:** Integrate a conversational AI agent inside the app to act as an instant, interactive tour guide.
*   **Technical Implementation:** Connect to a Large Language Model (e.g., Google Gemini Pro API) via secure HTTPS endpoints. The chatbot will dynamically suggest attractions, restaurants, and custom travel itineraries based on real-time conversation history.
*   **Language Support:** Natural Language Processing (NLP) optimized for English, Sinhala, and Tamil.
*   **Benefit:** Provides tourists with 24/7 personalized, local travel advice without requiring human customer service agents.

### 2. 📍 Real-Time Navigation System
*   **Description:** Transition from coordinate mapping to interactive, turn-by-turn navigation directly inside the app.
*   **Technical Implementation:** Embed the Google Maps Navigation SDK. Configure custom routes, real-time GPS location tracking, and traffic overlays.
*   **Benefit:** Enables tourists to navigate complex routes and roads safely and efficiently with live estimated travel time (ETA) calculations.

### 3. 🕶️ AR (Augmented Reality) Tourist Guide
*   **Description:** Superimpose digital tourism data directly over the real physical environment using the smartphone camera.
*   **Technical Implementation:** Integrate Google ARCore SDK. Track camera frames and overlay interactive historical context labels, descriptions, and ratings over visible landmarks in real time.
*   **Benefit:** Creates a highly immersive, futuristic, and engaging learning experience when visiting historical sites like Sigiriya or Galle Fort.

### 4. 💳 Integrated Online Payment Gateway
*   **Description:** Enable tourists to securely purchase attraction tickets and complete hotel reservations within the application.
*   **Technical Implementation:** Integrate a secure payment gateway (e.g., Stripe, PayHere, or Braintree Mobile SDKs) supporting Visa, MasterCard, Apple Pay, Google Pay, and local digital wallets (e.g., FriMi).
*   **Benefit:** Transforms the guide into a complete transaction-ready booking platform, eliminating the need to visit external browser windows to complete reservations.

### 5. 📉 Smart Expense Tracking & Budgeting
*   **Description:** Give travelers an automated, intelligent diary to manage their vacation finances.
*   **Technical Implementation:** Create an expense log schema inside Room Database. Implement an AI classifier that parses uploaded invoice photos (using ML Kit OCR) to categorize spending (food, lodging, transit) and predict budget limits.
*   **Benefit:** Helps budget-conscious travelers track expenses effortlessly, alerting them when they are near their budget limit.

### 6. 👥 Community Travel Forum
*   **Description:** Create a social ecosystem within the app where tourists can connect, post reviews, and share travel logs.
*   **Technical Implementation:** Create a nested Firestore schema for forum boards, user posts, and photo galleries (stored in Firebase Storage).
*   **Benefit:** Boosts user engagement and builds a highly active, organic travel community that shares trusted reviews and tips.

### 7. 🗺️ Offline Vector Maps
*   **Description:** Enable full map navigation and search capabilities even when in remote areas with zero network connectivity.
*   **Technical Implementation:** Implement offline map package rendering using Mapbox SDK or OpenStreetMap vector files cached locally on the device's storage.
*   **Benefit:** Critical for hiking in mountainous regions (like Ella or Knuckles Range) where cellular coverage is unavailable.

### 8. 📸 AI-Based Landmark Recognition
*   **Description:** Enable tourists to identify any monument, historical structure, or natural wonder simply by pointing their camera at it.
*   **Technical Implementation:** Train a custom TensorFlow Lite classification model on Sri Lankan landmarks, and embed it using ML Kit Image Labeling.
*   **Benefit:** Acts as a knowledgeable, interactive tour guide in your pocket, telling you the rich history of any structure instantly.

### 9. 🚨 Emergency SOS Monitoring System
*   **Description:** Improve traveler safety by providing rapid distress signaling and location broadcast tools.
*   **Technical Implementation:** Implement a background service that continuously tracks coordinates during active travel and provides a one-touch SOS widget that sends SMS alerts with exact GPS maps link to pre-configured emergency contacts and the local tourist police hotline.
*   **Benefit:** Provides immense peace of mind for solo travelers and international backpackers exploring remote regions.

### 10. ✈️ Hotel & Flight Integration Engine
*   **Description:** Expand the app from a regional guide into an all-in-one Global Travel Planner.
*   **Technical Implementation:** Connect with commercial travel GDS APIs (e.g., Amadeus, Sabre, or Expedia Affiliate Network) to query real-time flight availability, ticket prices, and hotel inventories globally.
*   **Benefit:** Allows users to plan, schedule, book flights, and reserve stays for an entire vacation in one single app.

### 11. 🧠 Machine Learning Recommendation Engine
*   **Description:** Transition from content-based category matching to dynamic, deep-learning recommendations.
*   **Technical Implementation:** Implement collaborative filtering models (using TensorFlow Recommenders or PyTorch) running on a server backend. The engine will analyze global user behaviors, ratings, travel trends, and seasonal changes to predict destination preferences.
*   **Benefit:** Renders highly accurate, organic recommendations, introducing travelers to hidden gems they would have otherwise missed.

### 12. 📊 National Tourism Analytics Dashboard
*   **Description:** Provide a backend reporting portal for government administrators, national hotel chains, and tourism boards.
*   **Technical Implementation:** Build a Web dashboard (using Angular/React) connecting to aggregated Firestore arrival metrics. Visualize tourist density hotspots, seasonal lodging demands, and revenue analytics using interactive chart widgets.
*   **Benefit:** Supports data-driven decision-making, helping local authorities optimize resources, infrastructure, and promotional campaigns.

---

## ⚙️ Compilation & Setup

To compile and launch the current, fully functional version of **LankaGuide**:

1.  Open **Android Studio**.
2.  Click **Open** and select: `C:\games\ayesha`.
3.  Ensure your Android device or emulator is connected.
4.  Click **File** -> **Sync Project with Gradle Files**.
5.  Select **Build** -> **Clean Project**, then **Build** -> **Rebuild Project**.
6.  Click **Run** to launch LankaGuide!
