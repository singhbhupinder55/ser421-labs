# Lab 2: Eliza Chatbot – Client-Side Web Programming

## Author
Bhupinder Singh  
Arizona State University  
Course: SER 421 - Web & Distributed Software Development

---

## Overview

This lab consists of three activities showcasing DOM manipulation, event handling, and local storage in a browser-only single page application (SPA). The implementation is inspired by the classic "Eliza" chatbot and demonstrates both functional and persistent client-side interaction using HTML and JavaScript.

---

## Activity Summaries

###  Activity 1: DOM Manipulation
- Renamed "More Results" to "More redundant Results!"
- Counted `<li>` elements and logged to console
- Retrieved input value from DuckDuckGo search bar
- Hid the duck icon in the top-left corner using a DOM call

###  Activity 2: Eliza Chatbot (Basic)
- Greets user by name and starts conversation
- One-line input with randomized Eliza responses based on keywords
- Catch-all fallback for unmatched input
- Inactivity timer triggers randomized alerts after 10 seconds of no input
- All interaction handled via JavaScript DOM manipulation (no CSS or server use)

###  Activity 3: Eliza Chatbot (Stateful)
- Maintains full conversation history
- Saves and restores chat state via `localStorage` per username
- Supports `/clear` command to reset state and view
- Implements enhanced randomization ensuring responses don’t repeat until all options are shown

---

## Folder Structure

- `/Activity1`: Includes `activity1.js` and screenshot of result
- `/Activity2`: Contains `activity2.html` and `activity2.js`
- `/Activity3`: Contains `activity3.html`, `activity3.js`, and saved page screenshot

---

## How to Run

1. Open each `activityX.html` file in a modern browser (Chrome/Firefox).
2. No server setup required. All files run locally as a SPA.
3. JavaScript files are linked in HTML and handle all interactivity.

---

## Notes
- The entire app avoids use of CSS, server-side code, or page refresh.
- The SPA structure and `localStorage` ensure all requirements are met as per the rubric.

---

## Screenshots
Included inside each folder as required by the assignment submission.


