# SER 421 – Lab 3: Vue.js Activities  
**Student Name**: Bhupinder Singh  
**ASURITE ID**: bsingh55  
**Vue Version**: 3 (using Options API, no build tools in Activity 1)

---

## 📁 Contents of Submission (`ser421labVue.zip`)
- `labvue_act1_bsingh55.html`
- `labvue_act2_bsingh55.vue`
- `lab3vue_act3_bsingh55/` (Vue app project folder)

---

## 🧪 Activity 1: Simple Survey (In-DOM, No Build Tools)

### ✔ Features Implemented
- Ask 3 questions with text inputs.
- Shows `"Knight: AAAAAAAAHHHHHHHHHHH!!!"` when user enters `idk`.
- Shows `"Bridgekeeper: Alright, off you go then"` if all answers are valid.
- “Go again” button resets inputs and toggles the 3rd question.

### ✔ Testing
- Type `idk` in any input → ❗ Knight message appears.
- Type 3 valid answers → ✅ Bridgekeeper message appears.
- Click “Go again” → inputs reset, question 3 toggles.

---

## 🧪 Activity 2: Single File Component (SFC) Survey

### ✔ Features Implemented
- Multiple-choice survey with 3 questions.
- Tracks score with each correct answer.
- On completion, shows styled green result message with italics.

### ✔ Testing
1. Click `OK` → progresses to next question ✅  
2. Score updates in real time ✅  
3. Final message styled in green, Times New Roman, 1.5x size ✅  
4. Radio buttons rendered vertically using `v-for` ✅  
5. Styling uses `<style>` block, no external CSS ✅  

---

## 🧪 Activity 3: Multi-Component App with Build Tools

### ✔ Components Created
- `HelloWorld.vue`: Displays "Hello Kevin" via `name` prop
- `Balance.vue`: Shows balance, slider to adjust amount, disables subtract if needed
- `Currency.vue`: Displays converter, syncs with amount and currency

### ✔ Features Implemented
- ✅ **R1**: HelloWorld shows name from parent `App.vue`
- ✅ **R2**: Slider controls amount from 5 to 100 in steps of 5
- ✅ **R3**: Subtract button disabled when amount > balance
- ✅ **R4**: Balance uses denomination from `Currency`
- ✅ **R5**: Currency default amount equals the balance amount
- ✅ **Component communication** via props/events
- ✅ **Separate SFCs** used with no global hacks

### ✔ Testing Instructions
1. Start app: `npm run dev`  
2. Visit [http://localhost:5174](http://localhost:5174)  
3. Verify each of the above R1–R5 on the live interface.

---

## 🛠️ Notes
- Vue Playground link for Activity 2 is included as a comment inside `labvue_act2_bsingh55.vue`.
- Activity 3 was scaffolded using:
  ```bash
  npm init vue@latest

