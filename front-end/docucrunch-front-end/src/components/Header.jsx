import React from 'react';
import crunchLogo from '../assets/logo.png';
import '../styles/App.css';
function header() {
  return (
    <>
      <div className="top-0 h-20 fixed rounded-sm  w-full ">
        <img src={crunchLogo} alt="DocuCrunch Logo" className="logo" />
      </div>
    </>
  );
}

export default header;
