import React, { useEffect, useState } from "react";
import NavBar from "./NavBar";
import axios from "axios";
import "./Register.css";

function Register() {
  const [courseList, setCourseList] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [form, setForm] = useState(false);

  useEffect(() => {
    axios.get("http://localhost:3001/manageCourses").then((response) => {
      setCourseList(response.data);
    });
  }, []);

  const filterFunction = (value) => {
    if (searchTerm === "") {
      return value;
    } else if (value.CRN.toLowerCase().includes(searchTerm.toLowerCase())) {
      return value;
    } else if (
      value.courseName.toLowerCase().includes(searchTerm.toLowerCase())
    ) {
      return value;
    }
  };

  const courseDescription = (
    <div className="form">
      <form>
        <div className="input-container">
          <label>Username</label>
          <input type="text" name="username" required />
        </div>
        <div className="input-container">
          <label>Password</label>
          <input type="password" name="password" required />
        </div>
        <div className="button-container">
          <button>Submit</button>
        </div>
      </form>
    </div>
  );

  const defaultForm = (
    <div className="deafult-form-container">
      <img
      className="register-image"
      src="/images/SMS-Register.jpeg" 
      alt="Logan Hall on Penn Campus" />
    </div>
  );

  return (
    <div className="register_container">
      <NavBar />
      <div className="form">{form ? courseDescription : defaultForm};</div>
      <input
          className="search-bar"
          type="text"
          placeholder="Search Courses..."
          onChange={(event) => {
            setSearchTerm(event.target.value);
          }}
        />
      <div className="courses">
        {courseList.filter(filterFunction).map((value, key) => {
          return (
            <div className="course">
              <button className="openCourse" onClick={() => setForm(true)}>
                {value.CRN} {value.courseName}
              </button>
            </div>
          );
        })}
      </div>
    </div>
  );
}

export default Register;
