# SER421 – Lab 4: Jeopardy Trivia Game (Activity 1)

**Name**: Bhupinder Singh  
**ASURITE**: bsingh55  
**Course**: SER421 – Web-based Applications  
**Lab**: 4 – Activity 1

---

## 📦 Project Setup

To run this Vue.js application locally, follow these steps:

```bash
# 1. Install dependencies
npm install

# 2. Run the development server
npm run serve

# 3. Build for production (optional)
npm run build

# 4. Lint and fix files (optional)
npm run lint

## Note: Once running, open your browser to http://localhost:8080

## 🔍 Overview

This project implements a Jeopardy-style trivia game using Vue 3 as part of **Activity 1** of Lab 4 for SER421 (Web-based Programming). It fulfills all core requirements by dynamically rendering a 4x5 game board of trivia questions fetched from the Open Trivia Database API.

The game supports 3 players, rotating turns, score tracking, and displays True/False questions by difficulty level based on the selected dollar value.

## 🎯 Features Implemented

### ✅ R1: Game Board Setup
- Renders a 4x5 grid using an HTML `<table>` layout.
- Each column is a category; each row corresponds to increasing dollar values ($100 to $500).

### ✅ R1.a: Dynamic Category Selection
- Randomly fetches 4 unique trivia categories from [OpenTDB](https://opentdb.com/api_category.php) using native fetch API with category filtering.


### ✅ R1.b: Difficulty Mapping
- Maps dollar values to difficulty:
  - `$100` & `$200` → Easy
  - `$300` & `$400` → Medium
  - `$500` → Hard

### ✅ R2: Question Selection & Display
- Clicking a cell fetches a True/False question for that category and value.
- Displays the question along with answer options below the board.

### ✅ R3: Answer Handling
- Players answer the question using True/False buttons.
- "Correct!" or "Incorrect!" message appears after selection.
- The cell updates with `"P#"` label and changes color:
  - 🟩 Green = Correct
  - 🟥 Red = Incorrect

### ✅ R3.c: Player Balance Management
- Correct answer: Adds dollar value to player’s balance.
- Incorrect answer: Subtracts value.
- Turn then passes to the next player (P1 → P2 → P3 → P1...).

### ✅ Game Completion
- When all 20 questions are answered, the game ends and a message declares the winning player.

### ✅ Error Handling
- If a question fails to load (due to API limits), the game shows an alert:
  `"No question available for [Category] at $[Value]"`
- Turn does **not** advance, allowing the player to pick another cell.
- Implements retry logic when the API returns a 429 (Too Many Requests) using a delay and retry pattern.
- Includes defensive checks for missing or malformed API data to improve robustness.


---

## ⚙️ Technologies Used

- Vue.js 3.x (created with Vue CLI 5.0.8)
- Native Fetch API with retry logic (no external libraries)
- HTML5 & CSS3
- JavaScript ES6+

---


## Vue CLI Configuration
- This project was scaffolded using the Vue CLI 5.0.8 with the following options:

 - Vue version: 3.x

 - Features: Babel, ESLint (Prettier)

 - Lint on save

## 📝 Notes

- Fetched questions are cached in localStorage per category to minimize redundant requests.
- Retry logic is implemented to handle 429 errors (Too Many Requests) using up to 3 attempts with 1.5-second intervals.




