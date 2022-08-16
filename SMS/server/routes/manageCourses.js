const express = require("express");
const router = express.Router();
const { Courses, addCourse } = require("../models");
const { validateToken } = require("../middlewares/AuthMiddleware");

router.get("/", async (req, res) => {
  const listOfCourses = await Courses.findAll({ include: [addCourse] });
  res.json(listOfCourses);
});

router.get("/courses", validateToken, async (req, res) => {
  const addedCourses = await addCourse.findAll({
    where: { StudentId: req.student.id },
  });
  res.json(addedCourses);
});

router.get("/byCRN/:courseID", async (req, res) => {
  const courseID = req.params.courseID;
  const course = await Courses.findAll({ where: { CRN: courseID } });
  res.json(course);
});

router.post("/", async (req, res) => {
  const course = req.body;
  await Courses.create(course);
  res.json(course);
});

module.exports = router;
