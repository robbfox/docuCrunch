import { Link } from 'react-router-dom';
import './index.css'
import Hero from '../../assets/Hero.png'


const Home = () => {

    return (
        <div className="Homepage">
            <section className="hero">
                <div className="hero-text">
                    <h1>TEXT SUMMARISER: POWERED BY AI</h1>
                    <p>Summarise lengthy articles and paragraphs with DocuCrunch</p>
                    <Link to="/start" className="btn-start">Start</Link>
                </div>
                <div className="hero-image">
                    <img src={Hero} alt="Hero"/>
                </div>
            </section>
        </div>
    )


}

export default Home;