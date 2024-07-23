
import { useState } from 'react';
import reactLogo from './assets/react.svg';
import viteLogo from '/vite.svg';
import Home from './temp/index.jsx';
import Layout from './temp/Layout/index.jsx';
import { Router, Routes, Route } from 'react-router-dom';
import Summarise from './temp/summarise.jsx';
import './styles/App.css';

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="/summarise" element={<Summarise />} />
        </Route>
      </Routes>
    </>

  );
};

export default App;
