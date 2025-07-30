// Sharedlink : https://play.vuejs.org/#eNqlVllv2zgQ/isTvThNfOTo7gKKE2yb7QLdYpvdNi9FnAdGHtlsKFIlKTtukP/eISnqsNOiQF8kajjHN7cek1dlOV5VmKTJ1GJRCmbxYiYBpnO+8gc67o1G8Bc3dLmBrNIapQWTKY0wGkUeYofViOfns2QvU6QILc5nSX1dMxi7EUgcuZJ2tEa+WNoU7pSYn4EnGf4VUzgen2Bx1pEF+KQq3TedwuNjDeLpCVRlQeWO9KVCY7mSZixQLuySbhsIk+BT8/2cX1G+de1Xsf8fNTp0UsEhHBMoj78Be0MXtz8LlUmzRgrHUvEMDTADms25grvKWtK1jbwDZSrYHQrKU640uRI0AJdR1yyB9B43zVXPDxLnsqRA203pAuGNOokVE5UjRBnSX6g5CiJVBvUrD5fIk54ycr+237rtHPcQWweeC8TVO3jtfQWrYKHc0y4RJD58J38hMvBnJnh2T7AcZ0wL+Xj1bjoJLLGae1a9zb+5ZAIKNIYtEFhuXQZCnXO58Pa/VPzrTkegMKGhdgopU0LpFBYaUW7V0G9UQ4GSs4KLTQqDa0624T2u4YMqmBzU971C3G4ZWLIVRpQ4b0DuhX4K3bOm+vnpVhp3MsUvrpdM3sOGDFE9Qcm05RkvmQvI3nTCd5LYP8fTdNIZPPRpMs1LS2d8KJW2MMecVcLCoxOaM8v2X4QzgEZbaWqsaIi6KIWjIUwmu/0sq+KOyrDmrGdIn9cTI0cTthRyRkn0nFaz7B54HnLNTcsVxdqKT8mkEB0xF353PTBgUGDmUhJ6ucEfI57CzeB3OIA/4HwwhMHJqZsaR+HjmtTUI4AKH1eoN3bpapDgDG6Hja7A41W9PHGCp6fuSefb4LUirzNb6zKN32EUOLEjx3/sH160VeDkS2UMvxNUYPUgckXAhGi9CCqfztz7yQMr0C7VnJTXKes2YptWcOovl1jHrHbWqXef25mNMpSVfQqEGbcpgPPzc5IhWozGTfiiaduxBoHHp//w8MxZ5zLTWLSLjpTX8YpCNLQ6aP9V1GnPziFCnXPJzTJyRwRkqVXRgHcb4iKC3mrAHmQy+jYH4i4cvoZ1CAXTIW6+Rqm7d2q0xtAOBrKnK/R58q6Bm1o78enG1de2j5RGg9bXdTdRvRh0Qta86OEKYzpp2t21vhuNdJwcwEc6xsGac21o7awVEIkK7WAykzQ7Uk8fqXzkNtIQHEnaZSTsn9TxemZQRmpvaUuf0i3bsbj3ezv2hccQtmlroxnWlzReOUWCxjV1TP1FmVFSmZJlLtLbtmJ1U6109kz0VLDW0WCwtz12/HELZBfWzg75XnQIHO/45dOSAreM9me4przVuZrJZJhY6hKZ88X4s1GS/iW9rFtxRckF6qsyzIOkafxZQpNCrf/xNFd89dxyfxHU98/QP5sHR5sl/7mK0yv60WjuLNMLtOH6zcf3VHudS/oXqQRx/+DyAxolqvA34NheV3JOsDt8Hu3bwi0kyta1efNgUZrolAPaDrlZQj/Ulz9wvYV7On4ZmyF5+gaOY7mu

<template>
  <div>
    <!-- Display current score -->
    <div v-if="!completed">
      <div style="font-weight: bold; font-size: 1.2em;">
        Your current score: {{ score }} out of {{ questions.length }}
      </div>

      <!-- Display current question -->
      <div style="font-weight: bold; font-size: 1.2em;">
        Question {{ qno + 1 }}: {{ questions[qno] }}
      </div>

      <!-- Display answer choices as radio buttons -->
      <div>
        <label v-for="choice in choices" :key="choice">
          <input type="radio" :value="choice" v-model="userAnswer" />
          {{ choice }}
        </label>
      </div>

      <!-- OK Button to go to the next question -->
      <button @click="nextQuestion">OK</button>
    </div>

    <!-- Final message after completing the quiz -->
    <div v-else>
      <div style="color: green; font-size: 1.5em; font-family: 'Times New Roman'; font-weight: bold;">
        You have completed the quiz! Your score was {{ score }} out of {{ questions.length }}.
        <i>Thank you for participating!</i>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      qno: 0, // current question number
      score: 0, // current score
      completed: false, // track if quiz is completed
      userAnswer: null, // track the user's selected answer
      questions: ['6 * 7 =', '23 + 10 =', 'The answer to everything is '],
      qanswers: ['42', '33', '42'], // correct answers
      choices: ['0', '1', '13', '33', '42'] // possible choices for all questions
    };
  },
  methods: {
    nextQuestion() {
      // Check the answer for the current question
      if (this.userAnswer === this.qanswers[this.qno]) {
        this.score++; // increment score if correct
      }

      // Move to the next question or finish
      this.qno++;

      if (this.qno >= this.questions.length) {
        // If no more questions, mark the quiz as completed
        this.completed = true;
      } else {
        this.userAnswer = null; // reset user answer for next question
      }
    }
  }
};
</script>

<style>
/* Styling the first two lines */
div:first-of-type, div:nth-of-type(2) {
  font-weight: bold;
  font-size: 1.2em;
}

/* Styling the choices (radio buttons) */
label {
  font-family: 'Courier New', Courier, monospace;
}

/* Styling for the final message */
div:last-of-type {
  color: green;
  font-size: 1.5em;
  font-family: 'Times New Roman';
  font-weight: bold;
}
i {
  font-style: italic;
}
</style>

