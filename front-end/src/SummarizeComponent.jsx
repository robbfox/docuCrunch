import React, { useState } from 'react';
import axios from 'axios';

const SummarizeComponent = () => {
  const [inputText, setInputText] = useState('');
  const [summary, setSummary] = useState('');
  const [error, setError] = useState('');

  const handleSummarize = async () => {
    const url = 'https://api-inference.huggingface.co/models/facebook/bart-large-cnn'; // Example model from Hugging Face

    const body = {
      inputs: inputText,
      parameters: {
        max_new_tokens: 100,
        min_length: 5,
        max_length: 100
      }
    };

    try {
      console.log('Sending request to:', url);
      console.log('Request body:', body);

      const response = await axios.post(url, body, {
        headers: {
          'Authorization': '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', // Replace with your actual API key
          'Content-Type': 'application/json'
        }
      });

      console.log('Response status:', response.status);
      console.log('Response data:', response.data);

      if (response.status === 200 && response.data && response.data.length > 0) {
        setSummary(response.data[0].generated_text); // Assuming the response structure matches
        setError('');
      } else {
        setError('Failed to summarize the text. Please try again.');
      }
    } catch (error) {
      console.error('Error summarizing text:', error);
      setError('Failed to summarize the text. Please check your network connection and API key.');
    }
  };

  return (
    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
      <textarea
        value={inputText}
        onChange={(e) => setInputText(e.target.value)}
        placeholder="Enter text to summarize"
        style={{ width: '80%', height: '200px', margin: '20px 0', padding: '10px' }}
      />
      <button onClick={handleSummarize} style={{ marginBottom: '20px' }}>Summarize</button>
      {summary && (
        <div style={{ border: '1px solid #ccc', borderRadius: '5px', padding: '10px', marginTop: '20px' }}>
          <strong>Summary:</strong>
          <div>{summary}</div>
        </div>
      )}
      {error && <div style={{ color: 'red', marginTop: '20px' }}>{error}</div>}
    </div>
  );
};

export default SummarizeComponent;
