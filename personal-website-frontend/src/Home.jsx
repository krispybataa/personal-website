import React from 'react';
import { Link } from 'react-router-dom';

const Home = () => {
    return (
        <div className="flex flex-col items-center justify-center h-screen bg-gray-900 text-white">
            <h1 className="text-4xl mb-8">Choose your Fighter</h1>
            <div className="flex space-x-10">
                <Link to="/clark">
                    <div className="flex flex-col items-center cursor-pointer">
                        {/*REPLACE WITH PROPER ASSETS*/}
                        <img src="https://media.cnn.com/api/v1/images/stellar/prod/160107100400-monkey-selfie.jpg?q=w_2912,h_1638,x_0,y_0,c_fill" alt="Clark" className="w-40 h-40 rounded-full mb-4" />
                        <button className="px-4 py-2 bg-blue-600 hover:bg-blue-700 rounded-lg">
                            CLARK
                        </button>
                    </div>
                </Link>
                <Link to="/sheianne">
                    <div className="flex flex-col items-center cursor-pointer">
                        {/*REPLACE WITH PROPER ASSETS*/}
                        <img src="https://images.newscientist.com/wp-content/uploads/2022/04/06095721/SEI_97255809.jpg?crop=1:1,smart&width=1200&height=1200&upscale=true" alt="Sheianne" className="w-40 h-40 rounded-full mb-4" />
                        <button className="px-4 py-2 bg-green-600 hover:bg-green-700 rounded-lg">
                            SHEIANNE
                        </button>
                    </div>
                </Link>
            </div>
        </div>
    );
};

export default Home;
