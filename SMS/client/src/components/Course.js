import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

function Course() {
  let { CRN } = useParams();
  const [courseObject, setCourseObject] = useState({});

  useEffect(() => {
    axios.get(`http://localhost:3001/courseInfo/${CRN}`).then((response) => {
      setCourseObject(response.data);
    });
  });

  return (
    <div className="course-container">
        <div className="leftside"> Left Side
            <div className="course-descrption">{ courseObject.Description }</div>
            <div className="course-capacity">{ courseObject.currentCapacity }</div>
        </div>
        <div className="middle"> Middle
            <div className="course-descrption">{ courseObject.Description }</div>
            <div className="course-capacity">{ courseObject.currentCapacity }</div>
        </div>
        <div className="rightside"> Right Side
            <div className="course-descrption">{ courseObject.Description }</div>
            <div className="course-capacity">{ courseObject.currentCapacity }</div>
        </div>
    </div>
  );
}

export default Course;
