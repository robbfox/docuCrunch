import { Link } from 'react-router-dom';
import '../styles/App.css';

import Hero from '../assets/Hero.png';
import Header from '../components/Header';
const Home = () => {
  return (
    <>
      <div className="Homepage">
        <Header />
        <section className="hero h-screen">
          <div className="hero-text">
            <h1>TEXT SUMMARISER: POWERED BY AI</h1>
            <p>Summarise lengthy articles and paragraphs with DocuCrunch</p>
            <Link to="/start" className="btn-start">
              Start
            </Link>
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
