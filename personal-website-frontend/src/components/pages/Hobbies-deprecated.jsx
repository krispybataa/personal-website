import React, { useState, useEffect } from 'react';
import MediaModal from '../MediaModal';

const Hobbies = ({ userId }) => {
    const [hobbiesList, setHobbiesList] = useState([]);
    const [userName, setUserName] = useState('');
    const [formData, setFormData] = useState({ name: '', description: '', media: '' });
    const [isModalVisible, setIsModalVisible] = useState(false);
    const [currentHobby, setCurrentHobby] = useState(null);

    useEffect(() => {
        // Fetch user details
        fetch(`http://localhost:8080/api/v1/users/${userId}`)
            .then(response => response.json())
            .then(data => setUserName(data.name))
            .catch(error => console.error('Error fetching user details:', error));

        // Fetch hobbies
        fetch(`http://localhost:8080/api/v1/hobbies/users/${userId}`)
            .then(response => response.json())
            .then(data => setHobbiesList(data))
            .catch(error => console.error('Error fetching hobbies:', error));
    }, [userId]);

    const handleAdd = () => {
        setIsModalVisible(true);
        setCurrentHobby(null);
        setFormData({ name: '', description: '', media: '' });
    };

    const handleUpdate = (hobby) => {
        setCurrentHobby(hobby);
        setFormData({
            name: hobby.name,
            description: hobby.description,
            media: hobby.media
        });
        setIsModalVisible(true);
    };

    const handleSubmit = () => {
        const method = currentHobby ? 'PUT' : 'POST';
        const url = currentHobby
            ? `http://localhost:8080/api/v1/hobbies/${currentHobby.id}`
            : 'http://localhost:8080/api/v1/hobbies';

        fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ ...formData, users: { id: userId } }),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to save hobby');
                }
                return response.json();
            })
            .then(data => {
                console.log('Data received from server:', data);  // Log the response data
                setHobbiesList(prevList => {
                    if (currentHobby) {
                        return prevList.map(h => (h.id === currentHobby.id ? data : h));
                    } else {
                        return [...prevList, data];
                    }
                });
                setIsModalVisible(false);
            })
            .catch(error => console.error('Error saving hobby:', error));
    };


    const handleDelete = (hobbyId) => {
        fetch(`http://localhost:8080/api/v1/hobbies/${hobbyId}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    setHobbiesList(prevList => prevList.filter(h => h.id !== hobbyId));
                } else {
                    console.error('Failed to delete hobby');
                }
            })
            .catch(error => console.error('Error deleting hobby:', error));
    };

    //LOGIC TO HANDLE FLEXIBLE MEDIA RENDERING
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
                        width="560"
                        height="315"
                        src={`https://www.youtube.com/embed/${videoId}`}
                        title="YouTube video player"
                        frameBorder="0"
                        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                        allowFullScreen
                    ></iframe>
                </div>
            );
        } else if (imageRegex.test(media)) {
            return <img src={media} alt="Hobby media" className="w-full h-auto" />;
        } else if (videoRegex.test(media)) {
            return <video src={media} controls className="w-full h-auto"></video>;
        } else if (audioRegex.test(media)) {
            return <audio src={media} controls className="w-full h-auto"></audio>;
        } else {
            return <a href={media} target="_blank" rel="noopener noreferrer" className="text-blue-500 underline">Open Media</a>;
        }
    };

    return (
        <div className="container mx-auto p-4">
            <h1 className="text-2xl mb-4">Hobbies for {userName}</h1>
            <button
                onClick={handleAdd}
                className="mb-4 bg-green-500 text-white px-4 py-2 rounded"
            >
                Add Hobby
            </button>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
                {hobbiesList.map(hobby => (
                    <div key={hobby.id} className="border p-4 rounded-lg">
                        <strong>{hobby.name}</strong>
                        <p>{hobby.description}</p>
                        <div>
                            {renderMedia(hobby.media)}
                        </div>
                        <button
                            onClick={() => handleUpdate(hobby)}
                            className="mt-2 bg-blue-500 text-white px-4 py-2 rounded"
                        >
                            Update
                        </button>
                        <button
                            onClick={() => handleDelete(hobby.id)}
                            className="mt-2 bg-red-500 text-white px-4 py-2 rounded"
                        >
                            Delete
                        </button>
                    </div>
                ))}
            </div>
            <MediaModal
                isVisible={isModalVisible}
                onClose={() => setIsModalVisible(false)}
                onSubmit={handleSubmit}
                formData={formData}
                setFormData={setFormData}
            />
        </div>
    );
};

export default Hobbies;
