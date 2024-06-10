import React, { useState, useEffect } from 'react';
import SpotifyPlayer from '../SpotifyPlayer';
import MusicModal from '../MusicModal.jsx';

const Music = ({ userId }) => {
    const [musicList, setMusicList] = useState([]);
    const [userName, setUserName] = useState('');
    const [isModalVisible, setIsModalVisible] = useState(false);
    const [currentMusic, setCurrentMusic] = useState(null);
    const [formData, setFormData] = useState({
        title: '',
        album: '',
        artist: '',
        aaUrl: '',
        spUrl: ''
    });

    useEffect(() => {
        // Fetch user details
        fetch(`http://localhost:8080/api/v1/users/${userId}`)
            .then(response => response.json())
            .then(data => setUserName(data.name))
            .catch(error => console.error('Error fetching user details:', error));

        // Fetch music
        fetch(`http://localhost:8080/api/v1/beatsies/users/${userId}`)
            .then(response => response.json())
            .then(data => setMusicList(data))
            .catch(error => console.error('Error fetching music:', error));
    }, [userId]);

    const handleUpdate = (music) => {
        setCurrentMusic(music);
        setFormData({
            title: music.title,
            album: music.album,
            artist: music.artist,
            aaUrl: music.aaUrl,
            spUrl: music.spUrl
        });
        setIsModalVisible(true);
    };

    const handleSubmit = () => {
        fetch(`http://localhost:8080/api/v1/beatsies/${currentMusic.id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData),
        })
            .then(response => {
                if (response.ok) {
                    setMusicList(prevList => prevList.map(m => (m.id === currentMusic.id ? { ...m, ...formData } : m)));
                    setIsModalVisible(false);
                } else {
                    console.error('Failed to update music');
                }
            })
            .catch(error => console.error('Error updating music:', error));
    };

    return (
        <div className="container mx-auto p-4">
            <h1 className="text-2xl mb-4">Favorite Music for {userName}</h1>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
                {musicList.map(music => (
                    <div key={music.id} className="border p-4 rounded-lg">
                        <strong>{music.title}</strong> by {music.artist} - {music.album}
                        <div>
                            <img src={music.aaUrl} alt={`${music.title} album art`} className="w-full h-auto"/>
                        </div>
                        <SpotifyPlayer embedLink={`https://open.spotify.com/embed/track/${music.spUrl.split('track/')[1].split('?')[0]}`} />
                        <button
                            onClick={() => handleUpdate(music)}
                            className="mt-2 bg-blue-500 text-white px-4 py-2 rounded"
                        >
                            Update
                        </button>
                    </div>
                ))}
            </div>
            <MusicModal
                isVisible={isModalVisible}
                onClose={() => setIsModalVisible(false)}
                onSubmit={handleSubmit}
                formData={formData}
                setFormData={setFormData}
            />
        </div>
    );
};

export default Music;
