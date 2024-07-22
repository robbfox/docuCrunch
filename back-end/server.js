const express = require('express');
const axios = require('axios');
const cors = require('cors');

const app = express();
const PORT = 5000;

// Middleware
app.use(cors());
app.use(express.json());

app.post('/summarize', async (req, res) => {
  const { inputText } = req.body;

  const url = 'https://portal.ayfie.com/api/summarize';
  const body = {
    language: 'auto',
    text: inputText,
    min_length: 5,
    max_length: 100
  };
  
  try {
    const response = await axios.post(url, body, {
      headers: {
        'Content-Type': 'application/json',
        'x-api-key': 'PetZeCKQGfTjHxDjvJGYBsBYlIRuOkNpFwAAWKMAlmkdJxwRan' // Replace with your actual API key
      }
    });

    res.json(response.data);
  } catch (error) {
    console.error('Error summarizing text:', error);
    if (error.response) {
      res.status(error.response.status).json(error.response.data);
    } else {
      res.status(500).json({ message: 'Failed to summarize the text. Please check your network connection and API key.' });
    }
  }
});

app.listen(PORT, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});
