// import React from 'react';
import React, {useEffect} from 'react';
import { Link } from 'react-router-dom';
import shan from './assets/sheianne.png';
import clark from './assets/clark.png';
import AOS from 'aos';
import 'aos/dist/aos.css';
import './App.css';
import './index.css';

const Home = () => {
    useEffect(() => {
        AOS.init({
            duration: 500
        })
    }, []);
    return (
        <div className="flex flex-col items-center justify-center h-screen">
            <div className="section">
                <h1 className="welcome">Welcome to 2Broculosis</h1>
            </div>
            <div className="bottom-page" data-aos="zoom-in">
                <br></br><h1 className="fighter" data-aos="fade-left">CHOOSE YOUR 'BRO'</h1>
                <div className="characters flex reveal space-x-10" data-aos="fade">
                    <Link to="/clark">
                        <div className="flex flex-col items-center cursor-pointer">
                            {/*REPLACE WITH PROPER ASSETS*/}
                            <img src={clark} alt="Clark" className="w-40 h-40 rounded-full mb-4"/><br></br>
                            <button className="hover:bg-blue-700 rounded-lg">
                                CLARK
                            </button>
                            <br></br>
                        </div>
                    </Link>
                    <Link to="/sheianne">
                        <div className="flex flex-col items-center cursor-pointer">
                            {/*REPLACE WITH PROPER ASSETS*/}
                            <img src={shan} alt="Sheianne" className="w-40 h-40 rounded-full mb-4"/><br></br>
                            <button className="hover:bg-green-700 rounded-lg">
                                SHEIANNE
                            </button>
                            <br></br>
                        </div>
                    </Link>
                </div>
            </div>
        </div>
    );
};

export default Home;
