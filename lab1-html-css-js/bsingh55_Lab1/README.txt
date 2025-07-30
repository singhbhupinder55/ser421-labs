README.txt  
SER 421 – Module 1 Assignment  
Student: Bhupinder Singh  
Date: May 29th, 2025

===========================================================
📄 FILE STRUCTURE (Included in ZIP)
-----------------------------------------------------------
1. Module1Activity1A.html  
2. Module1Activity1B.html  
3. Module1Activity2.docx  
4. Module1Activity3.zip  
   ├── Module1Activity3A.html  
   └── Module1Activity3B.html  
5. Module1Activity4A.js  
6. Module1Activity4B.js  
7. [Optional] README.txt (this file)

===========================================================
ACTIVITY 1A – Modern HTML Input Types (8 pts)
-----------------------------------------------------------
This file demonstrates the use of 8 modern HTML5 input types:
- Color  
- Range  
- Month  
- Date  
- Time  
- Week  
- Email  
- Tel

Each input is accompanied by a label and sample placeholder text.  
The inputs are semantically appropriate and display correctly in modern browsers.

File: Module1Activity1A.html

===========================================================
ACTIVITY 1B – Routing Number Pattern Validation (7 pts)
-----------------------------------------------------------
This file validates a 9-digit bank routing number using the `pattern` attribute.  
Regex ensures that exactly 9 numeric digits are required.  
The form includes basic error handling and accessibility.

File: Module1Activity1B.html

===========================================================
ACTIVITY 2 – Design Pattern Analysis (15 pts)
-----------------------------------------------------------
The document identifies and explains 3 UI interaction patterns:
1. Hub-and-Spoke Navigation  
2. Wizard/Sequential Workflow  
3. Progressive Disclosure

Each pattern is explained with:
- What it is  
- Why it's used  
- How it appears in real-world applications

File: Module1Activity2.docx

===========================================================
ACTIVITY 3 – CSS/Responsive Web Design (25 pts)
-----------------------------------------------------------

* PART A (10 pts):  
- Cleaned up old HTML formatting by removing deprecated tags (e.g., `<font>`, `align`)  
- Replaced all styling using internal `<style>` CSS in the `<head>`  
- Maintained layout visually identical to the original  

* PART B (15 pts):  
- Converted layout to CSS Grid  
- Implemented breakpoints at:
  • 800px: 3 columns → 2 columns  
  • 550px: 2 columns → 1 column  
- Ensured mobile responsiveness  
- Used semantic class names and modern styling best practices

Files:  
- Module1Activity3A.html  
- Module1Activity3B.html  
(both inside Module1Activity3.zip)

===========================================================
ACTIVITY 4A – Prefix Calculator (18 pts)
-----------------------------------------------------------
A simple prefix calculator implemented using JavaScript.

Features:
- Stores internal state starting at 0
- Accepts and parses JSON strings (e.g., '{"operation":"add", "operand":5}')
- Supports two operations: add and subtract
- Returns new calculated value instead of logging

Function: `calc(jsonString)`

File: Module1Activity4A.js

===========================================================
ACTIVITY 4B – Extended Calculator with History (27 pts)
-----------------------------------------------------------
A class-based calculator object with state history and undo functionality.

Features:
- `Calculator` class initialized with custom value
- `calc(jsonString)` as method on Calculator object
- `undo()` undoes last operation
- Stack history stores all results
- `peek()`, `peek(n)`, `pop()` retrieve values
- `printMe()` logs stack
- `clear()` resets to initial state

Fully modular, testable, and cleanly commented.

File: Module1Activity4B.js

===========================================================
📌 NOTES / COMMENTS:
-----------------------------------------------------------
• All HTML and JS files follow clean indentation and consistent styling  
• Inline comments added for clarity where needed  
• Fonts, margins, and layout decisions made with responsiveness and accessibility in mind  
• All CSS is written in the `<head>` section as required by instructions  
• JavaScript functions are modular and easy to test via browser console  
• No external files required to test individual components

===========================================================
