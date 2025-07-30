// 1. Rename "More Results"
const moreResults = document.getElementById('more-results');
if (moreResults) moreResults.textContent = "More redundant Results!";

// 2. Count <li> tags
console.log(document.getElementsByTagName('li').length);

// 3. Get value from search bar
console.log(document.querySelector('input[name="q"]').value);

// 4. Remove the duck logo properly
const logo = document.querySelector('.header__logo.js-logo-ddg');
if (logo) logo.remove();
