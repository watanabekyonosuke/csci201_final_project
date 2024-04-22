import React from 'react';
import {Link} from "react-router-dom";
import './Discussion.css';

const Discussion = () => {

    return (
        <div className="discussion-container">
            <Link to="/ForumList" className="back-button">&#8592;</Link>
        </div>
    );
}

export default Discussion;