import React, { useState, useEffect } from 'react';
import {Link} from "react-router-dom";
import './ForumList.css';
import ForumHeader from './ForumHeader';

const ForumList = () => {

    const [discussions, setDiscussions] = useState([]);

    useEffect(() => {fetchDiscussions();}, []); 

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
        // let fgid_ = localStorage.getItem("forumGroup");
        // need button to set sort method in ForumHeader -> drop down select
        xhr.send(JSON.stringify({ fgid: 0, sort: 0 }));
    };

    return (
        <div className="ForumContainer">
            <div>
                <ForumHeader />
            </div>
            <div className="DiscussionList">
                {discussions.map((discussion, index) => (
                    <div key={index} className="DiscussionItem">
                        <div>Title: {discussion.title}</div>
                        <div>Likes: {discussion.likes}</div>
                        <div>Creation Time: {discussion.creationtime}</div>
                        <div>Number of Comments: {discussion.numofcomments}</div>
                    </div>
                ))}
            </div>
            <div className="new-discussion-button"><Link to='/CreatePost'>Post Discussion</Link></div>   
        </div>
    );
};

export default ForumList;