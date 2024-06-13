import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './Hobbies.css';

const Hobbies = ({ userId }) => {
    const [hobbiesList, setHobbiesList] = useState([]);
    const [userName, setUserName] = useState('');

    useEffect(() => {
        // Fetch user details
        fetch(`http://localhost:8080/api/v1/users/${userId}`)
            .then(response => response.json())
            .then(data => {
                console.log('User data:', data);
                setUserName(data.name);
            })
            .catch(error => console.error('Error fetching user details:', error));

        // Fetch hobbies
        fetch(`http://localhost:8080/api/v1/hobbies/users/${userId}`)
            .then(response => response.json())
            .then(data => {
                console.log('Hobbies data:', data);
                setHobbiesList(data);
            })
            .catch(error => console.error('Error fetching hobbies:', error));
    }, [userId]);

    // Logic to handle flexible media rendering
    const renderMedia = (media) => {
        if (!media) {
            return <p>No media available</p>;
        }
        const youtubeRegex = /(?:youtube\.com\/(?:[^\/]+\/.+\/|(?:v|e(?:mbed)?)\/|.*[?&]v=)|youtu\.be\/)([^"&?\/\s]{11})/i;
        const imageRegex = /\.(jpeg|jpg|gif|png)$/;
        const videoRegex = /\.(mp4|webm|ogg)$/;
        const audioRegex = /\.(mp3|wav|ogg)$/;

        if (youtubeRegex.test(media)) {
            const videoId = media.match(youtubeRegex)[1];
            return (
                <div className="youtube-container">
                    <iframe
                        src={`https://www.youtube.com/embed/${videoId}`}
                        title="YouTube video player"
                        frameBorder="0"
                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                        allowFullScreen
                    ></iframe>
                </div>
            );
        } else if (imageRegex.test(media)) {
            return <img src={media} alt="Hobby media" className="hobby-media" />;
        } else if (videoRegex.test(media)) {
            return <video src={media} controls className="hobby-media"></video>;
        } else if (audioRegex.test(media)) {
            return <audio src={media} controls className="hobby-media"></audio>;
        } else {
            return <a href={media} target="_blank" rel="noopener noreferrer" className="text-blue-500 underline">Information</a>;
        }
    };

    return (
        <div className="hobbies-container">
            <h1 className="hobbies-header"> {userName}'s Hobbies</h1>
            <div className="grid">
                {hobbiesList.map(hobby => (
                    <div key={hobby.id} className="card">
                        <strong>{hobby.name}</strong>
                        <p>{hobby.description}</p>
                        <div>
                            {renderMedia(hobby.media)}
                        </div>
                    </div>
                ))}
            </div>
            <div className="todo-buttons">
                <div className="pages">
                    <Link to={`/${userId}/music`}>
                        <button className="hover:bg-blue-700 rounded-lg favorites">
                            Favorite Music
                        </button>
                    </Link>
                </div>
                <div className="pages">
                    <Link to={`/${userId}/todos`}>
                        <button className="hover:bg-blue-700 rounded-lg favorites">
                            To-Do
                        </button>
                    </Link>
                </div>
            </div>
        </div>
    );
};
export default Hobbies;
