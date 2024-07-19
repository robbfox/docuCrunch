import { Link } from 'react-router-dom';
import '../styles/App.css';

import Hero from '../assets/Hero.png';
import Header from '../components/Header';
const Home = () => {
  return (
    <>
      <div className="Homepage">
        <Link to="/summarise">
          <Header />
        </Link>

        <section className="hero h-screen">
          <div className="hero-text">
            <h1>
              TEXT SUMMARISER :
              <span className="text-blue-200 text-6xl"> POWERED BY AI</span>{' '}
            </h1>
            <p>Summarise lengthy articles and paragraphs with DocuCrunch</p>
            <div className="flex justify-center mt-10">
              <Link
                to="/summarise"
                className="bg-[#5095e4] text-white px-10 py-2 rounded-md hover:bg-[#1b344d] transition-colors"
              >
                Start
              </Link>
            </div>
          </div>
          <div className="hero-image">
            <img src={Hero} alt="Hero" />
          </div>
        </section>
      </div>
    </>
  );
};

export default Home;
