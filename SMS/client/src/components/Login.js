import React, { useState } from "react";
import "./Login.css";
import axios from "axios";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const login = () => {
    const data = { username: username, password: password };
    axios.post("http://localhost:3001/auth/login", data).then((response) => {
      console.log("Works here.");
      console.log(response.data);
    });
  };

  return (
    <div className="Login">
      <img src="/images/SMS-Login.jpg" alt="Logan Hall on Penn Campus" />
      <div className="login-form">
        <div className="title">Sign In</div>
        <div className="form">
          <form>
            <div className="input-container">
              <label>Username:</label>
              <input
                type="text"
                name="username"
                required
                autoComplete="off"
                onChange={(event) => {
                  setUsername(event.target.value);
                }}
              />
            </div>
            <div className="input-container">
              <label>Password:</label>
              <input
                type="password"
                name="password"
                required
                autoComplete="off"
                onChange={(event) => {
                  setPassword(event.target.value);
                }}
              />
            </div>
            <div className="button-container">
              <button onClick={login}>Submit</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}

export default Login;
