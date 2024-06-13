import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import './Navbar.css';
import './index.css';
import logo from "./assets/LOGO.png";

const Navbar = ({ user }) => {
    const location = useLocation();
    const showLinks = location.pathname !== '/';

    return (
        <nav className="bg-gray-800 text-white p-4">
            <div className="container mx-auto flex justify-end">
                {/*<div></div>*/}
                {/*<div className="text-lg font-bold">*/}
                {/*    <Link to="/"><b>2BroCulosis</b></Link>*/}
                {/*</div>*/}
                <div className="flex navbar-center">
                    <a href="/">
                        <img src={logo} alt="Logo" className="logo"/> {/* Adjust the size as needed */}
                    </a>
                    <div className="flex space-x-4">
                        <a href="/sheianne">SHEIANNE</a>
                        <span className="separator"><b>|</b></span>
                        <a href="/clark">CLARK</a>
                    </div>
                </div>
                {/*{showLinks && (*/}
                {/*    <div className="flex space-x-4">*/}
                {/*        <Link to={`/${user}/todos`} className="hover:text-gray-400">*/}
                {/*            To-Do List*/}
                {/*        </Link>*/}
                {/*        <Link to={`/${user}/music`} className="hover:text-gray-400">*/}
                {/*            Favorite Music*/}
                {/*        </Link>*/}
                {/*        <Link to={`/${user}/hobbies`} className="hover:text-gray-400">*/}
                {/*        Hobbies*/}
                {/*        </Link>*/}
                {/*        <Link to="/" className="hover:text-gray-400">*/}
                {/*            Switch Character*/}
                {/*        </Link>*/}
                {/*    </div>*/}
                {/*)}*/}
            </div>
        </nav>
    );
};

export default Navbar;
