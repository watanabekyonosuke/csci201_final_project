import React, { useState, useEffect } from 'react';
import { FaRegThumbsUp } from 'react-icons/fa';
import { useNavigate } from "react-router-dom";
import {Link} from "react-router-dom";
import './Discussion.css';

const Discussion = () => {
    var loggedIn = localStorage.getItem("uid");
    const navigate = useNavigate();
    const [comments, setComments] = useState([]);

    useEffect(() => {
        fetchComments();
    }, []);

    // A handler that sends a like/unlike action to the server
    const handleLikeClick = (commentId, isLiked) => {
        // Optimistic UI update
        setComments(commentDiscussions =>
        commentDiscussions.map(c =>
            c.titleid === commentId ? { ...c, likes: c.likes + (isLiked ? -1 : 1), liked: !isLiked } : c
        )
        );

        // Here you would make an API call to the backend to update the likes
        // For now, we'll just log to the console
        console.log(`Comment ${commentId} liked status: ${!isLiked}`);

        // If the API call fails, you'll need to revert the UI changes
        // This is where you would catch an error and revert if necessary
    };
    
    const handleCreateCommentClick = (titleId) => {
        if (!loggedIn) {
            alert("Please Sign In or Register to Make a Post!");
        } else {
            console.log(localStorage.getItem("tid"));
            navigate("/CreateComment");
        }
    }


    const fetchComments = () => {
        const xhr = new XMLHttpRequest();
        xhr.open('POST', 'http://localhost:3001/ProjectBackend/CommentServlet');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = () => {
            if (xhr.status === 200) {
                const data = JSON.parse(xhr.responseText);
                console.log(data);
                setComments(data);
            } else {
                console.error('Error fetching comments:', xhr.statusText);
            }
        };
        xhr.onerror = () => {
            console.error('Error fetching comments:', xhr.statusText);
        };
        const disc = parseInt(localStorage.getItem("tid"));
        xhr.send(JSON.stringify({ titleid: disc}));
    };
 
    const refreshCommentList = () => {
     // Call fetchDiscussions to refresh the forum list
         fetchComments();
     };

    return (
<div className="comment-section">
  <div className="comment-header">
    <Link to="/ForumList" className="back-button">&#8592;</Link>
    <h2 className="comment-title">Comments</h2>
  </div>

  <div className="comment-list">
    {comments.map((comment, index) => (
      <div key={index} className="comment-item">
        <div className="comment-content">
          <p>{comment.content}</p>
        </div>
        <div className="comment-meta">
          <div className="comment-likes">
            <button
              onClick={() => handleLikeClick(comment.titleid, comment.liked)}
              className={`like-button ${comment.liked ? 'liked' : ''}`}
            >
              <FaRegThumbsUp /> {comment.likes}
            </button>
          </div>
          <div className="comment-age">{comment.hours} hours ago</div>
          <div className="comment-user">Posted by: {comment.username}</div>
        </div>
      </div>
    ))}
  </div>

  <button className="create-comment-button" onClick={handleCreateCommentClick}>
    Add Comment
  </button>
</div>
    );
}


export default Discussion;

