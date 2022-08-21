import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import NavBar from "./NavBar";
import "./Course.css";

function Course() {
  let { CRN } = useParams();
  let navigate = useNavigate();
  const [course, setCourse] = useState({});
  const [extraInfo, setExtraInfo] = useState({});
  const [addedCourse, setAddedCourse] = useState([]);

  useEffect(() => {
    axios.get(`http://localhost:3001/courseInfo/${CRN}`).then((response) => {
      setCourse(response.data);
    });

    axios
      .get(`http://localhost:3001/manageCourses/byCRN/${CRN}`)
      .then((response) => {
        setExtraInfo(response.data);
      });
  }, [CRN]);

  useEffect(() => {
    axios
      .get("http://localhost:3001/manageCourses/courses", {
        headers: { accessToken: localStorage.getItem("accessToken") },
      })
      .then((response) => {
        setAddedCourse(response.data);
      });
  }, []);

  const addACourse = (courseId) => {
    if (!localStorage.getItem("accessToken")) {
      navigate("/login");
    } else {
      axios
        .post(
          "http://localhost:3001/add",
          { CourseId: courseId },
          { headers: { accessToken: localStorage.getItem("accessToken") } }
        )
        .then((response) => {
          // setAddedCourse(!addedCourse);
          alert(response.data);
        });
    }
  };

  const foundId = (courseId) => {
    Object.values(addedCourse).some((course) => {
      if (course.CourseId === courseId) {
        console.log(course.CourseId === courseId);
        return true;
      } else {
        return false;
      }
    });
  };

  return (
    course.length > 0 &&
    extraInfo.length > 0 && (
      <div className="course-container">
        <NavBar />
        <div className="top">
          <h2 className="course-info-title">Course Information</h2>
          <h3 className="course-info">
            {extraInfo[0].CRN} {extraInfo[0].courseName}
          </h3>
          <div className="extra-course-info">
            <div>Professor: {extraInfo[0].professor}</div>
            <div>Meeting Days: {extraInfo[0].days}</div>
            <div>Start Time: {extraInfo[0].startTime}</div>
            <div>End Time: {extraInfo[0].endTime}</div>
            <div>Credit: 1</div>
            <div>Max Capacity: {extraInfo[0].capacity}</div>
          </div>
        </div>
        <div className="middle">
          <h2 className="course-description-title">Course Description</h2>
          <div className="course-descrption">{course[0].Description}</div>
        </div>
        <div className="bottom">
          {foundId(extraInfo[0].id) ? (
            <button
              onClick={() => {
                addACourse(extraInfo[0].id);
              }}
            >
              Remove Course
            </button>
          ) : (
            <button
              onClick={() => {
                addACourse(extraInfo[0].id);
              }}
            >
              Add Course
            </button>
          )}
        </div>
      </div>
    )
  );
}

export default Course;
