import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";
import NavBar from "./NavBar";

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
  }, []);

  return (
    course.length > 0 && (
      <div className="course-container">
        <NavBar />
        <div className="top">
          <div className="course-info">
            <div className="course-crn">{extraInfo[0].CRN}</div>
            <div className="course-name">{extraInfo[0].courseName}</div>
          </div>
          <div className="course-days">Meeting Days: {extraInfo[0].days}</div>
          <div className="course-st">Start Time: {extraInfo[0].startTime}</div>
          <div className="course-et">End Time: {extraInfo[0].endTime}</div>
          <div className="course-credits">Credit: 1</div>
          <div className="course-capacity">Current Capacity: {course[0].currentCapacity}</div>
          <div className="course-capacity">Max Capacity: {extraInfo[0].capacity}</div>
        </div>
        <div className="middle">
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
