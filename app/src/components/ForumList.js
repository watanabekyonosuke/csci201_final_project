import React, { useState, useEffect } from 'react';
import { Link } from "react-router-dom";
import './ForumList.css';
import ForumHeader from './ForumHeader';
import { FaRegThumbsUp } from 'react-icons/fa';


const ForumList = () => {
   const [discussions, setDiscussions] = useState([]);


   useEffect(() => {
       fetchDiscussions();
   }, []);


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
       xhr.send(JSON.stringify({ fgid: 0, sort: 0 }));
   };


   return (
       <div className="forumContainer">
           <ForumHeader />
           <div className="discussionList">
               {discussions.map((discussion, index) => (
                   <div key={index} className="discussionItem">
                       <div className="discussionTitle">{discussion.title}</div>
                       <div className="discussionAttributes">
                           <div className="discussionLikes">
                               <FaRegThumbsUp /> {discussion.likes}
                           </div>
                           <div className="discussionAge">
                               {discussion.creationtime} hours ago
                           </div>
                       </div>
                   </div>
               ))}
           </div>
       </div>
   );
};


export default ForumList;


