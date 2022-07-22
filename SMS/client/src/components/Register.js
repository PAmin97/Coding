import React, { useEffect, useState } from "react";
import axios from "axios";

function Register() {
  const [courseList, setCourseList] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");

  useEffect(() => {
    axios.get("http://localhost:3001/manageCourses").then((response) => {
      setCourseList(response.data);
    });
  }, []);

  return (
    <div className="register_container">
      <input
        type="text"
        placeholder="Search By CRN..."
        onChange={(event) => {
          setSearchTerm(event.target.value);
        }}
      />
      {courseList
        .filter((value) => {
          if (searchTerm === "") {
            return value;
          } else if (
            value.CRN.toLowerCase().includes(searchTerm.toLowerCase())
          ) {
            return value;
          } else {
            return "Course does not exist.";
          }
        })
        .map((value, key) => {
          return (
            <div className="course">
              {value.CRN} {value.courseName}
            </div>
          );
        })}
    </div>
  );
}

export default Register;
