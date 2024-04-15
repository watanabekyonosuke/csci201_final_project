import React from 'react';
import './SiteHeader.css';

const SiteHeader = () => {

    return (
        <div className="nav-container">
            <header className="site-header">
                <div className="site-header__left">
                    <h1 className="site-header__title">CSOasis</h1>
                </div>
                <div className="site-header__right">
                    <a href="SignIn.html" className="site-header__button">SIGN IN</a>
                    <button className="site-header__button">JOIN</button>
                </div>
            </header>
        </div>
    );
};

export default SiteHeader;