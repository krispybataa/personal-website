import React from 'react';
import { Link } from 'react-router-dom';

const Sheianne = () => {
    return (
        <div className="container mx-auto p-4">
            <h1 className="text-4xl mb-8">Sheianne's Page</h1>
            <ul>
                <li className="mb-4">
                    <Link to="/sheianne/todos" className="text-blue-500">Sheianne's To-do List</Link>
                </li>
                <li className="mb-4">
                    <Link to="/sheianne/music" className="text-blue-500">Sheianne's Favorite Music</Link>
                </li>
                <li className="mb-4">
                    <Link to="/sheianne/hobbies" className="text-blue-500">Sheianne's Hobbies</Link>
                </li>
            </ul>
        </div>
    );
};

export default Sheianne;
