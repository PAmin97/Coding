import React, { useEffect, useState } from "react";
import NavBar from "./NavBar";
import axios from "axios";
import "./Register.css";
import { useParams } from "react-router-dom";

function Register() {
  const [courseList, setCourseList] = useState([]);
  const [courseInfoList, setCourseInfoList] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");
  let { CRN } = useParams();
  useEffect(() => {
    axios.get("http://localhost:3001/manageCourses").then((response) => {
      setCourseList(response.data);
    });
  }, []);

useEffect(() => {
    axios.get(`http://localhost:3001/courseInfo/${CRN}`).then((response) => {
      setCourseInfoList(response.data);
    });
  });

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
      <img classname='student-image' src='/images/SMS-Register.jpg' alt='Penn Campus'/>
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
            <div className="course">
              <button className="openCourse">
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
