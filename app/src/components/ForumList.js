import React, { useState, useEffect } from 'react';
import {Link} from "react-router-dom";
import './ForumList.css';
import ForumHeader from './ForumHeader';

const ForumList = () => {

    const [discussions, setDiscussions] = useState([]);

    useEffect(() => {fetchDiscussions();}, []); 

    const fetchDiscussions = () => {
        const xhr = new XMLHttpRequest();
        xhr.open('POST', '/ForumDisplayServlet');
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = () => {
            if (xhr.status === 200) {
                const data = JSON.parse(xhr.responseText);
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
        <div className="ForumContainer">
            <div>
                <ForumHeader />
            </div>
            <div className="DiscussionList">
                {discussions.map((discussion, index) => (
                    <div key={index} className="DiscussionItem">
                        <div>{discussion.post}</div>
                        <div>Likes: {discussion.likes}</div>
                        <div>Creation Time: {discussion.creationtime}</div>
                        <div>Number of Comments: {discussion.numofcomments}</div>
                    </div>
                ))}
            </div>
            <a><Link to="/Discussion">Enter discussion</Link></a>
        </div>
    );
};

export default ForumList;