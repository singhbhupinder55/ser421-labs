class Calculator {
    constructor(initialValue = 0) {
        this.value = initialValue;
        this.history = [];
        this.history.push(this.value);
    }

    // Method to perform calculations
    calc(inputString) {
        const input = JSON.parse(inputString);
        const operation = input.operation;
        const operand = input.operand;

        if (operation === "add") {
            this.value += operand;
        } else if (operation === "subtract") {
            this.value -= operand;
        }

        // Push the new value to the history stack
        this.history.push(this.value);
        return this.value;
    }

    // Method to undo the last operation
    undo() {
        if (this.history.length > 1) {
            this.history.pop(); // Remove the last operation
            this.value = this.history[this.history.length - 1]; // Set to the previous value
        } else {
            this.value = 0; // Reset to zero if there's no previous operation
        }
        return this.value;
    }

    // Method to peek at the top of the stack or a specific index
    peek(n = 0) {
        const index = this.history.length - 1 - n;
        return index >= 0 ? this.history[index] : null;
    }

    // Method to pop the top value off the stack
    pop() {
        if (this.history.length > 1) {
            return this.history.pop();
        }
        return null;
    }

    // Method to print the stack
    printMe() {
        console.log(this.history);
    }

    // Method to clear the stack and reset the calculator
    clear() {
        this.value = 0;
        this.history = [this.value];
    }
}

// Example usage:
const calculator = new Calculator(0);

// Perform some operations
console.log(calculator.calc('{"operation" : "add", "operand" : 5}'));  // returns 5
console.log(calculator.calc('{"operation" : "subtract", "operand" : 2}'));  // returns 3
console.log(calculator.calc('{"operation" : "add", "operand" : 19}'));  // returns 22

// Undo last operation
console.log(calculator.undo()); // returns 3

// Peek at the top of the stack
console.log(calculator.peek()); // returns 3

// Pop the top value from the stack
console.log(calculator.pop()); // returns 3

// Print the stack
calculator.printMe(); // prints [5]

// Clear the calculator
calculator.clear();
calculator.printMe(); // prints [0]
