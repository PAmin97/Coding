const express = require('express');
const app = express();
const db = require('./models');
const cors = require('cors');

app.use(express.json());
app.use(cors());

//Routers
const courseRouter = require('./routes/manageCourses');
app.use("/manageCourses", courseRouter);
const courseInfoRouter = require('./routes/courseInfo');
app.use("/courseInfo", courseInfoRouter);
const studentsRouter = require('./routes/Students');
app.use("/auth", studentsRouter);
const addCourseRouter = require('./routes/addCourse');
app.use("/add", addCourseRouter);


db.sequelize.sync().then(() => {
    app.listen(3001, () => {
        console.log("Running on port 3001!");
    });
});


