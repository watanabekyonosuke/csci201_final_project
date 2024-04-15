import React from 'react';
import './Landing.css';
import backgroundImage from './background.png'

const Landing = () => {

    return (
        <div className="landing-container">
            <img src={backgroundImage}></img>
        </div>
    );
};

export default Landing;