import React, { useState, useEffect } from 'react';
import { FaRegThumbsUp } from 'react-icons/fa';
import {Link} from "react-router-dom";
import './Discussion.css';

const Discussion = () => {

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
        <div className="comment-container">
            <Link to="/ForumList" className="back-button">&#8592;</Link>
            <div className="commentList">
               {comments.map((comment, index) => (
                   <div key={index} className="commentItem">
                       <div className="commentTitle">{comment.title}</div>
                       <div className="commentAttributes">
                            <div className="commentLikes">
                                <button
                                    onClick={() => handleLikeClick(comment.titleid, comment.liked)}
                                    className={`like-button ${comment.liked ? 'liked' : ''}`}>
                                    <FaRegThumbsUp /> {comment.likes}
                                </button>
                            </div>
                           <div className="commentAge">
                               {comment.creationtime} hours ago
                           </div>
                       </div>
                   </div>
               ))}
           </div>
        </div>
    );
}


export default Discussion;