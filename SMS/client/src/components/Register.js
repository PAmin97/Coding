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
    })
  }, []);

  const filterFunction = (value) => {
    if (searchTerm === "") {
      return value;
    } else if (value.CRN.toLowerCase().includes(searchTerm.toLowerCase())) {
      return value;
    } else if (value.courseName.toLowerCase().includes(searchTerm.toLowerCase())) {
      return value;
    }
  }

  const courseDescription = (
    <div className="course-list">
      <form>
        <div className="course-description">
          <h2 className="course-description-title">Course Decription</h2>
          <label>Username</label>
          <input type="text" name="username" required />
        </div>
        <div className="course-description">
          <label>Password</label>
          <input type="password" name="password" required />
        </div>
        <div className="button-container">
          <button>Add Course</button>
        </div>
      </form>
    </div>
  );

  const defaultForm = (
    <div className="deafult-form-container">
      <img
        className="register-image"
        src="/images/SMS-Register.jpg"
        alt="Logan Hall on Penn Campus"
      />
    </div>
  );

  return (
    <div className="register_container">
      <NavBar />
      {form ? courseDescription : defaultForm}
      <div className="courses">
        <h2 className="course-search-title">Search Courses</h2>
        <input
          className="search-course"
          type="text"
          placeholder="Search Courses..."
          onChange={(event) => {
            setSearchTerm(event.target.value)
          }}
        />
        {courseList.filter(filterFunction).map((value, key) => {
          return (
            <div className="course">
              <button className="openCourse" onClick={() => setForm(true)}>
                {value.CRN} {value.courseName}
              </button>
            </div>
          )
        })}
      </div>
    </div>
  )
}

export default Register;
