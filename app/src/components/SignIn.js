import React from 'react';
import {Link} from "react-router-dom";
import './SignIn.css';

const SignIn = () => {

    const handleSignIn = (event) =>{
        event.preventDefault();
        const uname = document.getElementById("loginUsername").value;
        const pword = document.getElementById("loginPassword").value;

        let xhr = new XMLHttpRequest();
        const url = "SigninServlet"
        xhr.open("POST", 'http://localhost:8080/ProjectBackend/SigninServlet', true);
        xhr.setRequestHeader('Content-Type', 'application/json');

        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    const reply = JSON.parse(xhr.responseText);
                    console.log(reply);
                    console.log("signin");
                    console.log(reply.userId);
                }
                else{
                    console.log("not");
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