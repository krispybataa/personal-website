import React from 'react';
import shan from '../../assets/sheianne.png';

import { Link } from 'react-router-dom';

const Sheianne = () => {
    return (
        <div className="container gen-container">
        <div className="container user">
            <img src={shan} alt="Sheianne" className="w-40 h-40 rounded-full mb-4 shan-pfp"/><br></br>
            <h3 className="user-name">Sheianne Deeno E. Seblante</h3>
            <p className="description shan-desc">This is Sheianne's personal statement.</p>
        </div>
        <div className="container user-details">
            <h1 className="explore">Explore Sheianne's...</h1>
            <div className="pages music-page">
                <Link to="/sheianne/music">
                    <button className="hover:bg-blue-700 rounded-lg">
                        Favorite Music
                    </button>
                </Link>
            </div>
            <div className="pages hobbies-page">
                <Link to="/sheianne/hobbies">
                    <button className="hover:bg-blue-700 rounded-lg">
                        Hobbies
                    </button>
                </Link>
            </div>
            <div className="pages to-do-page">
                <Link to="/sheianne/todos">
                    <button className="hover:bg-blue-700 rounded-lg">
                        To-Do List
                    </button>
                </Link>
            </div>
        </div>
        </div>
    );
};

export default Sheianne;
