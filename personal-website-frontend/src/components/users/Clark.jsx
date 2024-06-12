import React from 'react';
import { Link } from 'react-router-dom';
import clark from '../../assets/clark.png';
import '../../App.css';
import '../../index.css';

const Clark = () => {
    return (
        <div className="container gen-container">
            <div className="container user">
                <img src={clark} alt="Clark" className="w-40 h-40 rounded-full mb-4 clark-pfp"/><br></br>
                <h3 className="user-name">Augustus Clark Raphael P. Rodriguez</h3>
                <p className="description shan-desc">This is Clark's personal statement.</p>
            </div>
            <div className="container user-details">
                <h1 className="explore">Explore Clark's...</h1>
                <div className="pages music-page">
                    <Link to="/clark/music">
                        <button className="hover:bg-blue-700 rounded-lg favorites">
                            Favorite Music
                        </button>
                    </Link>
                </div>
                <div className="pages hobbies-page">
                    <Link to="/clark/hobbies">
                        <button className="hover:bg-blue-700 rounded-lg favorites">
                            Hobbies
                        </button>
                    </Link>
                </div>
                <div className="pages to-do-page">
                    <Link to="/clark/todos">
                        <button className="hover:bg-blue-700 rounded-lg favorites">
                            To-Do List
                        </button>
                    </Link>
                </div>
            </div>
        </div>
    );
};

export default Clark;
