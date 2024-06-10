import React, { useState } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './Home';
import Navbar from "./Navbar";
import Clark from './components/users/Clark';
import Sheianne from './components/users/Sheianne';
import Todos from './components/pages/Todos';
import Music from './components/pages/Music';
import Hobbies from './components/pages/Hobbies';

const App = () => {
    const [currentUser, setCurrentUser] = useState('clark');

    return (
        <Router>
            <Navbar user={currentUser} />
            <Routes>
                <Route path="/" element={<Home setuser={setCurrentUser} />} />
                <Route path="/clark" element={<Clark />} />
                <Route path="/sheianne" element={<Sheianne />} />
                <Route path="/clark/todos" element={<Todos userId={1} />} />
                <Route path="/clark/music" element={<Music userId={1} />} />
                <Route path="/clark/hobbies" element={<Hobbies userId={1} />} />
                <Route path="/sheianne/todos" element={<Todos userId={2} />} />
                <Route path="/sheianne/music" element={<Music userId={2} />} />
                <Route path="/sheianne/hobbies" element={<Hobbies userId={2} />} />
            </Routes>
        </Router>
    );
};
export default App;
