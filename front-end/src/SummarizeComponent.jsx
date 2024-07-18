import React, { useState } from 'react';
import axios from 'axios';

const SummarizeComponent = () => {
  const [inputText, setInputText] = useState('');
  const [summary, setSummary] = useState('');
  const [error, setError] = useState('');

  const handleSummarize = async () => {
    const url = 'http://localhost:5000/summarize'; // URL of your proxy server
    const body = {
      inputText
    };

    try {
      console.log('Sending request to:', url);
      console.log('Request body:', body);

      const response = await axios.post(url, body, {
        headers: {
          'Content-Type': 'application/json'
        }
      });

      console.log('Response status:', response.status);
      console.log('Response data:', response.data);

      if (response.status === 200 && response.data.result) {
        setSummary(response.data.result); // Use the 'result' field from the response
        setError('');
      } else {
        setError('Failed to summarize the text. Please try again.');
      }
    } catch (error) {
      console.error('Error summarizing text:', error);
      if (error.response) {
        console.error('Response data:', error.response.data);
        setError(`Failed to summarize the text: ${error.response.data.message}`);
      } else {
        setError('Failed to summarize the text. Please check your network connection and API key.');
      }
    }
  };

  return (
    <div>
      <textarea
        value={inputText}
        onChange={(e) => setInputText(e.target.value)}
        placeholder="Enter text to summarize"
      />
      <button onClick={handleSummarize}>Summarize</button>
      {summary && <div>Summary: {summary}</div>}
      {error && <div style={{ color: 'red' }}>{error}</div>}
    </div>
  );
};

export default SummarizeComponent;
