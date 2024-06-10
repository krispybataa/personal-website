import React from 'react';

const SpotifyPlayer = ({ embedLink }) => {
    return (
        <div className="spotify-player mt-2">
            <iframe
                src={embedLink}
                width="100%"
                height="80"
                frameBorder="0"
                allowtransparency="true"
                allow="encrypted-media"
                title="Spotify Player"
            ></iframe>
        </div>
    );
};

export default SpotifyPlayer;
