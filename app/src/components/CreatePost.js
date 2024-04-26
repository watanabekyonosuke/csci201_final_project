import React from 'react';
import {Link} from "react-router-dom";
import './CreatePost.css';

const CreatePost = () => {

    return (
        <div className="discussion-container">
            <Link to="/ForumList" className="back-button">&#8592;</Link>
        </div>
    );
}

// onClick={handlePostDiscussion}
export default CreatePost;

/**
 *     const handlePostDiscussion = () => {
        var loggedInStatus = localStorage.getItem("uid");
        if (!loggedInStatus) {
            alert("Please Sign In or Register to Post New Discussions");
            return;
        }

        // set up request to post discussion through CreateForumPostServlet
        const xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:3001/ProjectBackend/CreateForumPostServlet');
        xhr.setRequestHeader('Content-Type','application/json');
        xhr.onload = () => {
            if (xhr.status === 200) {

            }
        }
        xhr.onerror = () => {
            console.error('Error posting discussion:', xhr.statusText);
        };
        xhr.send(JSON.stringify({}));

    };
 */