import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import NavBar from "./NavBar";
import "./Course.css";

function Course() {
  let { CRN } = useParams();
  const [course, setCourse] = useState({});
  const [extraInfo, setExtraInfo] = useState({});

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
            <div>Meeting Days: {extraInfo[0].days}</div>
            <div>Start Time: {extraInfo[0].startTime}</div>
            <div>End Time: {extraInfo[0].endTime}</div>
            <div>Credit: 1</div>
            <div>Current Capacity: {course[0].currentCapacity}</div>
            <div>Max Capacity: {extraInfo[0].capacity}</div>
          </div>
        </div>
        <div className="middle">
          <h2 className="course-description-title">Course Description</h2>
          <div className="course-descrption">{course[0].Description}</div>
        </div>
        <div className="bottom">
          <div className="course-professor">{extraInfo[0].professor}</div>
        </div>
      </div>
    )
  );
}

export default Course;
