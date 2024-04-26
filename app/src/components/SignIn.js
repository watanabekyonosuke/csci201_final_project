import React from 'react';
import {Link, useNavigate} from "react-router-dom";
import './SignIn.css';

const SignIn = () => {

    const navigate = useNavigate();

    const handleSignIn = (event) =>{
        event.preventDefault();
        const uname = document.getElementById("loginUsername").value;
        const pword = document.getElementById("loginPassword").value;

        let xhr = new XMLHttpRequest();
        xhr.open("POST", 'http://localhost:3001/ProjectBackend/SigninServlet', true);
        xhr.setRequestHeader('Content-Type', 'application/json');

        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    const reply = JSON.parse(xhr.responseText);
                    const userId = reply.userId;
                    localStorage.setItem("uid", userId);
                    alert("Login Success");
                    navigate("/Landing");

                    
                }
                else{
                    const reply = JSON.parse(xhr.responseText);
                    alert(reply.error);
                }
            }
        };

        const sendJSON = JSON.stringify({username: uname, password: pword});
        xhr.send(sendJSON);

    };

    return (
        <div className="signin-container">
            <Link to="/Landing" className="back-button">&#8592;</Link>
            <form id="signin" onSubmit={handleSignIn}>
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