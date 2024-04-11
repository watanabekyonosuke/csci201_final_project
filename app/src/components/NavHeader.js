import React from 'react';
import './NavHeader.css';

const NavHeader = () => {
    return (
        <div className="nav-container">
            <header className="site-header">
                <div className="site-header__left">
                    <h1 className="site-header__title">CSOasis</h1>
                </div>
                <div className="site-header__right">
                    <button className="site-header__button">SIGN IN</button>
                    <button className="site-header__button">JOIN</button>
                </div>
            </header>
        </div>
    );
};

export default NavHeader;