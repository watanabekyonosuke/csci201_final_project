import React, {useState} from 'react';
import './ForumHeader.css';

const ForumHeader = () => {

    const [selectedButton, setSelectedButton] = useState(2); // Initially set to 2nd button

    const handleButtonClick = (buttonIndex) => {
        setSelectedButton(buttonIndex);
      };

return (
    <div className="NavContainer">
        <button className={selectedButton === 1 ? 'selected' : ''} onClick={() => handleButtonClick(1)}>
            More Discussions v
        </button>
        <button className={selectedButton === 2 ? 'selected' : ''} onClick={() => handleButtonClick(2)}>
            SWE
        </button>
        <button className={selectedButton === 3 ? 'selected' : ''} onClick={() => handleButtonClick(3)}>
            Product Management
        </button>
        <button className={selectedButton === 4 ? 'selected' : ''} onClick={() => handleButtonClick(4)}>
            Quant
        </button>
        <button className={selectedButton === 5 ? 'selected' : ''} onClick={() => handleButtonClick(5)}>
            MAANG
        </button>
    </div>
    );
};

export default ForumHeader;