const express = require('express');
const router = express.Router();
const { CourseInfo } = require('../models');

router.get('/:courseID', async (req, res) => {
    const courseID = req.params.courseID;
    const course = await CourseInfo.findAll({ where: {courseCRN: courseID}});
    res.json(course);
});

router.post('/', async (req, res) => {
    const courseInfo = req.body;
    await CourseInfo.create(courseInfo);
    res.json(courseInfo)
});


module.exports = router;