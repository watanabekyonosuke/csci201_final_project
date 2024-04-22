import React from 'react';
import {Link} from "react-router-dom";
import './Forum.css';
import ForumHeader from './ForumHeader';

const ForumList = () => {
    return (
        <div className="ForumContainer">
            <div>
                <ForumHeader />
            </div>
            <a><Link to="/Discussion">Enter discussion</Link></a>
        </div>
    );
};

export default ForumList;