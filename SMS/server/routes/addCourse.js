const express = require("express");
const router = express.Router();
const { addCourse } = require("../models");
const { validateToken } = require("../middlewares/AuthMiddleware");

router.post("/", validateToken, async (req, res) => {
  const { CourseId } = req.body;
  const StudentId = req.student.id;

  const found = await addCourse.findOne({
    where: {
      CourseId: CourseId,
      StudentId: StudentId,
    },
  });

  if (!found) {
    await addCourse.create({
      CourseId: CourseId,
      StudentId: StudentId,
    });
    res.json("This course was added to your list");
  } else {
    await addCourse.destroy({
      where: {
        CourseId: CourseId,
        StudentId: StudentId,
      },
    });
    res.json("This course was removed from your list");
  }
});

module.exports = router;
