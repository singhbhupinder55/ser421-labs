let storedValue = 0;

function calc(inputString) {
    const input = JSON.parse(inputString);
    const operation = input.operation;
    const operand = input.operand;

    if (operation === "add") {
        storedValue += operand;
    } else if (operation === "subtract") {
        storedValue -= operand;
    }

    return storedValue;
}

// Example usage:
console.log(calc('{"operation" : "add", "operand" : 5}'));  // returns 5
console.log(calc('{"operation" : "subtract", "operand" : 2}'));  // returns 3
console.log(calc('{"operation" : "add", "operand" : 19}'));  // returns 22
