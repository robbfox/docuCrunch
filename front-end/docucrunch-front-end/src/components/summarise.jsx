import '../styles/App.css';
import Header from './Header.jsx';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import fileUpload from '../assets/FileUpload.png';
import { FaRegTrashAlt } from 'react-icons/fa';
import pdfToText from 'react-pdftotext';
import { IoMdCopy } from 'react-icons/io';
import axios from 'axios';
import { IoMdClose } from 'react-icons/io';
import { TbAlertCircle } from 'react-icons/tb';
function SummarisePage() {
  const [textInput, setTextInput] = useState('');
  const [summary, setSummary] = useState('');
  const [summaryType, setSummaryType] = useState('articles');
  const [alertMessage, setAlertMessage] = useState('');
  function extractText(event) {
    const file = event.target.files[0];
    if (!file) {
      setAlertMessage('No file selected');
      return;
    }

    if (file.type !== 'application/pdf') {
      setAlertMessage('Unsupported file format. Please upload a PDF file');
    }
    pdfToText(file)
      .then((text) => setTextInput(text))
      .catch((error) => {
        console.error('Failed to extract text from pdf', error);
        setAlert('Failed to extract text from PDF. Please try again later');
      });
  }
  async function handlesubmit(e) {
    e.preventDefault();

    if (!textInput.trim()) {
      setAlertMessage('No input provided. Please upload a file and try again');
      return;
    }
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
      setAlertMessage(
        'Failed to fetch summary from the server. Please make sure you have selected the correct input and try again later'
      );
    }
  }
  function handleCopy() {
    navigator.clipboard.writeText(summary).then(
      () => {
        alert('copied to clipboard.');
      },
      (err) => {
        console.error('Could not copy text: ', err);
      }
    );
  }
  function handleClear() {
    setTextInput('');
    setSummary('');
  }

  return (
    <div>
      <Link to="/">
        <Header />
      </Link>
      <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100 p-6">
        <div className="bg-white shadow-md rounded-lg p-10 w-full max-w-7xl h-[80%]">
          {alertMessage && (
            <div className="mb-4 p-4 bg-[#fbeaea] text-red-800  rounded-md">
              <TbAlertCircle
                size={25}
                className="float-left  text-red-9000 mr-2"
              />
              {alertMessage}
              <button
                onClick={() => setAlertMessage('')}
                className="float-right text-red-9000"
              >
                <IoMdClose />
              </button>
            </div>
          )}
        </div>
        <div className="bg-white shadow-md rounded-lg p-10 w-full max-w-7xl h-[80%]">
          <div className="flex flex-col md:flex-row gap-4">
            {/* file upload component */}
            <div className="w-full md:w-1/3 p-4 border-r border-gray-200">
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

            {/* text input and summary display */}
            <div className="w-full md:w-3/3 p-4">
              <div className="flex flex-col md:flex-row gap-4">
                {/* user input extracted */}
                <div className="flex-1 border border-gray-300 p-4 rounded-md bg-gray-50">
                  <h2 className="text-xl text-center font-semibold mb-2 text-[#1b344d] border-b border-1 border-[#5095e410]">
                    Input text
                  </h2>
                  <textarea
                    value={textInput}
                    onChange={(e) => setTextInput(e.target.value)}
                    className="w-full h-64 p-2 border-none bg-gray-50 focus:outline-none resize-none"
                    placeholder=""
                  />
                </div>

                {/* summary output display */}
                <div className="flex-1 border border-gray-300 p-4 rounded-md bg-gray-50 relative">
                  <h2 className="text-xl text-center font-semibold mb-2 text-[#1b344d] border-b border-1 border-[#5095e410]">
                    Summary
                  </h2>
                  <textarea
                    value={summary}
                    onChange={(e) => setSummary(e.target.value)}
                    className="w-full h-64 p-2 border-none bg-gray-50 focus:outline-none resize-none"
                    placeholder=""
                  />
                  <button
                    className="absolute bottom-4 right-4 text-gray-500 hover:text-blue-700"
                    onClick={handleCopy}
                  >
                    <IoMdCopy />
                  </button>
                </div>
              </div>
            </div>
          </div>
          {/* clear text area component */}
          <div className="flex justify-end w-100">
            <FaRegTrashAlt
              className="hover:text-[#5095e4]"
              onClick={handleClear}
            />
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
