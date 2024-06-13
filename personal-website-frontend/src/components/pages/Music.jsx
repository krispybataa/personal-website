import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import SpotifyPlayer from '../SpotifyPlayer';
import MusicModal from '../MusicModal.jsx';
import './Music.css';

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


    // Divide musicList into two groups: 3 items in the first row and 2 items in the second row
    // const firstRow = musicList.slice(0, 3);
    // const secondRow = musicList.slice(3, 5);
    const musicItemOne = musicList.slice(0, 1);
    const musicItemTwo = musicList.slice(1, 2);
    const musicItemThree = musicList.slice(2, 3);
    const musicItemFour = musicList.slice(3, 4);
    const musicItemFive = musicList.slice(4, 5);

    return (
        <div className="container mx-auto p-4">
            <h1 className="text-2xl mb-4 welcome section">This is {userName}'s favorite music!</h1>

            {/*/!* Go-To buttons *!/*/}
            {/*<div className="go-to-buttons">*/}
            {/*    <Link to={`/${userId}/hobbies`}>*/}
            {/*        <button className="go-to-hobbies hover:bg-blue-700 rounded-lg favorites">*/}
            {/*            Hobbies*/}
            {/*        </button>*/}
            {/*    </Link>*/}
            {/*    <Link to={`/${userId}/todos`}>*/}
            {/*        <button className="go-to-todos hover:bg-blue-700 rounded-lg favorites">*/}
            {/*            To-Do*/}
            {/*        </button>*/}
            {/*    </Link>*/}
            {/*</div>*/}

            <div className="music-group music-row-1 grid grid-cols-1 md:grid-cols-1 lg:grid-cols-3 gap-4">
                {musicItemOne.map(music => (
                    <div key={music.id} className="music-item  music-item-1 border p-4 rounded-lg">
                        <div className="music-item-ns1">
                            <h1 className="music-label">{music.title} by {music.artist} - {music.album}</h1>
                            <div className="album-art">
                                <img src={music.aaUrl} alt={`${music.title} album art`}
                                     className="album-art w-full h-auto"/>
                            </div>
                        </div>
                        <div className="spotify-player-container">
                            <SpotifyPlayer className="spotify-player"
                                           embedLink={`https://open.spotify.com/embed/track/${music.spUrl.split('track/')[1].split('?')[0]}`}/>
                        </div>
                        {/*<button*/}
                        {/*    onClick={() => handleUpdate(music)}*/}
                        {/*    className="mt-2 bg-blue-500 text-white px-4 py-2 rounded"*/}
                        {/*>*/}
                        {/*    Update*/}
                        {/*</button>*/}
                    </div>
                ))}
                {musicItemTwo.map(music => (
                    <div key={music.id} className="music-item music-item-2 border p-4 rounded-lg">
                        <div className="music-item-ns1">
                            <h1 className="music-label">{music.title} by {music.artist} - {music.album}</h1>
                            <div className="album-art">
                                <img src={music.aaUrl} alt={`${music.title} album art`}
                                     className="album-art w-full h-auto"/>
                            </div>
                        </div>
                        <div className="spotify-player-container">
                            <SpotifyPlayer className="spotify-player"
                                           embedLink={`https://open.spotify.com/embed/track/${music.spUrl.split('track/')[1].split('?')[0]}`}/>
                        </div>
                    </div>
                ))}
                {musicItemThree.map(music => (
                    <div key={music.id} className="music-item music-item-3 border p-4 rounded-lg">
                        <div className="music-item-ns1">
                            <h1 className="music-label">{music.title} by {music.artist} - {music.album}</h1>
                            <div className="album-art">
                                <img src={music.aaUrl} alt={`${music.title} album art`}
                                     className="album-art w-full h-auto"/>
                            </div>
                        </div>
                        <div className="spotify-player-container">
                            <SpotifyPlayer className="spotify-player"
                                           embedLink={`https://open.spotify.com/embed/track/${music.spUrl.split('track/')[1].split('?')[0]}`}/>
                        </div>
                        {/*<button*/}
                        {/*    onClick={() => handleUpdate(music)}*/}
                        {/*    className="mt-2 bg-blue-500 text-white px-4 py-2 rounded"*/}
                        {/*>*/}
                        {/*    Update*/}
                        {/*</button>*/}
                    </div>
                ))}
            </div>
            <div className="music-group music-row-1 grid grid-cols-1 md:grid-cols-1 lg:grid-cols-2 gap-4">
                {musicItemFour.map(music => (
                    <div key={music.id} className="music-item music-item-4 border p-4 rounded-lg">
                        <div className="music-item-ns2">
                            <h1 className="music-label">{music.title} by {music.artist} - {music.album}</h1>
                            <div className="album-art">
                                <img src={music.aaUrl} alt={`${music.title} album art`}
                                     className="album-art w-full h-auto"/>
                            </div>
                        </div>
                        <div className="spotify-player-container">
                            <SpotifyPlayer className="spotify-player"
                                           embedLink={`https://open.spotify.com/embed/track/${music.spUrl.split('track/')[1].split('?')[0]}`}/>
                        </div>
                        {/*<button*/}
                        {/*    onClick={() => handleUpdate(music)}*/}
                        {/*    className="mt-2 bg-blue-500 text-white px-4 py-2 rounded"*/}
                        {/*>*/}
                        {/*    Update*/}
                        {/*</button>*/}
                    </div>
                ))}
                {musicItemFive.map(music => (
                    <div key={music.id} className="music-item music-item-5 border p-4 rounded-lg">
                        <div className="music-item-ns2">
                            <h1 className="music-label">{music.title} by {music.artist} - {music.album}</h1>
                            <div className="album-art">
                                <img src={music.aaUrl} alt={`${music.title} album art`}
                                     className="album-art w-full h-auto"/>
                            </div>
                        </div>
                        <div className="spotify-player-container">
                            <SpotifyPlayer className="spotify-player"
                                           embedLink={`https://open.spotify.com/embed/track/${music.spUrl.split('track/')[1].split('?')[0]}`}/>
                        </div>
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
            <div className="todo-buttons">
                <div className="pages">
                    <Link to={`/${userName}/hobbies`} className="">
                    <button className="">
                            Hobbies
                        </button>
                    </Link>
                </div>
                <div className="pages">
                    <Link to={`/${userName}/todos`} className="">
                        <button>
                            To-Do
                        </button>
                    </Link>
                </div>
            </div>
        </div>
    );
};

export default Music;
