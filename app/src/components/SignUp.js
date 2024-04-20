import React from 'react';
import './SignUp.css';

const SignUp = () => {

    return (
        <div className="signup-container">
        <form action="CSCI201_Final_Project_V1" method="post">
            <fieldset>
                <legend>Sign Up</legend>
                <label htmlFor="signUpUsername">Username:</label>
                <input type="text" id="signUpUsername" name="signUpUsername" required placeholder="Enter a username" />
                <br />
                <label htmlFor="signUpEmail">Email:</label>
                <input type="email" id="signUpEmail" name="signUpEmail" required placeholder="Enter your email" />
                <br />
                <label htmlFor="signUpPassword">Password:</label>
                <input type="password" id="signUpPassword" name="signUpPassword" required placeholder="Enter a password" />
                <br />
                <label htmlFor="confirmPassword">Confirm Password:</label>
                <input type="password" id="cofirmPassword" name="confirmPassword" required placeholder="Confirm password" />
            </fieldset>
            <input type="submit" value="Sign Up" />
        </form>

        <div id="Error"></div>
    </div>
    );
}

export default SignUp;