const express = require('express');
const router = express.Router();
const { CourseInfo } = require('../models');

router.get('/:courseID', async (req, res) => {
    const courseID = req.params.courseID;
    const course = await CourseInfo.findAll({ where: {courseCRN: courseID}});
    res.json(course);
});


module.exports = router;