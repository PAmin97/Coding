import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import "./NavBar.css";
import axios from "axios";
import { AuthContext } from "../helpers/AuthContext";

function NavBar() {
  const [click, setClick] = useState(false);
  const [button, setButton] = useState(true);
  const [authenticate, setAuthenticate] = useState({
    username: "",
    id: 0,
    log: false,
  });

  const handleClick = () => setClick(!click);
  const closeMobileMenu = () => setClick(false);

  const showButton = () => {
    if (window.innerWidth <= 960) {
      setButton(false);
    } else {
      setButton(true);
    }
  };

  const logout = () => {
    localStorage.removeItem("accessToken");
    setAuthenticate({
      username: "",
      id: 0,
      log: false,
    });
  };

  useEffect(() => {
    showButton();
  }, []);

  useEffect(() => {
    axios
      .get("http://localhost:3001/auth/validate", {
        headers: {
          accessToken: localStorage.getItem("accessToken"),
        },
      })
      .then((response) => {
        if (response.data.error) {
          setAuthenticate({
            ...authenticate,
            log: false,
          });
        } else {
          setAuthenticate({
            username: response.data.username,
            id: response.data.id,
            log: true,
          });
        }
      });
  }, []);

  window.addEventListener("resize", showButton);

  return (
    <>
      <AuthContext.Provider value={{ authenticate, setAuthenticate }}>
        <nav className="navbar">
          <div className="navbar-container">
            <Link
              to="/"
              className="navbar-logo"
              onClick={closeMobileMenu}
            >
              Penn SMS
              <img
                className="penn-logo"
                src="/images/PennLogo.jpg"
                alt="Penn Logo"
              />
            </Link>
            {!authenticate.log ? (
              <h3></h3>
            ) : (
              <h3 className="student-name">Welcome, {authenticate.username}</h3>
            )}
            <div className="menu-icon" onClick={handleClick}>
              <i className={click ? "fas fa-times" : "fas fa-bars"} />
            </div>
            <ul className={click ? "nav-menu active" : "nav-menu"}>
              <li className="nav-item">
                <Link
                  to="/"
                  className="nav-links"
                  onClick={closeMobileMenu}
                >
                  Home
                </Link>
              </li>
              <li className="nav-item">
                <Link
                  to="/support"
                  className="nav-links"
                  onClick={closeMobileMenu}
                >
                  Contact Us
                </Link>
              </li>
              <li className="nav-item">
                <Link
                  to="/my-profile"
                  className="nav-links"
                  onClick={closeMobileMenu}
                >
                  My Profile
                </Link>
              </li>
              <li className="nav-item">
                <Link
                  to="/login"
                  className="nav-links-mobile"
                  onClick={closeMobileMenu}
                >
                  Log In
                </Link>
              </li>
            </ul>
            {button && !authenticate.log ? (
              <Link to="/login" className="nav-links">
                Login
              </Link>
            ) : (
              button &&
              authenticate.log && (
                <Link to="/" className="nav-links" onClick={logout}>
                  Logout
                </Link>
              )
            )}
          </div>
        </nav>
      </AuthContext.Provider>
    </>
  );
}

export default NavBar;
