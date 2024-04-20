import React from 'react';
import { Outlet, Link } from "react-router-dom";
import './SiteHeader.css';

const SiteHeader = () => {

    return (
        <>
        <div className="nav-container">
            <header className="site-header">
                <div className="site-header__left">
                    <h1 className="site-header__title">CSOasis</h1>
                </div>
                <div className="site-header__right">
                    <li className="explore"><Link to="/Forum">Explore Discussions</Link></li>
                    <li className="signin"><Link to="/SignIn">Log In</Link></li>
                    <li className="signup"><Link to="/SignUp">Sign Up</Link></li>
                </div>
            </header>
        </div>

        <Outlet />
        </>
    );
};

export default SiteHeader;