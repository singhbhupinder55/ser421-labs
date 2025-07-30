<template>
  <div>
    <h2>Account Balance: {{ balance }} {{ currency }}</h2>
    <label for="slider">Change amount to: </label>
    <input id="slider" type="range" v-model="amount" min="5" max="100" step="5" />
    <p>{{ amount }}</p>
    <button @click="addAmount">Add</button>
    <button @click="subtractAmount" :disabled="amount > balance">Subtract</button>
  </div>
</template>

<script>
export default {
  props: ['currency'],
  data() {
    return {
      balance: 100,
      amount: 25
    };
  },
  methods: {
    addAmount() {
      this.balance += this.amount;
    },
    subtractAmount() {
      if (this.amount <= this.balance) {
        this.balance -= this.amount;
      }
    }
  }
}
</script>

