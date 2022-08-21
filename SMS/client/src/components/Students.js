import React from "react";
import "../App.css";
import CardItem from "./CardItem";
import Footer from "./Footer";
import NavBar from "./NavBar";
import "./Cards.css";

export default function Students() {
  return (
    <>
      <NavBar />
      <div className="cards">
        <img
          className="student-image"
          src="/images/SMS-Register.jpg"
          alt="Penn Campus"
        />
        <div className="cards__container">
          <div className="cards__wrapper">
            <ul className="cards__items">
              <CardItem
                text="Register"
                path="/register"
                src="/images/SMS-ViewCourses.jpg"
                description="Add/Drop/View all courses"
              />

              <CardItem
                text="My Courses"
                path="/myCourses"
                src="/images/SMS-Profile.jpg"
                description="View the courses you registered for"
              />

              <CardItem
                text="Support"
                path="/support"
                src="/images/SMS-Support.jpg"
                description="Give us a call or send us an email, if you need any help"
              />
            </ul>
          </div>
        </div>
      </div>
      <Footer />
    </>
  );
}
