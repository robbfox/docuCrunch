import '../styles/App.css';
import Header from './Header.jsx';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import fileUpload from '../assets/FileUpload.png';
import pdfToText from 'react-pdftotext';
import { IoMdCopy } from 'react-icons/io';
import axios from 'axios';

function SummarisePage() {
  const [textInput, setTextInput] = useState('');
  const [summary, setSummary] = useState('');
  const [summaryType, setSummaryType] = useState('articles');
  function extractText(event) {
    const file = event.target.files[0];
    pdfToText(file)
      .then((text) => setTextInput(text))
      .catch((error) => console.error('Failed to extract text from pdf'));
  }
  async function handlesubmit(e) {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/summarise', {
        text: textInput,
        type: summaryType,
      });
      console.log(response.data);
      setSummary(response.data);
    } catch (error) {
      console.error(
        'Failed to fecth summary from the server.Please try again later',
        error
      );
    }
  }
  function handleCopy() {
    navigator.clipboard.writeText(summary).then(
      () => {
        alert('Summary copied to clipboard!');
      },
      (err) => {
        console.error('Could not copy text: ', err);
      }
    );
  }

  return (
    <div>
      <Link to="/">
        <Header />
      </Link>
      <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100 p-6">
        <div className="bg-white shadow-md rounded-lg p-10 w-full max-w-4xl">
          <div className="flex flex-col md:flex-row">
            <div className="w-full md:w-1/2 p-4 border-r border-gray-200">
              <label
                htmlFor="file-upload"
                className="flex flex-col items-center justify-center h-full border-2 border-dashed border-gray-300 p-4 cursor-pointer hover:bg-gray-50 transition-colors"
              >
                <img src={fileUpload} alt="Upload File" className="" />
                <p className="text-gray-500">Please upload your file here</p>
                <input
                  id="file-upload"
                  type="file"
                  accept="application/pdf"
                  className="hidden"
                  onChange={extractText}
                />
              </label>
            </div>
            <div className="w-full md:w-1/2 p-4">
              <div className="border border-gray-300 p-4 rounded-md h-full bg-gray-50">
                <textarea
                  value={summary}
                  onChange={(e) => setSummary(e.target.value)}
                  className="w-full h-full p-2 border-none bg-gray-50 focus:outline-none"
                  placeholder="Your summarised text will appear here"
                />
                <button
                  className=" text-gray-500 absolute bottom-[35%] right-[47%]  hover:text-blue-700"
                  onClick={handleCopy}
                >
                  <IoMdCopy />
                </button>
              </div>
            </div>
          </div>
        </div>
        <div className="mt-6 flex justify-between items-center">
          <div>
            <label htmlFor="summary-type" className="mr-4 text-[#737373]">
              Document type:
            </label>
            <label className="mr-4">
              <input
                type="radio"
                value="articles"
                checked={summaryType === 'articles'}
                onChange={(e) => setSummaryType(e.target.value)}
                className="mr-2"
              />
              Article
            </label>
            <label className="mr-4">
              <input
                type="radio"
                value="minutes"
                checked={summaryType === 'minutes'}
                onChange={(e) => setSummaryType(e.target.value)}
                className="mr-2"
              />
              Minutes
            </label>
          </div>
          <button
            className="bg-[#5095e4] text-white px-6 py-2 rounded-md hover:bg-[#1b344d] transition-colors"
            onClick={handlesubmit}
          >
            Summarise
          </button>
        </div>
      </div>
    </div>
  );
}
export default SummarisePage;
