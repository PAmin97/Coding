import React, { useState } from "react";
import "./Login.css";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";
// import { AuthContext } from "../helpers/AuthContext";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  // const { setAuthenticate } = useContext(AuthContext);

  let navigate = useNavigate();

  const login = () => {
    const data = { username: username, password: password };
    axios.post("http://localhost:3001/auth/login", data).then((response) => {
      if (response.data.error) {
        alert(response.data.error);
      } else {
        localStorage.setItem("accessToken", response.data);
        // setAuthenticate(true);
        navigate("/");
      }
    });
  };

  return (
    <div className="Login">
      <img src="/images/SMS-Login.jpg" alt="Logan Hall on Penn Campus" />
      <div className="login-form">
        <div className="title">Sign In</div>
        <div className="input-container">
          <label>Username:</label>
          <input
            type="text"
            onChange={(event) => {
              setUsername(event.target.value);
            }}
          />
        </div>
        <div className="input-container">
          <label>Password:</label>
          <input
            type="password"
            onChange={(event) => {
              setPassword(event.target.value);
            }}
          />
        </div>
        <div className="button-container">
          <button onClick={login}> Login </button>
        </div>
        <div className="create-account-container">
          <Link to="/create-account">Create an Account</Link>
        </div>
      </div>
    </div>
  );
}

export default Login;
