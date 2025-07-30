<template>
  <div>
    <HelloWorld :name="userName" />
    <Balance :balance="balance" :amount.sync="amount" />
    <Currency :amount="amount" :fromCurrency="currency" />
  </div>
</template>

<script>
import HelloWorld from './components/HelloWorld.vue';
import Balance from './components/Balance.vue';
import Currency from './components/Currency.vue';

export default {
  components: {
    HelloWorld,
    Balance,
    Currency
  },
  data() {
    return {
      userName: 'Bhupinder Singh',
      balance: 100,
      amount: 25,
      currency: 'USD'
    };
  }
};
</script>
