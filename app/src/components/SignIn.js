import React from 'react';
import './SignIn.css';

const SignIn = () => {

    return (
        <div className="signin-container">
            <form action="CSCI201_Final_Project_V1" method="post">
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