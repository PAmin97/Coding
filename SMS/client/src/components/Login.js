import React, { useState } from 'react';
import "./Login.css";
import {Link} from 'react-router-dom';



function Login() {

    const [errorMessages, setErrorMessages] = useState({});
    const [isSubmitted, setIsSubmitted] = useState(false);

    const db = [
        {
            username: 'user1',
            password: 'pass1'
        },
        {
            username: 'user2',
            password: 'pass2'
        }
    ];

    const errors = {
        username: "invalid username",
        password: "invalid password"
    };

    const handleSubmit = (event) => {
        //Prevent page reload
        event.preventDefault();

        var {username, password} = document.forms[0];

        // Find user login info
        const userData = db.find((user) => user.username === username.value);

        //Compare user info
        if (userData) {
            if (userData.password !== password.value) {
                // Invalid password
                setErrorMessages({name: 'password', message: errors.password});
            } else {
                setIsSubmitted(true);
            }
        } else {
            //Username not found
            setErrorMessages({name: "username", message: errors.username});
        }
    };

    // JSX code for error message
    const renderErrorMessage = (name) => {
        name === errorMessages.name && (
            <div className='error'>
                {errorMessages.message}
            </div>
        )
    };

    // JSX code for login form
    const renderForm = (
        <div className='form'>
            <form onSubmit={handleSubmit}>
                <div className='input-container'>
                    <label>Username</label>
                    <input type='text' name='username' required />
                    {renderErrorMessage('username')}
                </div>
                <div className='input-container'>
                    <label>Password</label>
                    <input type='password' name='password' required />
                    {renderErrorMessage('password')}
                </div>
                <div className='button-container'>
                    <button>Submit</button>
                </div>
            </form>
        </div>
    );

    return (
        <div className='Login'>
            <img src='/images/SMS-Login.jpg' alt='Logan Hall on Penn Campus'/>
            <div className='login-form'>
                <div className='title'>Sign In</div>
                {isSubmitted ? <Link to='/students'><button>Enter</button></Link> : renderForm}
            </div>
        </div>
    )
}

export default Login