import React from 'react';
import './Landing.css';
import backgroundImage from './background.png'

const Landing = () => {

    return (
        <div className="landing-container">
            <img src={backgroundImage}></img>
            <div className='block'>
               
                

            </div>

            <div className='textHolder'>
                    <div> 
                        Your home for
                    </div>

                    <div>
                        candid conversations
                    </div>

                    <div>
                        about tech.
                    </div>

            </div>
            
        </div>
    );
};

export default Landing;