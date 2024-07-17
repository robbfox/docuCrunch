import { useState } from 'react';
import reactLogo from './assets/react.svg';
import viteLogo from '/vite.svg';
import Home from './Components/Homepage/index.jsx'
import Layout from './Components/Layout/index.jsx'
import { Router, Routes, Route } from 'react-router-dom'
import './App.css';

function App() {
  const [count, setCount] = useState(0);

  return (
    <>
        <Routes>
            <Route path = "/" element={<Layout />} >
                <Route index element={<Home />} />
            </Route>
        </Routes>
    </>
  );
}

export default App;
