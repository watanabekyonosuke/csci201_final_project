import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import './ForumHeader.css';

const ForumHeader = ({onButtonClick}) => {

    const navigate = useNavigate();

    const [selectedButton, setSelectedButton] = useState(2); // Initially set to 2nd button

    if(selectedButton === 2) localStorage.setItem("forumGroup", 2); // Initially set stored fid to 2nd button

    const handleButtonClick = (buttonIndex) => {
        setSelectedButton(buttonIndex);
        localStorage.setItem("forumGroup", buttonIndex);
        console.log(localStorage.getItem("forumGroup"));
        onButtonClick();
        navigate("/ForumList");
      };

return (
    <div className="headerWrapper">
    <h1 className="forumTitle">What's on your mind?</h1>
        <div className="NavContainer">
            <button className={selectedButton === 1 ? 'selected' : ''} onClick={() => handleButtonClick(1)}>
                Misc
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
            <button className={selectedButton === 6 ? 'selected' : ''} onClick={() => handleButtonClick(6)}>
                Hardware
            </button>
        </div>
    </div>
    );
};

export default ForumHeader;
