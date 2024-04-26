import React from 'react';
import {Link} from "react-router-dom";
import './SignIn.css';

const SignIn = () => {

    function handleSignIn(){

    }

    return (
        <div className="signin-container">
            <a><Link to="/Landing" className="back-button">&#8592;</Link></a>
            <form id="signin" onSubmit="event.preventDefault(); handleSignIn();">
                <fieldset>
                    <legend>Login</legend>
                    <label htmlFor="loginUsername">Username:</label>
                    <input type="text" id="loginUsername" name="loginUsername" required placeholder="Enter your username" />
                    <br />
                    <label htmlFor="loginPassword">Password:</label>
                    <input type="password" id="loginPassword" name="loginPassword" required placeholder="Enter your password" />
                </fieldset>
                <input type="submit" value="Login" />
            </form>

            <div id="Error"></div>
        </div>
    );
}

export default SignIn;