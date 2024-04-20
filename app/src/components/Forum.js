import React from 'react';
import './Forum.css';
import ForumHeader from './ForumHeader';

const Forum = () => {
    return (
        <div className="ForumContainer">
            <div>
                <ForumHeader />
            </div>
            <h1>Forum Content</h1>
        </div>
    );
};

export default Forum;