import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import "./NavBar.css";

function NavBar() {
  const [click, setClick] = useState(false);
  const [button, setButton] = useState(true);
  const [log, setLog] = useState(true);

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
    setLog(false);
  }

  useEffect(() => {
    showButton();
  }, []);

  window.addEventListener("resize", showButton);

  return (
    <>
      <nav className="navbar">
        <div className="navbar-container">
          <Link
            to="/students"
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
          <div className="menu-icon" onClick={handleClick}>
            <i className={click ? "fas fa-times" : "fas fa-bars"} />
          </div>
          <ul className={click ? "nav-menu active" : "nav-menu"}>
            <li className="nav-item">
              <Link
                to="/students"
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
          {button && !log && !localStorage.getItem("accessToken") ? (
            <Link to="/login" className="nav-links">
              Login
            </Link>
          ) : (
            button && log && (
              <Link to="/" className="nav-links" onClick={logout}>
                Logout
              </Link>
            )
          )}
        </div>
      </nav>
    </>
  );
}

export default NavBar;
