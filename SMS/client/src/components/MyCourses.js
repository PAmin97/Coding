import React, { useEffect, useState } from "react";
import axios from "axios";
import NavBar from "./NavBar";

function MyCourses() {
  const [myCourses, setMyCourses] = useState([]);
  const [courseList, setCourseList] = useState([]);
  //const [courses, setCourses] = useState([]);

  const res = (f) => {
    return myCourses.find((course) => {
      if (f.id === course.CourseId) {
        return f.courseName;
      }
      return 0
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
    <div className="myCourses_container">
      <NavBar />
      <div className="myCourses_list">
        {courseList.filter(res).map((course, key) => {
          return <div key={key}>{course.courseName}</div>;
        })}
      </div>
    </div>
  );
}
export default MyCourses;
