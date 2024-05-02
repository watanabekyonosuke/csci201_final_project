import React, { useState } from 'react';
import {Link, useNavigate} from "react-router-dom";
import './CreatePost.css';

const CreatePost = () => {
    // use the states of the currently filled boxes
    const navigate = useNavigate();
    const [title, setTitle] = useState('');
    const [post, setBody] = useState('');
    const [fgid, setCategory] = useState(1);


    const handlePostDiscussion = (event) => {
        event.preventDefault();

        var loggedInStatus = localStorage.getItem("uid");
        if (!loggedInStatus) {
            alert("Please Sign In or Register to Post New Discussions");
            return;
        }
    
        // set up data to be sent
        const data = {
            title: title,
            post: post,
            fgid: fgid,
            userid: loggedInStatus
        }

        // set up request to post discussion through CreateForumPostServlet
        const xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:3001/ProjectBackend/CreateForumPostServlet');
        xhr.setRequestHeader('Content-Type','application/json');
        xhr.onload = () => {
            if (xhr.status === 200) {
                alert("Discussion Posted!");
                navigate("/ForumList");
            } else if (xhr.status === 400) {
                alert("Please fill in all fields");
                navigate("/CreatePost");
            } else if (xhr.status === 401) {
                alert("Something went wrong. Please try again later");
                navigate("/ForumList");
            }
        }
        xhr.onerror = () => {
            console.error('Error posting discussion:', xhr.statusText);
        };
        xhr.send(JSON.stringify(data));
    };
    
    return (
        <div className='create-post'>

            <form className='post-form' onSubmit={handlePostDiscussion}>
                
                <div className="form-group">
                    <label>Title:</label>
                    <textarea
                        id="title-input"
                        placeholder="Title..."
                        className="form-control" // Add Bootstrap form-control class
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                    />
                </div>

                <div className="form-group">
                    <label>Body:</label>
                    <textarea
                        id="body-input"
                        placeholder="Body..."
                        className="form-control" // Add Bootstrap form-control class
                        value={post}
                        onChange={(e) => setBody(e.target.value)}
                    />
                </div>
               
                <div className="radio-buttons">
                    <label>
                        <input type="radio" name="category" value={1} checked={fgid===1} onChange={() => setCategory(1)}/>
                        Miscellaneous
                    </label>
                    <label>
                        <input type="radio" name="category" value={2} checked={fgid===2} onChange={() => setCategory(2)}/>
                        SWE
                    </label>
                    <label>
                        <input type="radio" name="category" value={3} checked={fgid===3} onChange={() => setCategory(3)}/>
                        Product Management
                    </label>
                    <label>
                        <input type="radio" name="category" value={4} checked={fgid===4} onChange={() => setCategory(4)}/>
                        Quant
                    </label>
                    <label>
                        <input type="radio" name="category" value={5} checked={fgid===5} onChange={() => setCategory(5)}/>
                        MAANG
                    </label>
                    <label>
                        <input type="radio" name="category" value={6} checked={fgid===6} onChange={() => setCategory(6)} />
                        Hardware
                    </label>
                            <div></div>
                    <div id='submission'>
                        <button>
                            Submit
                        </button>
                    </div>
                </div>
            </form>

            <div className="discussion-container">
                <Link to="/ForumList" className="back-button">&#8592;</Link>
            </div>

        </div>
    );
}

export default CreatePost;
