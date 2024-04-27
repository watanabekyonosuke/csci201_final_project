import React, { useState } from 'react';
import {Link, useNavigate} from "react-router-dom";
import './CreateComment.css';

const CreateComment = () => {
    // use the states of the currently filled boxes
    const navigate = useNavigate();
    const [content, setContent] = useState('');


    const handleCommentDiscussion = (event) => {
        event.preventDefault();

        // set up data to be sent
        const data = {
            content: content,
            userid: parseInt(localStorage.getItem("uid")),
            hours: new Date().getHours(), // Assuming you want to set the current hour
            titleid: parseInt(localStorage.getItem("tid")) // Assuming tid is stored in localStorage
        };

        // set up request to post discussion through CreateForumPostServlet
        const xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:3001/ProjectBackend/CreateCommentServlet');
        xhr.setRequestHeader('Content-Type','application/json');
        xhr.onload = () => {
            if (xhr.status === 200) {
                alert("Comment Posted!");
                navigate("/Dicussion");
            } else if (xhr.status === 400) {
                alert("Please fill in all fields");
                navigate("/CreateComment");
            } else if (xhr.status === 401) {
                alert("Something went wrong. Please try again later");
                navigate("/Dicussion");
            }
        }
        xhr.onerror = () => {
            console.error('Error posting comment:', xhr.statusText);
        };
        xhr.send(JSON.stringify(data));
    };
    
    return (
        <div className='create-comment'>

            <form className='comment-form' onSubmit={handleCommentDiscussion}>
            
                <div>
                <label>
                    <input
                        type="text"
                        id="body-input"
                        placeholder="Comment..."
                        className="body-input"
                        value = {content}
                        onChange={(e)=>setContent(e.target.value)}
                    />
                </label>
                </div>
                
                <div id='submission'>
                    <button>
                        Submit
                    </button>
                </div>
            </form>

            <div className="discussion-container">
                <Link to="/Discussion" className="back-button">&#8592;</Link>
            </div>

        </div>
    );
}

export default CreateComment;