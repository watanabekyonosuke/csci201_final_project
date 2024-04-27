import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import './ForumList.css';
import ForumHeader from './ForumHeader';
import { FaRegThumbsUp } from 'react-icons/fa';

const ForumList = () => {

    // fetch user id to determine whether to limit create post button
    var loggedIn = localStorage.getItem("uid");
    var navigate = useNavigate();
    const handleCreatePostClick = () => {
        if (!loggedIn) {
            alert("Please Sign In or Register to Make a Post!");
        } else {
            navigate("/CreatePost");
        }
    }

   const [discussions, setDiscussions] = useState([]);


   useEffect(() => {
       fetchDiscussions();
   }, []);

   // A handler that sends a like/unlike action to the server
    const handleLikeClick = (discussionId, isLiked) => {
        // Optimistic UI update
        setDiscussions(currentDiscussions =>
        currentDiscussions.map(d =>
            d.titleid === discussionId ? { ...d, likes: d.likes + (isLiked ? -1 : 1), liked: !isLiked } : d
        )
        );

        // Here you would make an API call to the backend to update the likes
        // For now, we'll just log to the console
        console.log(`Discussion ${discussionId} liked status: ${!isLiked}`);

        // If the API call fails, you'll need to revert the UI changes
        // This is where you would catch an error and revert if necessary
    };


   const fetchDiscussions = () => {
       const xhr = new XMLHttpRequest();
       xhr.open('POST', 'http://localhost:3001/ProjectBackend/ForumDisplayServlet');
       xhr.setRequestHeader('Content-Type', 'application/json');
       xhr.onload = () => {
           if (xhr.status === 200) {
               const data = JSON.parse(xhr.responseText);
               console.log("got json");
               setDiscussions(data);
           } else {
               console.error('Error fetching discussions:', xhr.statusText);
           }
       };
       xhr.onerror = () => {
           console.error('Error fetching discussions:', xhr.statusText);
       };
       const group = parseInt(localStorage.getItem("forumGroup"));
       console.log("this is group " + group);
       xhr.send(JSON.stringify({ fgid: group, sort: 0 }));
   };

   const refreshForumList = () => {
    // Call fetchDiscussions to refresh the forum list
        fetchDiscussions();
    };


   return (
       <div className="forumContainer">
           <ForumHeader onButtonClick={refreshForumList} />
           <div className="discussionList">
               {discussions.map((discussion, index) => (
                   <div key={index} className="discussionItem">
                       <div className="discussionTitle">{discussion.title}</div>
                       <div className="discussionAttributes">
                            <div className="discussionLikes">
                                <button
                                    onClick={() => handleLikeClick(discussion.titleid, discussion.liked)}
                                    className={`like-button ${discussion.liked ? 'liked' : ''}`}>
                                    <FaRegThumbsUp /> {discussion.likes}
                                </button>
                            </div>
                           <div className="discussionAge">
                               {discussion.creationtime} hours ago
                           </div>
                       </div>
                   </div>
               ))}
           </div>
            <button id='create-post-button' onClick={handleCreatePostClick}>Add Post</button> 
       </div>
   );
};


export default ForumList;