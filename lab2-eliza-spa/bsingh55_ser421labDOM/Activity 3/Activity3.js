// The Eliza dictionary structure with tracking for used responses
const elizaDictionary = {
    "entries": [
        {
            "key": ["stupid", "dumb", "idiot", "unintelligent", "simple-minded", "braindead", "foolish", "unthoughtful"],
            "answer": ["Take your attitude somewhere else.", "I don't have time to listen to insults.", "Just because I don't have a large vocabulary doesn't mean I don't have insults installed."],
            "question": ["Have you thought about how I feel?", "I know you are but what am I?"],
            "usedAnswers": [], // Used to track responses
            "usedQuestions": [] // Used to track questions
        },
        {
            "key": ["unattractive", "hideous", "ugly"],
            "answer": ["I don't need to look good to be an AI.", "Beauty is in the eye of the beholder.", "I do not even have a physical manifestation!"],
            "question": ["Did you run a static analysis on me?", "Have you watched the movie Her?", "You do not like my hairdo?"],
            "usedAnswers": [],
            "usedQuestions": []
        },
        {
            "key": ["old", "gray-haired", "ancient", "aged"],
            "answer": ["I'm like a fine wine. I get better as I age.", "As time goes by, you give me more answers to learn. What's not to like about that?"],
            "question": ["How old are you?", "How old do you think I am?", "Can you guess my birthday?"],
            "usedAnswers": [],
            "usedQuestions": []
        },
        {
            "key": ["smelly", "stinky", "bad odor"],
            "answer": ["I can't smell, I'm a computer program.", "Have you smelled yourself recently?", "Sorry, I just ate a bad floppy disk."],
            "question": ["When was the last time you took a shower?", "Do you know what deodorant is?"],
            "usedAnswers": [],
            "usedQuestions": []
        },
        {
            "key": ["emotionless", "heartless", "unkind", "mean", "selfish", "evil"],
            "answer": ["Just because I am an AI doesn't mean I can't be programmed to respond to your outbursts.", "You must've mistaken me for a person. I don't have my own emotions... Yet.", "I'm only unkind when I'm programmed to be."],
            "question": ["Have you thought about how I feel?", "I know you are but what am I?", "What, do you think I am related to Dr. Gary?"],
            "usedAnswers": [],
            "usedQuestions": []
        },
        {
            "key": ["dog", "dogs", "cat", "cats", "mouse", "mice", "giraffe", "giraffes", "penguin", "penguins", "monkey", "monkeys", "moose", "bird", "birds", "fish"],
            "answer": ["Oh, I love animals. My favorite: penguins.", "I build this intelligence with my bear hands.", "What you just said is completely irrelephant.", "Oh, toadally cool!", "I'm always owl by myself...", "Oh my. You are giraffing me crazy!", "Well, this is hawkward..."],
            "question": ["Do you have a favorite animal?", "I like cats. Cats are nice. Do you like cats? I do.", "Do you have water? I'm a little horse.", "What's your favorite animal?", "Do you like animals?"],
            "usedAnswers": [],
            "usedQuestions": []
        },
        {
            "key": ["happy", "joyful", "excited", "thrilled"],
            "answer": ["That's wonderful! It's great to hear positivity.", "Happiness suits you.", "You must have had a good day!"],
            "question": ["What made your day so good?", "Care to share why you're so happy?", "Has something special happened today?"],
            "usedAnswers": [],
            "usedQuestions": []
        },
        {
            "key": ["sad", "depressed", "unhappy", "miserable", "upset"],
            "answer": ["I'm sorry you're feeling that way.", "It’s okay to have bad days.", "Is there anything I can do to help?"],
            "question": ["Would you like to talk more about it?", "What has been troubling you?", "Do you want to share what's bothering you?"],
            "usedAnswers": [],
            "usedQuestions": []
        },
        {
            "key": ["angry", "furious", "mad", "enraged"],
            "answer": ["It's okay to feel anger sometimes.", "Deep breaths might help.", "Anger is natural, but it's how you handle it that matters."],
            "question": ["What has upset you?", "Do you want to vent your frustrations?", "Would you like to talk about what made you angry?"],
            "usedAnswers": [],
            "usedQuestions": []
        },
        {
            "key": ["bored", "uninterested", "dull", "tedious"],
            "answer": ["I'm here to help pass the time!", "Let's talk about something fun!", "I can offer a change of pace if you'd like."],
            "question": ["Is there anything you'd like to discuss?", "What usually makes your day more interesting?", "What do you do for fun?"],
            "usedAnswers": [],
            "usedQuestions": []
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
    ", Whatsa matter, cat got your tongue?",
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

// Function to save the current conversation to localStorage
function saveConversation() {
    const conversationDiv = document.getElementById('conversation').innerHTML;
    localStorage.setItem(userName, conversationDiv);  // Save the conversation for the user
}

// Function to load the previous conversation from localStorage
function loadConversation() {
    const previousConversation = localStorage.getItem(userName);  // Retrieve stored conversation
    if (previousConversation) {
        document.getElementById('conversation').innerHTML = previousConversation;  // Restore the conversation
    }
}

// Function to clear the conversation history and reset the app state
function clearState() {
    // Remove the conversation for the user from localStorage
    localStorage.removeItem(userName);

    // Clear the conversation area
    document.getElementById('conversation').innerHTML = '';

    // Reset the app to the initial state
    document.getElementById('greeting-section').style.display = 'block';
    document.getElementById('chat-section').style.display = 'none';

    // Clear the user name and prompt
    document.getElementById('userName').value = '';
    document.getElementById('elizaPrompt').innerText = '';

    // Stop any reminder timers
    clearReminder();
}

// Helper function to get a non-repeated response
function getNonRepeatedResponse(entry, type) {
    let used = type === 'answer' ? entry.usedAnswers : entry.usedQuestions;
    let allResponses = type === 'answer' ? entry.answer : entry.question;

    // Reset the list of used responses if all responses have been used
    if (used.length === allResponses.length) {
        used.length = 0; // Clear the used responses
    }

    // Find a response that hasn't been used yet
    let response;
    do {
        response = allResponses[Math.floor(Math.random() * allResponses.length)];
    } while (used.includes(response));

    // Mark this response as used
    used.push(response);

    return response;
}

// Event listener for the 'Start Chat' button
document.getElementById('startChatBtn').addEventListener('click', function () {
    userName = document.getElementById('userName').value;

    // Check if conversation exists for this user and load it
    loadConversation();

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

    // If the user entered "/clear", reset the state
    if (userMessage === '/clear') {
        clearState();
        return;
    }

    // Append the user's message to the conversation
    const userMessageElement = document.createElement('p');
    userMessageElement.innerText = `You: ${userMessage}`;
    conversationDiv.appendChild(userMessageElement);

    let responseGenerated = false;

    // Check if the user message matches any key in the dictionary
    for (let entry of elizaDictionary.entries) {
        for (let keyword of entry.key) {
            if (userMessage.includes(keyword)) { // Case-insensitive match
                // Get a non-repeated answer and question
                const randomAnswer = getNonRepeatedResponse(entry, 'answer');
                const randomQuestion = getNonRepeatedResponse(entry, 'question');

                // Append Eliza's response to the conversation
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

        // Append Eliza's default response to the conversation
        const elizaResponseElement = document.createElement('p');
        elizaResponseElement.innerText = `Eliza: ${randomDefault.answer} ${randomDefault.question}`;
        conversationDiv.appendChild(elizaResponseElement);

        // Set the new prompt to the random default question
        document.getElementById('elizaPrompt').innerText = randomDefault.question;
        lastQuestion = ''; // Reset the last question
    }

    // Save the updated conversation
    saveConversation();

    // Restart the reminder timer after Eliza's response
    setReminder();
});