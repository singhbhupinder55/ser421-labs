<template>
  <div>
    <h2>Currency Converter</h2>
    <input type="number" v-model="localAmount" @input="updateAmount" />
    <select v-model="selectedFromCurrency" @change="updateCurrency">
      <option value="USD">US Dollar</option>
      <option value="BHD">Bahraini Dinar</option>
    </select>
    <select v-model="toCurrency">
      <option value="INR">Indian Rupee</option>
    </select>
    <p>{{ localAmount }} {{ selectedFromCurrency }} equals {{ convertedAmount }} {{ toCurrency }}</p>
  </div>
</template>

<script>
export default {
  props: {
    amount: {
      type: Number,
      required: true
    },
    fromCurrency: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      localAmount: this.amount,
      selectedFromCurrency: this.fromCurrency,
      toCurrency: 'INR',
      conversionRates: {
        USD: {
          INR: 73.50
        },
        BHD: {
          INR: 198.45
        }
      }
    };
  },
  computed: {
    convertedAmount() {
      const rate = this.conversionRates[this.selectedFromCurrency]?.[this.toCurrency];
      return rate ? (this.localAmount * rate).toFixed(2) : 'Conversion rate not available';
    }
  },
  methods: {
    updateAmount() {
      this.$emit('currencyUpdated', this.localAmount);
    },
    updateCurrency() {
      this.$emit('currencyUpdated', this.selectedFromCurrency);
    }
  },
  watch: {
    amount(newAmount) {
      this.localAmount = newAmount;
    },
    fromCurrency(newCurrency) {
      this.selectedFromCurrency = newCurrency;
    }
  }
};
</script>

<style scoped>
/* Add any styles here */
</style>
