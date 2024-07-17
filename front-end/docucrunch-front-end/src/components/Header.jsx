import crunchLogo from '../assets/docuCrunchLogo.png';

function Header() {
  <>
    <div className="flex justify-start w-100 fixed top-0 bg-pink">
      <div className="flex justify-start w-100">
        <img src={crunchLogo} className="h-8" alt="react logo" />
      </div>
    </div>
  </>;
}

export default Header;
