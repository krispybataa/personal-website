import React from 'react';
import { Link } from 'react-router-dom';

const Clark = () => {
    return (
        <div className="container mx-auto p-4">
            <h1 className="text-4xl mb-8">Clark's Page</h1>
            <ul>
                <li className="mb-4">
                    <Link to="/clark/todos" className="text-blue-500">Clark's To-do List</Link>
                </li>
                <li className="mb-4">
                    <Link to="/clark/music" className="text-blue-500">Clark's Favorite Music</Link>
                </li>
                <li className="mb-4">
                    <Link to="/clark/hobbies" className="text-blue-500">Clark's Hobbies</Link>
                </li>
            </ul>
        </div>
    );
};

export default Clark;
