import React from 'react';
import {Link, useNavigate} from "react-router-dom";
import './SignUp.css';

const SignUp = () => {

    const navigate = useNavigate();

    const handleSignUp = (event) =>{
        event.preventDefault();
        const uname = document.getElementById("signUpUsername").value;
        const email = document.getElementById("signUpEmail").value;
        const pword = document.getElementById("signUpPassword").value;

        let xhr = new XMLHttpRequest();
        xhr.open("POST", 'http://localhost:3001/ProjectBackend/SignupServlet', true);
        xhr.setRequestHeader('Content-Type', 'application/json');

        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    const reply = JSON.parse(xhr.responseText);
                    const userId = reply.userId;
                    localStorage.setItem("uid", userId);
                    alert("Successful Signup");
                    navigate("/Landing");

                }
                else{
                    const reply = JSON.parse(xhr.responseText);
                    alert(reply);
                }
            }
        };

        const sendJSON = JSON.stringify({username: uname, email: email, password: pword});
        localStorage.setItem("username", uname);
        xhr.send(sendJSON);

    };

    return (
        <div className="signup-container">
        <Link to="/Landing" className="back-button">&#8592;</Link>
        <form id="signup" onSubmit={handleSignUp}>
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