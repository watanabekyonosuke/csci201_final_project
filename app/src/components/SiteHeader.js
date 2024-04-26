import React from 'react';
import { Outlet, Link, useLocation, useNavigate } from "react-router-dom";
import { FaHome } from 'react-icons/fa';
import { VscAccount } from "react-icons/vsc";
import './SiteHeader.css';

const SiteHeader = () => {

    const location = useLocation();
    const navigate = useNavigate();
    let logsign = location.pathname.includes("/SignIn") || location.pathname.includes("/SignUp");
    let discussing = location.pathname.includes("/ForumList") || location.pathname.includes("Discussion");

    let uid = localStorage.getItem("uid");
    console.log(uid);

    const handleLogout = () =>{
        localStorage.removeItem("uid");
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
                        {uid &&
                          <div className="user-profile-icon"><VscAccount /> </div> }
                    </div>
                </header>
            </div>
        )}

        <Outlet />
        </>
    );
};

export default SiteHeader;