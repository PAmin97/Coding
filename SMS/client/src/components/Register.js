import React, { useEffect, useState } from "react";
import NavBar from "./NavBar";
import axios from "axios";
import "./Register.css";
import { useNavigate } from "react-router-dom";

function Register() {
  const [courseList, setCourseList] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  let navigate = useNavigate();

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

  return (
    <div className="register_container">
      <NavBar />
      <img className='student-image' src='/images/SMS-Register.jpg' alt='Penn Campus'/>
      <div className="courses">
        <h2 className="course-search-title">Search Courses</h2>
        <input
          className="search-course"
          type="text"
          placeholder="Search Courses..."
          onChange={(event) => {
            setSearchTerm(event.target.value);
          }}
        />
        {courseList.filter(filterFunction).map((value, key) => {
          return (
            <div className="course" key={key}>
              <button className="openCourse" onClick={() => {navigate(`/course/${value.CRN}`)}}>
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
