import React, {useState} from 'react';
import { Outlet, Link, useLocation} from "react-router-dom";
import { FaHome } from 'react-icons/fa';
import { VscAccount } from "react-icons/vsc";
import './SiteHeader.css';

const SiteHeader = () => {

    const getPoints = () =>{

        const uid = localStorage.getItem("uid");

        let xhr = new XMLHttpRequest();
        xhr.open("POST", 'http://localhost:3001/ProjectBackend/GetTotalPointsServlet', true);
        xhr.setRequestHeader('Content-Type', 'application/json');

        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    const reply = JSON.parse(xhr.responseText);

                    
                    localStorage.setItem("points", reply);

                }
                else{
                    console.log("error");
                }
            }
        };

        const sendJSON = JSON.stringify({userid: uid});
        xhr.send(sendJSON);

    }

    const location = useLocation();
    const [showDropdown, setShowDropdown] = useState(false);

    getPoints();

    const username = localStorage.getItem("username");
    let points = localStorage.getItem("points");

    let logsign = location.pathname.includes("/SignIn") || location.pathname.includes("/SignUp");
    let discussing = location.pathname.includes("/ForumList") || location.pathname.includes("Discussion");

    let uid = localStorage.getItem("uid");
    console.log(uid);

    const handleLogout = () =>{
        localStorage.removeItem("uid");
        setShowDropdown(false);
    }

    const showProfile = () =>{
        setShowDropdown(!showDropdown);
    }

    const clickIcon = () =>{
        console.log(points);
        showProfile();
    }

    return (
        <>
        {/*hide site header if we are in signin/login page*/}
        {!logsign && (
            <div className="nav-container">
                <header className="site-header">
                    <div className="site-header__left">
                        <h1 className="site-header__title">CSOasis</h1>
                    </div>
                    <div className="site-header__right">
                        {!discussing && (
                            <li className="explore"><Link to="/ForumList">Explore Discussions</Link></li> )}
                        {discussing && (
                        <li className="home"><Link to="/Landing"><FaHome /></Link></li> )}
                        {!uid && (
                        <li className="signin"><Link to="/SignIn">Log In</Link></li> )}
                        {!uid && (
                        <li className="signup"><Link to="/SignUp">Sign Up</Link></li> )}
                        {uid && (
                        <div onClick={handleLogout}><li className="logout"><Link to="/Landing">Logout</Link></li></div> )}
                        <div className="full-profile">
                         {showDropdown && (
                        <div className = "dropdown-container">
                            <div className="dropdown-content">
                                    <p> {username} </p>
                                    <p> {points} </p>
                            </div>
                            <div className="arrow">&#9658;</div>
                        </div>
                             )}
                        {uid && (
                        <div className="user-profile-container">
                            <div className="user-profile-icon" onClick={clickIcon}><VscAccount /></div>
                        </div>
                            )}
                        </div>
                    </div>
                </header>
            </div>
        )}

        <Outlet />
        </>
    );
};

export default SiteHeader;