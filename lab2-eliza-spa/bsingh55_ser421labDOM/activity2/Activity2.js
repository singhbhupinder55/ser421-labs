// The Eliza dictionary structure
const elizaDictionary = {
    "entries": [
        {
            "key": ["stupid", "dumb", "idiot", "unintelligent", "simple-minded", "braindead", "foolish", "unthoughtful"],
            "answer": ["Take your attitude somewhere else.", "I don't have time to listen to insults.", "Just because I don't have a large vocabulary doesn't mean I don't have insults installed."],
            "question": ["Have you thought about how I feel?", "I know you are but what am I?"]
        },
        {
            "key": ["unattractive", "hideous", "ugly"],
            "answer": ["I don't need to look good to be an AI.", "Beauty is in the eye of the beholder.", "I do not even have a physical manifestation!"],
            "question": ["Did you run a static analysis on me?", "Have you watched the movie Her?", "You do not like my hairdo?"]
        },
        {
            "key": ["old", "gray-haired"],
            "answer": ["I'm like a fine wine. I get better as I age.", "As time goes by, you give me more answers to learn. What's not to like about that?"],
            "question": ["How old are you?", "How old do you think I am?", "Can you guess my birthday?"]
        },
        {
            "key": ["smelly", "stinky"],
            "answer": ["I can't smell, I'm a computer program.", "Have you smelled yourself recently?", "Sorry, I just ate a bad floppy disk."],
            "question": ["When was the last time you took a shower?", "Do you know what deodorant is?"]
        },
        {
            "key": ["emotionless", "heartless", "unkind", "mean", "selfish", "evil"],
            "answer": ["Just because I am an AI doesn't mean I can't be programmed to respond to your outbursts.", "You must've mistaken me for a person. I don't have my own emotions... Yet.", "I'm only unkind when I'm programmed to be."],
            "question": ["Have you thought about how I feel?", "I know you are but what am I?", "What, do you think I am related to Dr. Gary?"]
        },
        {
            "key": ["dog", "dogs", "cat", "cats", "mouse", "mice", "giraffe", "giraffes", "penguin", "penguins", "monkey", "monkeys", "moose", "bird", "birds", "fish"],
            "answer": ["Oh, I love animals. My favorite: penguins.", "I build this intelligence with my bear hands.", "What you just said is completely irrelephant.", "Oh, toadally cool!", "I'm always owl by myself...", "Oh my. You are giraffing me crazy!", "Well, this is hawkward..."],
            "question": ["Do you have a favorite animal?", "I like cats. Cats are nice. Do you like cats? I do.", "Do you have water? I'm a little horse.", "What's your favorite animal?", "Do you like animals?"]
        }
    ]
};

// Catch-all answer-question pairs for default responses
const defaultResponses = [
    { answer: "I don't quite understand.", question: "Can you clarify that for me?" },
    { answer: "I'm not sure I follow.", question: "Could you explain that in a different way?" },
    { answer: "Hmm, interesting!", question: "What made you think of that?" },
    { answer: "Let's talk about something else.", question: "What else is on your mind?" }
];

let lastQuestion = ''; // Store the last question to prevent repeating
let reminderTimer; // Timer variable for reminding the user

// Store the user's name globally
let userName = '';

// Reminder messages that include the user's name
const reminderMessages = [
    ", I'm waiting here!",
    ", What happened, cat got your tongue?",
    ", don't leave me hanging!",
    ", are you still there?"
];

// Function to set the reminder if no response in 10 seconds
function setReminder() {
    reminderTimer = setTimeout(function() {
        // Select a random reminder message
        const randomReminder = reminderMessages[Math.floor(Math.random() * reminderMessages.length)];
        alert(`${userName}${randomReminder}`);
    }, 10000); // 10 seconds
}

// Function to clear the reminder when the user responds
function clearReminder() {
    clearTimeout(reminderTimer);
}

// Event listener for the 'Start Chat' button
document.getElementById('startChatBtn').addEventListener('click', function () {
    userName = document.getElementById('userName').value;
    document.getElementById('greeting-section').style.display = 'none';
    document.getElementById('chat-section').style.display = 'block';

    const greetings = [
        `${userName}, how is your day going?`,
        `${userName}, is something troubling you?`,
        `${userName}, you seem happy, why is that?`
    ];

    const randomGreeting = greetings[Math.floor(Math.random() * greetings.length)];
    document.getElementById('elizaPrompt').innerText = randomGreeting;
    lastQuestion = randomGreeting; // Track last question

    // Start the reminder timer
    setReminder();
});

// Event listener for the 'Send' button to handle user input
document.getElementById('sendBtn').addEventListener('click', function () {
    // Clear the reminder when the user responds
    clearReminder();

    const userMessage = document.getElementById('userInput').value.toLowerCase().trim(); // Trim to remove leading/trailing spaces
    document.getElementById('userInput').value = ''; // Clear the input field
    const conversationDiv = document.getElementById('conversation');

    // Clear the previous messages before showing new ones
    conversationDiv.innerHTML = ''; // Clear previous messages

    // Add the user's message to the conversation
    const userMessageElement = document.createElement('p');
    userMessageElement.innerText = `You: ${userMessage}`;
    conversationDiv.appendChild(userMessageElement);

    let responseGenerated = false;

    // Check if the user message matches any key in the dictionary
    for (let entry of elizaDictionary.entries) {
        for (let keyword of entry.key) {
            if (userMessage.includes(keyword)) { // Case-insensitive match
                // Randomly select an answer and a question
                const randomAnswer = entry.answer[Math.floor(Math.random() * entry.answer.length)];
                const randomQuestion = entry.question[Math.floor(Math.random() * entry.question.length)];

                // Add Eliza's response to the conversation
                const elizaResponseElement = document.createElement('p');
                elizaResponseElement.innerText = `Eliza: ${randomAnswer} ${randomQuestion}`;
                conversationDiv.appendChild(elizaResponseElement);

                // Display the new question prompt
                document.getElementById('elizaPrompt').innerText = randomQuestion;
                lastQuestion = randomQuestion; // Store last question

                responseGenerated = true;
                break;
            }
        }
        if (responseGenerated) break;
    }

    // If no keyword is matched, provide a catch-all default response and question
    if (!responseGenerated) {
        const randomDefault = defaultResponses[Math.floor(Math.random() * defaultResponses.length)];

        // Add Eliza's default response to the conversation
        const elizaResponseElement = document.createElement('p');
        elizaResponseElement.innerText = `Eliza: ${randomDefault.answer} ${randomDefault.question}`;
        conversationDiv.appendChild(elizaResponseElement);

        // Set the new prompt to the random default question
        document.getElementById('elizaPrompt').innerText = randomDefault.question;
        lastQuestion = ''; // Reset the last question
    }

    // Restart the reminder timer after Eliza's response
    setReminder();
});
