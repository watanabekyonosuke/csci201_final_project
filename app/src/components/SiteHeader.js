import React from 'react';
import { Outlet, Link, useLocation } from "react-router-dom";
import { FaHome } from 'react-icons/fa';
import './SiteHeader.css';

const SiteHeader = () => {

    const location = useLocation();
    let logsign = location.pathname.includes("/SignIn") || location.pathname.includes("/SignUp");
    let discussing = location.pathname.includes("/ForumList") || location.pathname.includes("Discussion");

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
                        <li className="signin"><Link to="/SignIn">Log In</Link></li>
                        <li className="signup"><Link to="/SignUp">Sign Up</Link></li>
                    </div>
                </header>
            </div>
        )}

        <Outlet />
        </>
    );
};

export default SiteHeader;