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
                    <li className="site-header__button"><Link to="/SignIn">Sign In</Link></li>
                    <li className="site-header__button"><Link to="/SignUp">Join Us</Link></li>
                    <li className="site-header__button"><Link to="/Forum">Forum</Link></li>
                    <li className="site-header__button"><Link to="/Landing">Landing</Link></li>
                </div>
            </header>
        </div>

        <Outlet />
        </>
    );
};

export default SiteHeader;