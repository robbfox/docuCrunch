import React from 'react';
import crunchLogo from '../assets/logo.png';
import '../App.css';
function Header() {
  return (
    <>
      <div className="top-0 py-5 fixed rounded-sm  w-full ">
        <img src={crunchLogo} alt="DocuCrunch Logo" className="logo" />
      </div>
    </>
  );
}

export default Header;
