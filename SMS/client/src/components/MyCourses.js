import React, { useEffect, useState } from "react";
import axios from "axios";
import NavBar from "./NavBar";
import "./MyCourses.css";

function MyCourses() {
  const [myCourses, setMyCourses] = useState([]);
  const [courseList, setCourseList] = useState([]);

  const res = (f) => {
    return myCourses.find((course) => {
      if (f.id === course.CourseId) {
        return f.courseName;
      }
      return 0;
    });
  };

  useEffect(() => {
    axios
      .get("http://localhost:3001/manageCourses/courses", {
        headers: { accessToken: localStorage.getItem("accessToken") },
      })
      .then((response) => {
        setMyCourses(response.data);
      });

    axios.get("http://localhost:3001/manageCourses").then((response) => {
      setCourseList(response.data);
    });
  }, []);

  return (
    <div className="myCourses-container">
      <NavBar />
      <div>
        {courseList.filter(res).map((course, key) => {
          return (
            <div className="myCourse-list" key={key}>
              {course.courseName}
            </div>
          );
        })}
      </div>
    </div>
  );
}
export default MyCourses;
