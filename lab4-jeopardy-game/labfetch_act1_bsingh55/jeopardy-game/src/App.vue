<template>
  <div id="app">
    <h1>Jeopardy Game</h1>

    <h2>Current Player: Player {{ currentPlayer + 1 }}</h2>

    <table>
      <thead>
        <tr>
          <th v-for="category in categories" :key="category.id">
            {{ category.name }}
          </th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="row in 5" :key="row">
          <td
            v-for="(category, categoryIndex) in categories"
            :key="categoryIndex"
          >
            <button
              :disabled="completedQuestions[categoryIndex][row - 1]"
              :class="getQuestionButtonClass(categoryIndex, row)"
              @click="selectQuestion(categoryIndex, row)"
            >
              {{
                completedQuestions[categoryIndex][row - 1]
                  ? completedQuestions[categoryIndex][row - 1]
                  : `$${dollarValues[row - 1]}`
              }}
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <div v-if="currentQuestion">
      <h2>Category: {{ currentQuestion.category }}</h2>
      <h3>Value: ${{ currentQuestion.value }}</h3>
      <p v-html="decodeHTMLEntities(currentQuestion.question)"></p>
      <div class="answers">
        <button
          v-for="(answer, index) in currentQuestion.answers"
          :key="index"
          @click="checkAnswer(answer)"
        >
          {{ answer }}
        </button>
      </div>
      <p v-if="answerFeedback">{{ answerFeedback }}</p>
    </div>

    <div>
      <h3>Scores:</h3>
      <ul>
        <li v-for="(player, index) in players" :key="index">
          Player {{ index + 1 }}: {{ player.score }}
        </li>
      </ul>
    </div>

    <div v-if="isGameOver">
      <h2>{{ getWinnerMessage() }}</h2>
    </div>
  </div>
</template>

<script>
import { INVALID_CATEGORY_IDS } from "./invalidCategories.js";

function delay(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

export default {
  data() {
    return {
      categories: [],
      dollarValues: [100, 200, 300, 400, 500],
      currentQuestion: null,
      players: [{ score: 0 }, { score: 0 }, { score: 0 }],
      currentPlayer: 0,
      completedQuestions: Array.from({ length: 4 }, () => Array(5).fill(null)),
      answerFeedback: "",
      isGameOver: false,
    };
  },
  async created() {
    await this.fetchCategories();
    await this.fetchQuestions();
  },
  methods: {
    async fetchCategories() {
      try {
        const response = await fetch("https://opentdb.com/api_category.php");
        const data = await response.json();

        // Defensive check in case the API structure changes
        if (!data || !data.trivia_categories) {
          throw new Error("No categories found in API response.");
        }

        const allCategories = data.trivia_categories;
        console.log("All categories:", allCategories);

        const validCategories = allCategories.filter(
          (cat) => !INVALID_CATEGORY_IDS.includes(cat.id)
        );
        console.log("Filtered valid categories:", validCategories);

        this.categories = this.getRandomCategories(validCategories, 4);

        console.log("Selected Categories:", this.categories);
      } catch (error) {
        console.error("Error fetching categories:", error);
      }
    },

    async fetchWithRetry(url, retries = 3, delayMs = 1500) {
      for (let i = 0; i < retries; i++) {
        const response = await fetch(url);
        if (response.status !== 429) return response;
        await delay(delayMs);
      }
      throw new Error("Too many requests - exceeded retry attempts.");
    },

    async fetchQuestions() {
      const difficultyMapping = {
        100: "easy",
        200: "easy",
        300: "medium",
        400: "medium",
        500: "hard",
      };

      for (let category of this.categories) {
        try {
          const cachedQuestions = localStorage.getItem(
            `questions_${category.id}`
          );
          if (cachedQuestions) {
            category.questions = JSON.parse(cachedQuestions);
          } else {
            category.questions = [];

            for (let value of this.dollarValues) {
              const difficulty = difficultyMapping[value];
              await delay(5000);

              const response = await this.fetchWithRetry(
                `https://opentdb.com/api.php?amount=1&category=${category.id}&difficulty=${difficulty}&type=boolean`
              );

              const data = await response.json();
              const questionData = data.results[0];

              if (!questionData) {
                console.warn(
                  `No questions available for category ${category.name} at $${value}`
                );
                category.questions.push(null);
                continue;
              }

              const formattedQuestion = {
                question: questionData.question,
                correctAnswer: questionData.correct_answer,
                answers: [
                  questionData.correct_answer,
                  ...questionData.incorrect_answers,
                ].sort(() => 0.5 - Math.random()),
              };

              category.questions.push(formattedQuestion);
            }

            localStorage.setItem(
              `questions_${category.id}`,
              JSON.stringify(category.questions)
            );
          }
        } catch (error) {
          console.error(
            `Error fetching questions for category: ${category.name}`,
            error
          );
        }
      }
    },

    getRandomCategories(categories, count) {
      return categories.sort(() => 0.5 - Math.random()).slice(0, count);
    },

    selectQuestion(categoryIndex, row) {
      const selectedCategory = this.categories[categoryIndex];
      if (!selectedCategory.questions || !selectedCategory.questions[row - 1]) {
        alert(
          `No question available for ${selectedCategory.name} at $${
            this.dollarValues[row - 1]
          }`
        );
        return;
      }

      const question = selectedCategory.questions[row - 1];
      if (!question) {
        alert(
          `No question loaded for ${selectedCategory.name} at $${
            this.dollarValues[row - 1]
          }`
        );
        return;
      }

      this.currentQuestion = {
        category: selectedCategory.name,
        question: question.question,
        answers: question.answers,
        correctAnswer: question.correctAnswer,
        value: this.dollarValues[row - 1],
      };
    },

    checkAnswer(answer) {
      if (answer === this.currentQuestion.correctAnswer) {
        this.answerFeedback = "Correct!";
        this.players[this.currentPlayer].score += this.currentQuestion.value;
      } else {
        this.answerFeedback = "Incorrect!";
        this.players[this.currentPlayer].score -= this.currentQuestion.value;
      }

      const categoryIndex = this.getCurrentCategoryIndex();
      const rowIndex = this.getCurrentRowIndex();
      this.completedQuestions[categoryIndex][rowIndex] = `P${
        this.currentPlayer + 1
      }`;
      this.markCompletedQuestion(
        answer === this.currentQuestion.correctAnswer ? "green" : "red"
      );

      this.checkGameOver();
      this.currentPlayer = (this.currentPlayer + 1) % 3;

      setTimeout(() => {
        this.currentQuestion = null;
        this.answerFeedback = "";
      }, 5000);
    },

    getCurrentCategoryIndex() {
      return this.categories.findIndex(
        (category) => category.name === this.currentQuestion.category
      );
    },

    getCurrentRowIndex() {
      return this.dollarValues.indexOf(this.currentQuestion.value);
    },

    markCompletedQuestion(color) {
      const categoryIndex = this.getCurrentCategoryIndex();
      const rowIndex = this.getCurrentRowIndex();
      const button = document.querySelector(
        `table tbody tr:nth-child(${rowIndex + 1}) td:nth-child(${
          categoryIndex + 1
        }) button`
      );
      if (button) {
        button.style.backgroundColor = color;
      }
    },

    getQuestionButtonClass(categoryIndex, row) {
      const completed = this.completedQuestions[categoryIndex][row - 1];
      return completed && completed.startsWith("P") ? "completed" : "";
    },

    checkGameOver() {
      const allCompleted = this.completedQuestions.every((col) =>
        col.every((cell) => cell)
      );
      this.isGameOver = allCompleted;
    },

    getWinnerMessage() {
      const highestScore = Math.max(...this.players.map((p) => p.score));
      const winnerIndex = this.players.findIndex(
        (p) => p.score === highestScore
      );
      return `Player ${winnerIndex + 1} has won the game!`;
    },

    decodeHTMLEntities(str) {
      const parser = new DOMParser();
      return parser.parseFromString(str, "text/html").body.textContent;
    },
  },
};
</script>

<style>
table {
  width: 100%;
  border-collapse: collapse;
}
td,
th {
  border: 1px solid black;
  text-align: center;
  padding: 10px;
}
button {
  padding: 10px;
  font-size: 16px;
}
.answers {
  display: flex;
  gap: 10px;
  margin-top: 10px;
}
</style>
